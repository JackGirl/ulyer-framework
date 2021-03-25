package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.mapper.MapperDefinition;
import org.dom4j.DocumentException;

import java.io.FileInputStream;
import java.io.InputStream;

public interface OrmFileReader {

    MapperDefinition readDefinition(InputStream fileInputStream) throws DocumentException, ClassNotFoundException;

}
