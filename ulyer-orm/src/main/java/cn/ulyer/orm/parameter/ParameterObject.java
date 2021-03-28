package cn.ulyer.orm.parameter;

import lombok.Data;

@Data
public class ParameterObject {

    private Object parameter;

    private Class<?> parameterClass;

    public static ParameterObject newParameter(Object parameter){
        return new ParameterObject();
    }

    public Object getParameterByName(String name){
        return null;
    }

}
