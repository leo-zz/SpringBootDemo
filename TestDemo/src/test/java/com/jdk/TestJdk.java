package com.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class TestJdk {

    public static String staticField = "静态变量";
    static {
        System.out.println("静态语句块，在类实例化之前执行，只执行一次");
    }
    public String field = "实例变量";


    public TestJdk() {
        System.out.println("执行构造函数");
        System.out.println("实例化TestJdk,this"+this.hashCode());
    }
    {
        System.out.println("普通语句块,在构造函数执行前执行，实例化几个类，执行几次");
    }

    int j;
    public static int returnInt(){
        TestJdk testJdk = new TestJdk();
        testJdk.j=5;
        return testJdk.j;
    }

    public static void main(String[] args) {
//        System.out.println("main方法");
//        TestJdk jdk = new TestJdk();
//        A b1 = jdk.new C(); //在创建内部类时，会先创建一个外部类
//        A b2 = jdk.new C();

        String a="a";
        a.equals("a");
//        System.out.println(returnInt());
//        System.out.println(A.a);

    }

//    @Test
    public void test1() throws CloneNotSupportedException {
//        test int
//        Integer x = new Integer(123);
//        Integer y = new Integer(123);
//        System.out.println(x == y);    // false
//        Integer z = Integer.valueOf(123); //IntegerCache
//        Integer k = Integer.valueOf(123);
//        System.out.println(z == k);   // true
//        Integer a = 123;
//        Integer b = 123;
//        System.out.println(a == b);   // true
//
//        Integer c = 1231;
//        Integer d = 1231;
//        System.out.println(c == d);   // false

//        test String
//        String a=new String("abc");
//        String b="abc";
//        String c=a.intern();
//        System.out.println(a == b);//false
//        System.out.println(b == c);//true


//        test short
//        Short x = new Short((short)123);
//        Short y = new Short((short)123);
//        System.out.println(x == y);    // false
//        Short z = Short.valueOf((short)123); //ShortCache
//        Short k = Short.valueOf((short)123);
//        System.out.println(z == k);   // true
//        Short a = 123;
//        Short b = 123;
//        System.out.println(a == b);   // true
//
//        Boolean.valueOf("");
//        String.valueOf(1);   //
//
//        Byte.valueOf("a");  //ByteCache
//        Character.valueOf('a'); //CharacterCache

//

//

//        String s1 = new String("aaa");
//        String s2 = new String("aaa");
//        System.out.println(s1 == s2);           // false
//        String s3 = s1.intern();
//        String s4 = s1.intern();
//        System.out.println(s3 == s4);           // true
//        System.out.println(s1 == s4);           // false s1指向堆中的对象  s4指向字符串常量池中的元素

//        TestJdk testJdk = new TestJdk();
//        B b = testJdk.new B();
//        b.b=1;
////        b.func1();                  //接口中可以存在默认的方法实现
////        System.out.println(a.a);    //接口中的字段是static final的。存放在方法区
//        B c = (B)b.clone();
//        System.out.println(c.b);
//        System.out.println(b==c);
//        System.out.println(c.equals(b));
//        System.out.println(c);
//        System.out.println(b);
//
//        HashSet<B> bs = new HashSet<>();
//        bs.add(b);
//        bs.add(c);
//        System.out.println(bs.size()); //只有 hashCode()返回的散列值相同且 equals()方法返回true，set才会认为这是同样的对象。

//        TestJdk testJdk = new TestJdk();
//        B b1 = testJdk.new B();
//        B b2 = b1.clone();
//        System.out.println(b1+ " xxxxxxxxxx "+b2);
//        System.out.println(b1.t+ " xxxxxxxxxx "+b2.t); //浅拷贝时，拷贝类实例中的引用类型的属性引用的是用一个属性
//
//        b1.t.j=3;
//        System.out.println(b1.t.j);
//        System.out.println(b2.t.j); //深拷贝必须自己实现，这样拷贝对象中的引用类型的属性才不会共用


//        System.out.println(this.j);
//
//        System.out.println("开始执行main方法");
//        TestJdk testJdk = new TestJdk();
//        System.out.println("初始化完毕");

//        A b1 = new C();
//        A b2= new C();

        try {
            int a=1/0;
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    interface  A{
        int a=1;
        default void func1(){
            System.out.println("aa");
        }
        void func2();

        interface A1{
            public static void hasValue(){

            }
        }
    }

    class B implements A ,Cloneable{ //被final修饰的类不能够被继承

        TestJdk t =new TestJdk();
        int b ;

        public B(int b) {
            this.b=b;
            System.out.println("使用构造方法实例化B对象。");
            System.out.println("实例化B,this"+this.hashCode());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        @Override
        public B clone() throws CloneNotSupportedException {
//            return (B)super.clone(); //浅拷贝  在堆中创建相同的对象，互补影响
            //深拷贝需要自己实现
            B clone = (B) super.clone();
            TestJdk jdk = new TestJdk();
            jdk.j=clone.t.j;
            clone.t=jdk;
            return clone;

        }

        @Override
        public boolean equals(Object obj) {
//            return  super.equals(obj);
            return obj==this || (obj !=null && (obj.getClass().getName().equals(this.getClass().getName())) && ((B)obj).b==this.b);
        }

       @Override
       public void func2() {
           System.out.println("实例化B,this"+this.hashCode());
       }

//        @Override
//        public int hashCode() {
////            return  new Random().nextInt();
//            return  this.b;
//        }
    }

    class C extends B{

        public C() {
            super(1);//父类没有无参的构造方法，因此子类的构造方法中必须要使用super显示的指出使用父类那个构造方法。
            System.out.println("实例化C,this"+this.hashCode()+"  super:"+super.hashCode());
        }
    }


    @Test
    public void test(){
//        String[] a=new String[]{"a1","a2"};
//        Object[] a1=new Object[]{"a1","a2"};
//        System.out.println(a.getClass());
//        System.out.println(a.getClass()!= Object[].class);//比较时报错，不可比较的类型
//        System.out.println(a1.getClass()!= Object[].class);
//
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);

        Integer[] array = new Integer[5];
        //System.arraycopy()是native方法
        arrayList.toArray(array);//数组类型不能强制转换，执行时会报错。
        for (int i:array
             ) {
            System.out.println(i);
        }


//        Integer[] array = (Integer[])arrayList.toArray();//数组类型不能强制转换，执行时会报错。
        System.out.println(array);

//        //对于Foreach语句，
//        //对于数组，foreach按顺序从数组的第一个元素遍历到最后一个元素。
//        //对于Iterable容器，则依照迭代器的遍历顺序。
//        for (String s:list) {
//            System.out.println(s);
//        }
//        //对于ArrayList，其Iterable遍历的顺序就是数组的下标的增序
//        Iterator<String> iterator = list.iterator();
//        String next = iterator.next();

//        try {
//            int a=3/0;
//        }finally {
//            System.out.println("error");
//        }//抛出异常前会执行finally中的方法


    }

}
