package cn.ulyer.orm.mapper.parameter;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.ParameterMapping;
import cn.ulyer.orm.utils.LogUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ParameterObject {

    private Object value;

    private Class<?> valueClass;

    private List<ParameterMapping> parameterMappings;

    private final static String PARAM_SPLIT = ".";

    public ParameterObject(Object value,Class<?> valueClass,List<ParameterMapping> parameterMappings){
        this.valueClass = valueClass;
        this.value = value;
        this.parameterMappings = parameterMappings;
    }

    public static ParameterObject newParameter(MapperMethod mapperMethod,Object[] parameter){
        if(parameter==null){
            return new ParameterObject(null,Object.class,mapperMethod.getParameterMappings());
        }
        if(parameter.length==1){
            return new ParameterObject(parameter[0],parameter[0].getClass(),mapperMethod.getParameterMappings());
        }
        Map<String,Object> value = new HashMap<>(5);
        for (int i = 0; i < parameter.length; i++) {
            value.put("arg"+i,parameter);
        }
        return new ParameterObject(parameter,Map.class,mapperMethod.getParameterMappings());
    }

    public <T> T getParameterByName(String name) throws IllegalAccessException {
        Object returnVal = null;
            if(name.contains(PARAM_SPLIT)){
                String [] splits = name.split(PARAM_SPLIT);
                 returnVal = null;
                for (int i = 0; i < splits.length; i++) {
                    returnVal = getDeepParameterByName(value,splits[i]);
                    if(returnVal==null){
                        if(i!=splits.length-1){
                            throw new IllegalArgumentException("no parameter for nameExpression :" +name);
                        }
                        break;
                    }
                }
            }
            if(returnVal==null){
                returnVal = getDeepParameterByName(value,name);
            }
            Assert.notNull(returnVal);
            return (T) returnVal;

    }

    private <T>T getDeepParameterByName(Object target,String name){
        Assert.notNull(target);
        Class<?> valClass = target.getClass();
        Object returnVal = null;
        if(String.class.equals(valClass)){
            returnVal = target;
        }
        if(Number.class.isAssignableFrom(valClass)){
            returnVal = target;
        }
        if(Map.class.isAssignableFrom(valClass)) {
            returnVal =  ((Map)target).get(name);
        }
        try{
            Field field = valClass.getDeclaredField(name);
            field.setAccessible(true);
            field.get(target);
        }catch (Exception e){
            LogUtils.error(e);
            throw new RuntimeException("参数获取入参失败");
        }
        return (T) returnVal;
    }


    public static void main(String[] args) {
        System.out.println(Number.class.isAssignableFrom(int.class));
    }



}
