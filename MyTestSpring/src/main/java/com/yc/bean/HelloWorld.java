package com.yc.bean;

import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;

import javax.annotation.PreDestroy;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 11:50
 */
@MyComponent
public class HelloWorld {

    @MyPostConstruct
    public void setUp(){
        System.out.println("MyPostConstrucr");
    }

    @PreDestroy
    public void destory(){
        System.out.println("MyPreDestory");
    }

    public HelloWorld(){
        System.out.println("Hello World 构造");
    }

    public void show(){
        System.out.println("show");
    }
}
