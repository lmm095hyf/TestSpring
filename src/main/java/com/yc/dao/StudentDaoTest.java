package com.yc.dao;

import com.yc.AppConfig;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class StudentDaoTest extends TestCase {

    private StudentDao studentDao;
    private ApplicationContext ac;

    @Override
    public void setUp(){
        //1.能否完成自动装配
//        studentDao = new StudentDaoJpaImpl();
//
//        studentBizImpl = new StudentBizImpl();
//
//        studentBizImpl.setStudentDao(studentDao);
        ac = new AnnotationConfigApplicationContext(AppConfig.class);

        studentDao = (StudentDao) ac.getBean("studentDaoMybatisImpl");
    }


    public void testadd() {
        studentDao.add("张三");
    }


    public void testupdate() {
        studentDao.update("张三");
    }

    public void testBizAdd(){
        studentDao.add("张三");
    }
}