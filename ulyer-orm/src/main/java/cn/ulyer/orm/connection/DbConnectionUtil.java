package cn.ulyer.orm.connection;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 19:49 2021/3/20
 */
@Data
public class DbConnectionUtil {


    static Properties   jdbcProperties = new Properties();

    private static String url;

    private static String username;

    private static String password;

    private static String driver;

    static {
        try {
            jdbcProperties.load(DbConnectionUtil.class.getResourceAsStream("/jdbc.properties"));
            url = jdbcProperties.getProperty("jdbc.url");
            username = jdbcProperties.getProperty("jdbc.username");
            password = jdbcProperties.getProperty("jdbc.password");
            driver = jdbcProperties.getProperty("jdbc.driver-class-name");
            Class.forName(driver);
        } catch (Exception e) {
           throw new RuntimeException("read jdbc error");
        }
    }

    public static Connection getConnection()  {
        Connection connection =null ;
        try{
            connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("connection is null");
        }
        return connection;
    }

    public static void closeConnection(Statement statement,Connection connection) {
        try {
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("关闭数据库异常");
        }
    }



}
