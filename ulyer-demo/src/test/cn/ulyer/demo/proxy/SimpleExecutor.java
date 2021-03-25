package cn.ulyer.demo.proxy;

public class SimpleExecutor implements Excutor{
    @Override
    public Object execute(String a) {

        System.out.println(a);
        return a;
    }
}
