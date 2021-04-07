package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-05 14:30
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Documented
public @interface MyComponentScan {
    String[] basePackages() default {};
}
