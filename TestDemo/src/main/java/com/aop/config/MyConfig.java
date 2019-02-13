package com.aop.config;

import com.aop.service.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan("com.aop.aspect")
@EnableAspectJAutoProxy
public class MyConfig {

    @Bean("calc")
    public Calculator CreateCalcutor(){
        return new Calculator();
    }
}
