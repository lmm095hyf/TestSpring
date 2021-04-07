package com.yc.biz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-04 15:25
 */
@Component//将这个类交给spring容器托管
@Lazy
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)//多例
//@Named（hw）别名机制，
@Named("hw")
public class HelloWorld {

    //@Lazy//懒加载，需要的时候才使用但是提示depend-on强制加载，取消掉依赖
    public HelloWorld() {
        System.out.println("无参构造方法");
    }

    public void hello(){
        System.out.println("helloworld！！！");
    }
}
