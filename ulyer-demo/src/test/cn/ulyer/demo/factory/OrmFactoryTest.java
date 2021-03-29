package cn.ulyer.demo.factory;

import cn.ulyer.demo.dao.UserMapper;
import cn.ulyer.demo.entity.User;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import cn.ulyer.orm.excutor.SqlSession;
import cn.ulyer.orm.factory.DefaultOrmFactory;
import cn.ulyer.orm.factory.OrmFactory;
import cn.ulyer.orm.factory.OrmFactoryBuilder;
import com.alibaba.druid.DbType;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class OrmFactoryTest {



    public static DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/framework-db?useSSL=false&useCharacter=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
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
        UserMapper mapper = factory.getMapper(UserMapper.class);
        User user = mapper.getById("1");
        System.out.println(user);
        SqlSession sqlSession = factory.createSqlSession();
        User u = new User();
        u.setName("orm 插入测试");
        u.setId("222");
        sqlSession.insert(UserMapper.class.getName()+"."+"saveUser",u);
        List<Map> users = mapper.listUser();
        System.out.println(users);
    }


}
