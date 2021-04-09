package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
public class StudentBizImplTest {

    @Resource(name="studentBizImpl")
    //@Autowired
    private StudentBizImpl sbi;

    @Test
    public void add() {
        sbi.add("张三");
    }

    @Test
    public void update() {
        sbi.update("张三");
    }

    @Test
    public void find() {
        sbi.find("张三");
    }
}