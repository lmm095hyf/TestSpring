package com.yc.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 11:50
 */
@Component
public class HelloWorld {

    @PostConstruct
    public void setUp(){
        System.out.println("PostConstrucr");
    }

    @PreDestroy
    public void destory(){
        System.out.println("PreDestory");
    }

    public HelloWorld(){
        System.out.println("Hello World 构造");
    }

    public void show(){
        System.out.println("show");
    }
}
