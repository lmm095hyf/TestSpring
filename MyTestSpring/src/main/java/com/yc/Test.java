package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 11:47
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        HelloWorld helloWorld= (HelloWorld) ac.getBean("hw");
        System.out.println(helloWorld.hashCode());//1670675563

        HelloWorld helloWorld2= (HelloWorld) ac.getBean("hw2");
        System.out.println(helloWorld.hashCode());//1670675563

        helloWorld.show();
    }
}
