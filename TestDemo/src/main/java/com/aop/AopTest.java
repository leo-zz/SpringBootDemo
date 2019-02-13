package com.aop;


import com.aop.config.MyConfig;
import com.aop.service.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Calculator calc = (Calculator)context.getBean("calc");
        calc.div(1,1);

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String str:beanDefinitionNames
//             ) {
////            System.out.println(str);
//        }

    }
}
