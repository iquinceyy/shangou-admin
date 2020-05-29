package com.qc.shangou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.qc.shangou.dao"})//扫描包路径
@EnableScheduling //开启定时事务
@EnableTransactionManagement //开启事务控制
public class ShangouWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangouWebApplication.class, args);
    }

}
