package com.leo;

import org.junit.Test;

public class ChildClass extends FatherClass {

    //即使不写super();但是也会执行父类的构造函数
    public ChildClass() {
    }

//    @Test
    public static  void main(String[] args){
        ChildClass aClass = new ChildClass();
        Child2Class bClass = aClass.new Child2Class();

        System.out.println("aClass:"+aClass.skills);
        System.out.println("bClass:"+bClass.skills);
    }

    private  class Child2Class extends FatherClass{
        public Child2Class() {
            super();
        }
    }
}
