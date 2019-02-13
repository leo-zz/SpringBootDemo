package com.spring.config;

import com.spring.bean.Car;
import com.spring.bean.Oil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.spring.bean")
public class Config2 {

    @Bean("car")
    @Scope("prototype")
    public Car getCar(Oil oil){
        Car car = new Car();
        car.setO(oil);
        return car;
    }

}
