package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.utils.LogUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultExecutorHandler implements ExecutorHandler{


    @Override
    public int update(PreparedStatement statement, TypeHandler typeHandler)  {
        LogUtils.info(statement);
        try{
            return statement.executeUpdate();
        }catch (Exception e){
            LogUtils.error(e);
            return 0;
        }
    }

    @Override
    public ResultSet query(PreparedStatement preparedStatement)  {
        LogUtils.info(preparedStatement);
        try{
            ResultSet resultSet =  preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            LogUtils.error(e);
            return null;
        }
    }

}
