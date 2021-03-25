package cn.ulyer.demo.proxy;

import cn.ulyer.orm.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

public class OtherInterceptor implements Interceptor{
    @Override
    public Object interceptor(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("other");
        return invocation.invoke();
    }
}
