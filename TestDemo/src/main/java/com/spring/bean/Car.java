package com.spring.bean;



public class Car {
    Oil o;

    public Oil getO() {
        return o;
    }

    public void setO(Oil o) {
        this.o = o;
    }

    @Override
    public String toString() {
        return "Car{" +
                "o=" + o +
                '}'+super.toString();
    }
}
