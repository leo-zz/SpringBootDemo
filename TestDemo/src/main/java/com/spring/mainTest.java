package com.spring;

import com.spring.bean.Beauty;
import com.spring.bean.Boss;
import com.spring.bean.Car;
import com.spring.config.Config2;
import com.spring.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mainTest {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config2.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name:names
             ) {
            System.out.println(name);
        }

        System.out.println("++++++++++++++");

        Car car = (Car)context.getBean("car");
        Boss boss = (Boss)context.getBean("boss");
        Beauty beauty = (Beauty)context.getBean("beauty");

        System.out.println("car: "+car);
        System.out.println("boss: "+boss);
        System.out.println("beauty: "+beauty);
    }
}
