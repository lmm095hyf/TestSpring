package com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: TestSpring
 * @description:  切面类，你要增强的功能在这里
 * @author: 作者 :林木木
 * @create: 2021-04-09 19:34
 */
@Aspect  //说明这是切面类
@Component//注意：要托管给spring
@Order(value = 100)
public class LogAspect {
    //切入点声明
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add(..))")//切入点表达式，哪些方法上加增强 *代表任意修饰符
    private void add(){  //私有化方法，只是为了表明这是一个切入点

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update(..))")//切入点表达式，哪些方法上加增强 *代表任意修饰符
    private void update(){  //私有化方法，只是为了表明这是一个切入点

    }

    @Pointcut("add()|| update()")//?代表0/1
    //modifiers-pattern：修饰符
    private void addAndUpdate(){  //私有化方法，只是为了表明这是一个切入点

    }

    //切入点表达式的语法: ?代表出现0次或一次
    //modifiers-pattern:修饰衔
    //ret-type-pattern:返回类型
    //declaring-type-pattern:
    //name-pattern:名字模型
    //throws-pattern
    //execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)
    //          throws-pattern?)



    @After("com.yc.aspect.LogAspect.addAndUpdate()")
    public void bye(JoinPoint jp) {//spring是一个ioc容器,它可以使用di将程序运行的信息注入 joinpoint
        System.out.println("---------------bye---------------");
        //连接点中的所有信息
        Object target = jp.getTarget();
        System.out.println("目标类为:" + target);

        Object method = jp.getSignature();
        System.out.println("方法:" + method);

        Object[] objs = jp.getArgs();

        if (objs != null) {
            for (Object o : objs) {
                System.out.println("参数:" + o);
            }
        }

        System.out.println("---------------bye---------------");
    }

    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==========compute进入了   增强。。。");
        long start = System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("==========compute退出增强了。。。");
        System.out.println("时长为"+(end-start));
        return retVal;
    }

    @Before("com.yc.aspect.LogAspect.addAndUpdate()")
    public void log(){
        System.out.println("===前置增强的日志===");
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr=sdf.format(d);
        System.out.println("时间"+dstr);
        System.out.println("===增强结束===");
    }

}
