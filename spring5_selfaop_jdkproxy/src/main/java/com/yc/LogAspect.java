package com.yc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-10 19:21
 */
public class LogAspect implements InvocationHandler {

    private Object target;

    public LogAspect(Object target) {
        this.target = target;
    }

    public Object createProxy(){
        //新建一个代理实例
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),this);
    }
    @Override//回调方法，当JVM调用代理对象的方法的时候，会有jvm自动调用这个invoke
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类对象"+proxy.getClass());
        System.out.println("目标类的方法"+method);
        System.out.println("方法中的参数"+args);

        if (method.getName().equals("add")){//转换成为切入点表达式->AspectJ的切入点表达式
            log();
        }
        //前置增强
        Object ob=method.invoke(this.target,args);
        //后置增强
        return ob;
    }

    public void log(){
        System.out.println("======前置增强======");
        System.out.println("hello，this is"+new Date());
        System.out.println("============");


    }


}
