package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-04 15:24
 */
@Configuration//说明这个类是一个配置类
@ComponentScan(basePackages = "com.yc")//告诉spring容器需要扫描哪些包
public class AppConfig {

}
