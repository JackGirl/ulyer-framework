package cn.ulyer.orm.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.handler.*;
import cn.ulyer.orm.utils.LogUtils;
import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractExecutor implements Executor {


    private OrmConfiguration ormConfiguration ;

    private Connection connection;

    private StatementHandler statementHandler ;

    private ExecutorHandler executorHandler;

    @SneakyThrows
    public AbstractExecutor(Connection connection, OrmConfiguration ormConfiguration){
        connection.setAutoCommit(false);
        this.ormConfiguration = ormConfiguration;
        this.connection = connection;
        this.statementHandler = new PrepareStatementHandler();
        executorHandler = ormConfiguration.newExecutorHandler(new DefaultExecutorHandler());
    }




    @Override
    public <T> T execute( MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        //执行
        //返回包装
        try {
            connection.setAutoCommit(false);
            switch (mapperWrapper.getMapperMethod().getMapperSqlType()){
                case SELECT:
                   ResultSet resultSet = this.executorHandler.query(statement);
                    return resolverResultSet(resultSet,mapperWrapper);
                case DELETE:
                case INSERT:
                case UPDATE:
                    Object count = Integer.valueOf(executorHandler.update(statement,null));
                    connection.commit();
                    return (T)count;
                default:
                    throw  new IllegalStateException("no  executor type for this mapperSql"+ mapperWrapper.getMapperMethod().getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @Override
    public <E> List<E> selectList(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        ResultSet resultSet = this.executorHandler.query(statement);
        return resolverResultSet(resultSet,mapperWrapper);
    }

    @Override
    public <T> T selectOne(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        ResultSet resultSet = this.executorHandler.query(statement);
        return resolverResultSet(resultSet,mapperWrapper);
    }

    @Override
    public int insert(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        return doUpdate(statement);
    }

    @SneakyThrows
    public int doUpdate(PreparedStatement statement){
        int result = this.executorHandler.update(statement,null);
        connection.commit();
        return result;
    }

    @Override
    public int update(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        return doUpdate(statement);

    }

    @Override
    public int delete(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        return doUpdate(statement);
    }

    public <T>T resolverResultSet(ResultSet resultSet,MapperWrapper mapperWrapper){
        try {
            int rows = resultSet.getRow();
            Class elementType = getMethodElementReturnType(mapperWrapper);
            if(rows>1){
                List list = new ArrayList();
                for (int i = 0; i < rows; i++) {
                    Object element = getElementFromResultSet(resultSet,elementType);
                    boolean add = element==null?false:list.add(element);
                }
                return (T) list;
            }
            return (T) getElementFromResultSet(resultSet,elementType);
        } catch (SQLException|ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LogUtils.error(e);
            }
        }
    }

    @SneakyThrows
    public Object getElementFromResultSet(ResultSet resultSet,Class elementType)  {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        if(resultSet.next()){
            if(Map.class.isAssignableFrom(elementType)){
                Map<String,Object> element = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object val = ormConfiguration.getTypeHandler(JDBCType.valueOf(resultSetMetaData.getColumnType(i))).getResult(resultSet,i);
                    element.put(resultSetMetaData.getColumnName(i),val);
                }
                return element;
            }
            if(Number.class.isAssignableFrom(elementType)){
                Assert.isFalse(columnCount>1,"返回值类型不配 存在多列数据");
                Object val = ormConfiguration.getTypeHandler(JDBCType.valueOf(resultSetMetaData.getColumnType(1))).getResult(resultSet,1);
                return val;
            }
            Object obj = elementType.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                Object val = ormConfiguration.getTypeHandler(JDBCType.valueOf(resultSetMetaData.getColumnType(i))).getResult(resultSet,i);
                String columnName = resultSetMetaData.getColumnName(i);
                Field field = elementType.getDeclaredField(columnName);
                field.setAccessible(true);
                Assert.notNull(field);
                field.set(obj,val);
            }
            return obj;
        }
        return null;
    }



    private Class getMethodElementReturnType(MapperWrapper mapperWrapper) throws ClassNotFoundException {
        Class returnType = mapperWrapper.getMapperMethod().getResultType();
        Type type = mapperWrapper.getMapperMethod().getMethod().getGenericReturnType();
        Class actualClass = null;
        if(returnType == List.class){
            if (type instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                //因为list泛型只有一个值 所以直接取0下标
                String typeName = actualTypeArguments[0].getTypeName();
                //真实返回值类型 Class对象
                actualClass = Class.forName(typeName);
            }else {
                actualClass = Map.class;
            }
        }
        actualClass = actualClass==null?returnType:actualClass;
        return actualClass;
    }

    public PreparedStatement preparedStatement(Connection connection,MapperWrapper mapperWrapper){
        PreparedStatement statement = null;
        try{
            //标签根据参数解析
            //生成sql
            StatementHandler statementHandler = ormConfiguration.newStatementHandler(this.statementHandler);
            statement = statementHandler.createStatement(connection,mapperWrapper);
            //设置参数
            ParameterHandler parameterHandler = ormConfiguration.newParameterHandler(new RegexParameterResolver());
            parameterHandler.setParameter(statement,mapperWrapper);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return statement;
    }



}
