package cn.ulyer.orm.utils;

import cn.hutool.core.exceptions.ExceptionUtil;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:50 2021/3/20
 */
public class LogUtils {


    private LogUtils(){}

    public static void error(Exception e){
        System.out.println("error stacktrace:"+ ExceptionUtil.stacktraceToString(e));
    }

    public static void error(Exception e,String msg){
        System.out.println("error msg: "+msg+"error stacktrace:"+ ExceptionUtil.stacktraceToString(e));
    }

    public static void info(Object msg){
        System.out.println(msg==null?"null":msg.toString());
    }



}
