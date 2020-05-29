package com.qc.shangou.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author quincey
 * Date 2020/5/29 11:20
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("spring容器开始创建");
        SpringUtil.applicationContext = applicationContext;
        System.err.println("spring自定义工具类已经完成");

    }


    public static <T> T getBean(String var1, Class<T> var2) {
        return SpringUtil.applicationContext.getBean(var1, var2);
    }
    public static Object getBean(String var1) {
        return SpringUtil.applicationContext.getBean(var1);
    }

    public static <T> T getBean(Class<T> var2) {
        return SpringUtil.applicationContext.getBean(var2);
    }

}
