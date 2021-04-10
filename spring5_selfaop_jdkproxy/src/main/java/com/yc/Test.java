package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-10 19:31
 */
public class Test {
    public static void main(String[] args) {
        StudentBiz target=new StudentBizImpl();

        LogAspect la=new LogAspect(target);
        Object obj = la.createProxy();
        //System.out.println(obj);

        if (obj instanceof StudentBiz){
            StudentBiz sb= (StudentBiz) obj;
            sb.find("张三");//jvm会
            sb.add("李四");
            sb.update("林木木");
        }
    }
}
