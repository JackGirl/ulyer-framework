package cn.ulyer.demo.service;

import cn.ulyer.demo.connection.DatasourceWrapper;
import cn.ulyer.demo.entity.User;

import java.sql.*;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:21 2021/3/20
 */
public class UserService {

    public Object getUser(String id) throws SQLException {
        Connection connection = DatasourceWrapper.getConnection();
        String sql = "select * from User where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        connection.setReadOnly(true);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String userId = resultSet.getString(1);
            String name= resultSet.getString(2);
            return new User().setId(userId).setName(name);
        }
        DatasourceWrapper.closeConnection(statement,connection);
        return null;
    }

    public int insert(String id,String name) throws SQLException {
        Connection connection = DatasourceWrapper.getConnection();
        try{
            connection.setAutoCommit(false);
            String sql = "insert into user  (id,name) value (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,name);
            int result =  statement.executeUpdate();
            connection.commit();
            DatasourceWrapper.closeConnection(statement,connection);
            return result;
        }catch (Exception e){
            connection.rollback();
        }
        return 0;
    }
}
