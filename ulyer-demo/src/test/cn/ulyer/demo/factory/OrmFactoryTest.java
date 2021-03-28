package cn.ulyer.demo.factory;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import cn.ulyer.orm.excutor.SqlSession;
import cn.ulyer.orm.factory.DefaultOrmFactory;
import cn.ulyer.orm.factory.OrmFactory;
import cn.ulyer.orm.factory.OrmFactoryBuilder;
import com.alibaba.druid.DbType;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

public class OrmFactoryTest {



    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/framework-db?useSSL=false&useCharacter=true&characterEncoding=utf8");
        dataSource.setPassword("xiaoxiao1997");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setDbType(DbType.mysql);

        return dataSource;
    }

    @Test
    public void flow() throws FileNotFoundException {
        OrmConfiguration configuration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        OrmFactory factory = new DefaultOrmFactory(configuration);
        factory.setDataSource(dataSource());

    }


}
