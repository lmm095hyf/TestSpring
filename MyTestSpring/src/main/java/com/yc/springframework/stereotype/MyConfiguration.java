package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 14:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyConfiguration {
    String value() default "";
}
