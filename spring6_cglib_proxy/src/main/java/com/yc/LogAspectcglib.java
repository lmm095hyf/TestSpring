package com.yc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-10 19:21
 */
public class LogAspectcglib implements MethodInterceptor {
    private Object target;


    public LogAspectcglib(Object target) {
        this.target = target;
    }

    public Object createProxy(){
        Enhancer enhancer=new Enhancer();

        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        //新建一个代理实例
        return enhancer.create();
    }

    public void log(){
        System.out.println("======前置增强======");
        System.out.println("hello，this is"+new Date());
        System.out.println("============");


    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().startsWith("add")){
            log();
        }
        Object returnValue=method.invoke(this.target,objects);

        return returnValue;

    }
}
