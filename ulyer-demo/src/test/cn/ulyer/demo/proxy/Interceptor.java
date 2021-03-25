package cn.ulyer.demo.proxy;

import cn.ulyer.orm.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {


    Object interceptor(Invocation invocation) throws InvocationTargetException, IllegalAccessException;


}
