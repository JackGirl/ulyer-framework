package cn.ulyer.orm.mapper.parameter;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.ParameterMapping;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ParameterObject {

    private Object value;

    private Class<?> valueClass;

    private int parameterSize;

    private List<ParameterMapping> parameterMappings;

    public ParameterObject(Object value, Class<?> valueClass, List<ParameterMapping> parameterMappings,int parameterSize) {
        this.valueClass = valueClass;
        this.value = value;
        this.parameterMappings = parameterMappings;
        this.parameterSize = parameterSize;
    }

    public static ParameterObject newParameter(MapperMethod mapperMethod, Object... parameter) {
        if (parameter == null) {
            return new ParameterObject(null, String.class, mapperMethod.getParameterMappings(),0);
        }
        if (parameter.length == 1) {
            return new ParameterObject(parameter[0], parameter[0].getClass(), mapperMethod.getParameterMappings(),1);
        }
        Map<String, Object> value = new HashMap<>(5);
        int parameterMappingSize = mapperMethod.getParameterMappings()==null?0:mapperMethod.getParameterMappings().size();
        for (int i = 0; i < parameter.length; i++) {
            if(i<parameterMappingSize-1){
                value.put(mapperMethod.getParameterMappings().get(i).getName(), parameter[i]);
                continue;
            }
            value.put("arg" + i, parameter[i]);
        }
        return new ParameterObject(value, Map.class, mapperMethod.getParameterMappings(),parameter.length);
    }

    public <T>T getParameterObjectByMapping(ParameterMapping parameterMapping){
        if(parameterSize==1){
            return parameterMapping.getType().isAssignableFrom(valueClass)? (T) value :null;
        }
        return (T) ((Map) value).get(parameterMapping.getName());
    }

    public <T> T getParameterByName(String name) {
        Object returnVal = null;
        if (name.contains(OrmConfiguration.PARAM_SPLIT)) {
            String[] splits = StrUtil.split(name, OrmConfiguration.PARAM_SPLIT);
            returnVal = null;
            for (int i = 0; i < splits.length; i++) {
                returnVal = getDeepParameterByName(returnVal==null?value:returnVal, splits[i]);
                if (returnVal == null) {
                    Assert.isFalse(i != splits.length - 1,"no parameter for nameExpression :" + name);
                    break;
                }
            }
        }
        if (returnVal == null) {
            returnVal = getDeepParameterByName(value, name);
        }
        Assert.notNull(returnVal,"not found param for name :"+name);
        return (T) returnVal;

    }

    private <T> T getDeepParameterByName(Object target, String name) {
        Assert.notNull(target);
        Class<?> valClass = target.getClass();
        if (String.class.equals(valClass)) {
            return (T) target;
        }
        if (Number.class.isAssignableFrom(valClass)) {
            return (T) target;
        }
        if (Map.class.isAssignableFrom(valClass)) {
            return (T) ((Map) target).get(name);
        }
        if(ParameterObject.class.isAssignableFrom(valClass)){
            return (T)getDeepParameterByName(((ParameterObject)target).getValue(),name);
        }
        Object returnVal = null;
        try {
            Field field = valClass.getDeclaredField(name);
            field.setAccessible(true);
            returnVal = field.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return (T) returnVal;
    }




}
