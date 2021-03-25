package cn.ulyer.demo.proxy;

import cn.ulyer.orm.plugin.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;

public class Plugin implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;


    public Plugin(Object target,Interceptor interceptor){
        this.target = target;
        this.interceptor = interceptor;
    }


    public static Object wrap(Object target, Interceptor interceptor) {
        Class<?> type = target.getClass();
        HashSet interfaces;
        for(interfaces = new HashSet(); type != null; type = type.getSuperclass()) {
            Class[] var3 = type.getInterfaces();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<?> c = var3[var5];
                    interfaces.add(c);
            }
        }
        boolean size =  interfaces.size() > 0;
        if(size){
            Object proxy =  Proxy.newProxyInstance(Plugin.class.getClassLoader(), (Class[])interfaces.toArray(new Class[interfaces.size()]), new Plugin(target, interceptor));
            return proxy;
        }else{
            return target;
        }

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method!=null?this.interceptor.interceptor(new Invocation(this.target, method, args)):method.invoke(target,args);
    }
}
