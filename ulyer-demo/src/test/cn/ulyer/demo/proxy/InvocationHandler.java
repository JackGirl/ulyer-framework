package cn.ulyer.demo.proxy;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class InvocationHandler {


    private Object target;

    private Method method;

    private Object [] args;



    public Object process() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,args);
    }


}
