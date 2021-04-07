package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
@DependsOn
public class HelloWorldTest {

    private ApplicationContext ac;

    @Autowired
    HelloWorld hw;

    @Before
    public void setUp() throws Exception {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    public void hello() {
        // 默认的bean是首字母小写，其他不改变
//        HelloWorld helloWorld= (HelloWorld) ac.getBean("helloWorld");
//        helloWorld.hello();
//
//        HelloWorld helloWorld2= (HelloWorld) ac.getBean("helloWorld");
//        helloWorld2.hello();

        System.out.println("1111");
        //spring是单例

    }
}