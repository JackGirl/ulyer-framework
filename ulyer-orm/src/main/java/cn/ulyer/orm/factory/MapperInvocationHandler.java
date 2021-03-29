package cn.ulyer.orm.factory;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.excutor.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperInvocationHandler  implements InvocationHandler {

    OrmFactory ormFactory;

    private Class<?> mapperClass;

    public MapperInvocationHandler(OrmFactory ormFactory,Class<?> mapperClass){
        this.ormFactory = ormFactory;
        this.mapperClass = mapperClass;
    }


    public static Object instance(OrmFactory factory,Class<?> mapperClass){
        Assert.notNull(factory);
        if(!mapperClass.isInterface()){
            throw new RuntimeException("mapper is not interface cannot proxy class:"+mapperClass.getName());
        }
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),new Class[]{mapperClass},new MapperInvocationHandler(factory,mapperClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = ormFactory.createSqlSession();
        Object result = sqlSession.execute(mapperClass.getName()+"."+method.getName(),args);
        return result;
    }



}
