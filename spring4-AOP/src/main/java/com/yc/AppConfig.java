package com.yc;

import com.yc.bean.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 11:47
 */
@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableAspectJAutoProxy//启用AspectJ支持
public class AppConfig {

    @Bean
    public HelloWorld hw(){ //激活方法  method.invoke(My...)
        return new HelloWorld();
    }

    @Bean
    public HelloWorld hw2(){ //激活方法  method.invoke(My...)
        return new HelloWorld();
    }
}
