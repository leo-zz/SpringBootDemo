package com.jdk;

import org.junit.Test;

public class TestByCmd {
    final String a=new String("aa"); //存在堆中，不能改变，但是不能边
    final Son s=new Son(1);

    public static void main(String[] args) {
//        TestByCmd T1 = new TestByCmd();
//        TestByCmd T2 = new TestByCmd();
//
//        System.out.println(T1.a==T2.a);
//        T1.s.a=0; //常量必须引用这个对象，但是对象本身的数据可以改变
//        T2.s.a=2;

        for (int i=0;i<10;i++){
            if(i%2==0){
//                continue; //偶数，跳过本次循环，只打印奇数
                if(i>5)
                break;//直接结束所在for循环
            }
            System.out.println(i);//
        }

    }

    static class Son{
        int a;
        public static int age =1;

        public Son(int a) {
            this.a = a;
        }
    }
}
