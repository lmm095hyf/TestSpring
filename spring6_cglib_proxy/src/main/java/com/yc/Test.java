package com.yc;

import com.yc.biz.StudentBizImpl;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-10 19:31
 */
public class Test {
    public static void main(String[] args) {
        StudentBizImpl sbi=new StudentBizImpl();

        LogAspectcglib lc=new LogAspectcglib(sbi);

        Object obj=lc.createProxy();
        System.out.println(obj);
        if (obj instanceof StudentBizImpl){
            StudentBizImpl s= (StudentBizImpl) obj;

            s.find("张三");
            s.update("李四");
            s.add("林木木");
        }
    }
}
