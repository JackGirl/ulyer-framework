package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.utils.LogUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DefaultExecutorHandler implements ExecutorHandler{


    @Override
    public int update(PreparedStatement statement, TypeHandler typeHandler)  {
        try{
            return statement.executeUpdate();
        }catch (Exception e){
            LogUtils.error(e);
            return 0;
        }
    }

    @Override
    public ResultSet query(PreparedStatement preparedStatement) {
        try{
            return preparedStatement.executeQuery();
        }catch (Exception e){
            LogUtils.error(e);
            return null;
        }
    }

}
