package cn.ulyer.demo;

import cn.hutool.core.map.MapUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.excutor.AbstractExecutor;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.excutor.SimpleExecutor;
import cn.ulyer.orm.mapper.MapperMethod;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationTest {

    @Test
    public void test() throws FileNotFoundException {
        OrmConfiguration ormConfiguration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        AbstractExecutor executor = new SimpleExecutor(ormConfiguration);
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMapperSqlType(MapperSqlType.SELECT);
        mapperMethod.setSql("select * from test_2 where id = {id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","1");
        Executor proxy = (Executor) ormConfiguration.newExecutor(executor);
        System.out.println(JSON.toJSONString(ormConfiguration.getRegisterConf(),true));

    }

}
