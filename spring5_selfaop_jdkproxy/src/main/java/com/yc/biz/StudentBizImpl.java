package com.yc.biz;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-10 19:20
 */
public class StudentBizImpl implements StudentBiz{
    @Override
    public int add(String name) {
        System.out.println("add:"+name);
        return 100;
    }

    @Override
    public void update(String name) {
        System.out.println("update"+name);
    }

    @Override
    public String find(String name) {
        System.out.println("find"+name);
        return name;
    }
}
