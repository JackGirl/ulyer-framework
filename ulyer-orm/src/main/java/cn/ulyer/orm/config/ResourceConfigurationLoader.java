package cn.ulyer.orm.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceConfigurationLoader {


    public static OrmConfiguration loadConfiguration(String filePath) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        return url==null?null:loadConfiguration(url.openStream());
    }

    public static OrmConfiguration loadConfiguration(InputStream inputStream){
        Yaml yaml = new Yaml();
        OrmConfiguration data = yaml.loadAs(inputStream,OrmConfiguration.class);
        return data;
    }


}
