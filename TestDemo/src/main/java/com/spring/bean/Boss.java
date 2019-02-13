package com.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boss {

    //Always use constructor based dependency injection in your beans.
    //Always use assertions for mandatory dependencies
    @Autowired
    private Beauty b;

    private Car car;

    public Car getCar() {
        return car;
    }

    @Autowired
    public void setCar( Car car) {
        this.car = car;
    }

    public Beauty getB() {
        return b;
    }

    public void setB(Beauty b) {
        this.b = b;
    }


    @Override
    public String toString() {
        return "Boss{" +
                "b=" + b +
                ", car=" + car +
                '}'+super.toString();
    }
}
