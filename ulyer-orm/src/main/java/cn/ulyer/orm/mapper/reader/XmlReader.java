package cn.ulyer.orm.mapper.reader;

import cn.hutool.core.util.StrUtil;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperDefinition;
import cn.ulyer.orm.mapper.MapperMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

public class XmlReader implements OrmFileReader {

    @Override
    public MapperDefinition readDefinition(InputStream fileInputStream) throws DocumentException, ClassNotFoundException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(fileInputStream);
        Element rootElement = document.getRootElement();
        MapperDefinition mapperDefinition = new MapperDefinition();
        mapperDefinition.setNamespace(rootElement.attributeValue("namespace"));
        Iterator<Element> iterator = rootElement.elementIterator();
        Map<String, MapperMethod> mapperMethodMap = new HashMap<>();
        while (iterator.hasNext()){
            Element tag = iterator.next();
            String name = tag.getName();
            switch (name){
                case "insert":
                    MapperMethod insertMapper = insertMapper(rootElement,tag);
                    mapperMethodMap.put(insertMapper.getId(),insertMapper);
                    break;
                case "update":
                    MapperMethod updateMapper = updateMapper(rootElement,tag);
                    mapperMethodMap.put(updateMapper.getId(),updateMapper);
                    break;
                case "delete":
                    MapperMethod deleteMapper = deleteMapper(rootElement,tag);
                    mapperMethodMap.put(deleteMapper.getId(),deleteMapper);
                    break;
                case "select":
                    MapperMethod selectMapper = selectMapper(rootElement,tag);
                    mapperMethodMap.put(selectMapper.getId(),selectMapper);
                    break;

            }
        }
        mapperDefinition.setMapperMethodMap(mapperMethodMap);
        return mapperDefinition;
    }


    public MapperMethod get(MapperMethod mapperMethod,Element root,Element element) throws ClassNotFoundException {
        String resultType = element.attributeValue("resultType");
        String sql = element.getStringValue();
        mapperMethod.setId(root.attributeValue("namespace")+"."+element.attributeValue("id"));
        mapperMethod.setXml(element.asXML());
        if(StrUtil.isNotBlank(resultType)){
            mapperMethod.setResultType(Class.forName(resultType));
        }
        return mapperMethod;
    }


    public MapperMethod deleteMapper(Element root,Element element) throws ClassNotFoundException {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMapperSqlType(MapperSqlType.DELETE);
        return get(mapperMethod,root,element);
    }


    public MapperMethod updateMapper(Element root,Element element) throws ClassNotFoundException {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMapperSqlType(MapperSqlType.UPDATE);
        return get(mapperMethod,root,element);
    }


    public MapperMethod insertMapper(Element root,Element element) throws ClassNotFoundException {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMapperSqlType(MapperSqlType.INSERT);
        return get(mapperMethod,root,element);
    }


    public MapperMethod selectMapper(Element root,Element element) throws ClassNotFoundException {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setMapperSqlType(MapperSqlType.SELECT);
        return get(mapperMethod,root,element);
    }

}
