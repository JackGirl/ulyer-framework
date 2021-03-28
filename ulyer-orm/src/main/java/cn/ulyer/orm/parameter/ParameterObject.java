package cn.ulyer.orm.parameter;

import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.utils.LogUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
public class ParameterObject {

    private Object value;

    private Class<?> valueClass;

    public ParameterObject(Object value,Class<?> valueClass){
        this.valueClass = valueClass;
        this.value = value;
    }

    public static ParameterObject newParameter(MapperMethod mapperMethod,Object... parameter){
        if(parameter==null){
            return new ParameterObject(null,Object.class);
        }
        if(parameter.length==1){
            return new ParameterObject(parameter[0],parameter[0].getClass());
        }
        Map<String,Object> value = new HashMap<>();
        for (Object o : parameter) {
        }
        return new ParameterObject(parameter,Map.class);
    }

    public <T> T getParameterByName(String name) {
        if(String.class.equals(valueClass)){
            return (T) value;
        }
        if(Number.class.isAssignableFrom(valueClass)){
            return (T) value;
        }
        if(Map.class.isAssignableFrom(valueClass)) {
            return (T) ((Map)value).get(name);
        }
        try{
            Field field = valueClass.getDeclaredField(name);
            field.setAccessible(true);
            field.get(value);
        }catch (Exception e){
            LogUtils.error(e);
            throw new RuntimeException("参数获取入参失败");
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Number.class.isAssignableFrom(int.class));
    }



}
