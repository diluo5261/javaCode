package com.dilo.ch01_project01;

import com.dilo.ch01_project01.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Ch01Project01ApplicationTests {

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person.toString());
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void testLog(){
        /*

        测试日志输出,
        SLF4J 日志输出级别(从小到达):trace < debug < info < warn < error  指挥输出比当前级别更高的日志
        日志级别是可以调整的
         */

        //springboot中,默认级别就是 info 级别
        logger.trace("trace日志...");
        logger.debug("debug日志...");
        logger.info("info日志...");
        logger.warn("warn日志...");
        logger.error("error日志...");



    }
}
