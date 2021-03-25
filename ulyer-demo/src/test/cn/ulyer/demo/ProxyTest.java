package cn.ulyer.demo;

import cn.ulyer.demo.proxy.*;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProxyTest {





    @Test
    public void test(){

        SimpleExecutor excutor = new SimpleExecutor();

       // excutor = (Excutor) createProxy(excutor);
        Invo invo = new Invo();

        Excutor e = (Excutor) invo.newInstance(excutor,invo);

        String b = (String) e.execute("aaaa");




    }



    public  Object createProxy(Object target){
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new PageInterceptor());
        interceptors.add(new OtherInterceptor());
        Interceptor interceptor;
        for(Iterator var2 = interceptors.iterator(); var2.hasNext();) {
            interceptor = (Interceptor)var2.next();
            target = Plugin.wrap(target,interceptor);

        }
    return target;
    }









}
