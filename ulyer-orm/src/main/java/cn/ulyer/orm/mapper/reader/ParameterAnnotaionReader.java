package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Param;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.ParameterMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ParameterAnnotaionReader implements AnnotationReader {

    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        Parameter[] args = method.getParameters();
        if(args!=null){
            List<ParameterMapping> parameterMappings = new ArrayList<>();
            int paramIndex = 0;
            for (Parameter arg : args) {
                ParameterMapping parameterMapping = new ParameterMapping();
                parameterMapping
                        .setName(arg.isAnnotationPresent(Param.class)?arg.getAnnotation(Param.class).value()
                                : OrmConfiguration.DEFAULT_PARAM_PREFIX+paramIndex);
                parameterMapping.setType(arg.getType());
                parameterMappings.add(parameterMapping);
                paramIndex++;
            }
            mapperMethod.setParameterMappings(parameterMappings);
        }
    }
}
