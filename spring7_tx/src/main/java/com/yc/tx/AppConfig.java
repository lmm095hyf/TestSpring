package com.yc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @program: TestSpring
 * @description:
 * @author: 作者 :林木木
 * @create: 2021-04-14 19:29
 */
@Configuration
@ComponentScan(basePackages = {"com.yc"})
public class AppConfig {
    //手动创建数据连接池的数据源
    @Bean//将连接池交给spring托管
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        DataSource ds=new ComboPooledDataSource();
        //因为子类没有重写父类的方法，所以获取不到
        ((ComboPooledDataSource)ds).setDriverClass("com.mysql.cj.jdbc.Driver");
        //如果MySQL的版本较高，如果>=5.5，则需要修改版本
        ((ComboPooledDataSource)ds).setJdbcUrl("jdbc:mysql://localhost:3306/testBank");
        ((ComboPooledDataSource)ds).setUser("root");
        ((ComboPooledDataSource)ds).setPassword("root");
        return (ComboPooledDataSource) ds;
    }
}
