package cn.ulyer.orm.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class PluginInvocationHandler  implements InvocationHandler {

    Object target;

    OrmInterceptor interceptor;

    public PluginInvocationHandler(Object obj,OrmInterceptor interceptor){
        this.target = obj;
        this.interceptor = interceptor;
    }

    public static Object newInstance(Object obj,OrmInterceptor ormInterceptor){
        Class<?>[] interfaces = getInterfaces(obj.getClass());
        return Proxy.newProxyInstance(PluginInvocationHandler.class.getClassLoader(),interfaces,new PluginInvocationHandler(obj,ormInterceptor));
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method!=null?this.interceptor.plugin(new Invocation(target,method,args)): method.invoke(target,args);
    }

    private static Class<?>[] getInterfaces(Class<?> obj) {
        Set<Class<?>> classSet = new HashSet<>();
        while (obj!=null){
            Class [] classes = obj.getInterfaces();
            for (Class aClass : classes) {
                classSet.add(aClass);
            }
            obj = obj.getSuperclass();
        }
        return (Class<?>[]) classSet.toArray(new Class[classSet.size()]);
    }
}
