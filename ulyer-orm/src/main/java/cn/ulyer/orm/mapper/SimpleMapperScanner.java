package cn.ulyer.orm.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SimpleMapperScanner  extends AbstractBaseMapperScanner{

    private MapperDefinitionReader definitionReader;

    public SimpleMapperScanner(){
        definitionReader = new DefaultMapperDefinitionReader();
    }

    @Override
    public MapperDefinition register(Class<?> mapperClass) {
        return this.definitionReader.read(mapperClass);
    }



    @Override
    public MapperDefinition register(InputStream file) {
        return definitionReader.read(file);
    }
}
