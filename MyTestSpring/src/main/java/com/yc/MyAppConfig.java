package com.yc;

import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;
import com.yc.bean.HelloWorld;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 11:47
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao","com.yc.biz","com.yc.bean"})
public class MyAppConfig {

    @MyBean
    public HelloWorld hw(){ //激活方法  method.invoke(My...)
        return new HelloWorld();
    }

    @MyBean
    public HelloWorld hw2(){ //激活方法  method.invoke(My...)
        return new HelloWorld();
    }
}
