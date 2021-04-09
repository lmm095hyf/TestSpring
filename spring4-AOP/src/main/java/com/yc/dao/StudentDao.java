package com.yc.dao;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-04 14:45
 */

public interface StudentDao {

    public int add(String name);

    public void update(String name);

    public String find(String name);
}
