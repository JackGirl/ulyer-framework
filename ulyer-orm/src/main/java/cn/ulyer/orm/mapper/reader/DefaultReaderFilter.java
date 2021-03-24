package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultReaderFilter implements  AnnotationReader{

    private List<AnnotationReader> annotationReaders;

    public DefaultReaderFilter(){
        annotationReaders = new ArrayList<>();
        annotationReaders.add(new UpdateAnnotaionReader());
        annotationReaders.add(new InsertAnnotaionReader());
        annotationReaders.add(new DeleteAnnotationReader());
        annotationReaders.add(new SelectAnnotationReader());
    }




    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        for (AnnotationReader annotationReader : annotationReaders) {
            annotationReader.read(mapperClass,method,mapperMethod);
        }
    }
}
