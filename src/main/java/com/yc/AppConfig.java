package com.yc;

import com.yc.biz.StudentBizImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-04 15:33
 */
@Configuration//声明这是一个配置类
@ComponentScan(basePackages = "com.yc")//告诉spring容器需要扫面哪些类
public class AppConfig {

    @Bean
    public StudentBizImpl studentBiz(){
        return new StudentBizImpl();
    }


}
