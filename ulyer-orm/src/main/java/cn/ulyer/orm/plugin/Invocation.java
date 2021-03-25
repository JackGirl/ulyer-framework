package cn.ulyer.orm.plugin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class Invocation {

    private Object target;

    private Method method;

    private Object [] args;

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,args);
    }

}
