package cn.ulyer.orm.config;

import com.alibaba.fastjson.JSON;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ResourceConfigurationLoader {


    public static OrmConfiguration loadConfiguration(String filePath) throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        return url==null?null:loadConfiguration(new FileInputStream(url.getPath()));
    }

    public static OrmConfiguration loadConfiguration(InputStream inputStream){
        Yaml yaml = new Yaml();
        Map<String,Object> data = yaml.loadAs(inputStream,Map.class);
        OrmConfiguration configuration = new OrmConfiguration();
        System.out.println(JSON.toJSONString(data));
        return new OrmConfiguration();
    }


}
