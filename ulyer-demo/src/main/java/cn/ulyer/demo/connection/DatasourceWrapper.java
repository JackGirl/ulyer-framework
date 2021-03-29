package cn.ulyer.demo.connection;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;

import javax.sql.DataSource;
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
public class DatasourceWrapper {

    private static DataSource dataSource;

    static Properties   jdbcProperties = new Properties();


    static {
        try {
            DruidDataSource druidDataSource = new DruidDataSource();
            jdbcProperties.load(DatasourceWrapper.class.getResourceAsStream("/jdbc.properties"));
            druidDataSource.setUrl(jdbcProperties.getProperty("jdbc.url"));
            druidDataSource.setUsername(jdbcProperties.getProperty("jdbc.username"));
            druidDataSource.setPassword(jdbcProperties.getProperty("jdbc.password"));
            druidDataSource.setDriverClassName(jdbcProperties.getProperty("jdbc.driver-class-name"));
            dataSource = druidDataSource;
        } catch (Exception e) {
           throw new RuntimeException("read jdbc error");
        }
    }

    public static Connection getConnection() throws SQLException {
       return dataSource.getConnection();
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
