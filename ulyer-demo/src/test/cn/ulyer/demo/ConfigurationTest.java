package cn.ulyer.demo;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationTest {

    @Test
    public void test() throws FileNotFoundException {
        OrmConfiguration ormConfiguration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        Pattern pattern = Pattern.compile(OrmConfiguration.paramRegex());
        Matcher matcher  = pattern.matcher("select * from user where id =  #{id} and name = #{name}");
        while (matcher.find()){
            String find = matcher.group();
            System.out.println(find);
        }

    }

}
