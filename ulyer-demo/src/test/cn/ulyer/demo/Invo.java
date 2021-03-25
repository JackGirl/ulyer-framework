package cn.ulyer.demo;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class Invo implements InvocationHandler {

    Object target;

    public  Object newInstance(Object target,InvocationHandler invocationHandler){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),invocationHandler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("11111");
        Object result =  method.invoke(target,args);
        System.out.println("2222");
       return result;
    }
}