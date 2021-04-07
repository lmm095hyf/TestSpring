package com.yc.springframework.stereotype;

import java.lang.annotation.*;
import java.lang.annotation.*;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 14:25
 */
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {

    boolean required() default true;
}
