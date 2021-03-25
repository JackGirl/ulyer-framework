package cn.ulyer.demo;

import cn.ulyer.demo.proxy.*;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProxyTest {



    @Test
    public void test(){

        Excutor excutor = new SimpleExecutor();

        excutor = (Excutor) createProxy(excutor);

        excutor.execute("aaaa");




    }



    public  Object createProxy(Object target){
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new PageInterceptor());
        interceptors.add(new OtherInterceptor());
        Interceptor interceptor;
        for(Iterator var2 = interceptors.iterator(); var2.hasNext(); target = Plugin.wrap(target,interceptor)) {
            interceptor = (Interceptor)var2.next();
        }
        return target;
    }







}
