package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultReaderFilter implements  AnnotationReader{

    private List<AnnotationReader> annotationReaders;

    public DefaultReaderFilter(){
        annotationReaders = new ArrayList<>();
        annotationReaders.add(new SelectAnnotationReader());
        annotationReaders.add(new UpdateAnnotaionReader());
        annotationReaders.add(new InsertAnnotaionReader());
        annotationReaders.add(new DeleteAnnotationReader());
    }


    @Override
    public <T> T read(Class<?> mapperClass, Method method) {
        MapperMethod mapperMethod ;
        for (AnnotationReader annotationReader : annotationReaders) {
             mapperMethod  = annotationReader.read(mapperClass,method);
            if(mapperMethod!=null){
                return (T) mapperMethod;
            }
        }
        mapperMethod = new MapperMethod();
        mapperMethod.setResultType(method.getReturnType());
        mapperMethod.setId(mapperClass.getName()+"."+method.getName());
        return (T) mapperMethod;
    }
}
