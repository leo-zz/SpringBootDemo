//package com.leo;
//
//import org.junit.Test;
//
//import java.util.Set;
//
//import static com.leo.JDKTest.ResourceClass.Resource1;
//import static com.leo.JDKTest.ResourceClass.Resource2;
//
//public class JDKTest {
//
//    /**
//     * TestThreadLoca
//     * ThreadLocal用于保存某个线程共享变量：
//     * 对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。
//     */
//    public static void main(String[] args) {
//        Getter getter = new Getter();
//        Setter setter = new Setter();
//
//        for (int i = 0; i < 15; i++) {
//            String s1 = "线程-" + i;
//            String s2 = " value = (" + i + ")";
//            new Thread() {
//                @Override
//                public void run() {
//
//                    try {
//                        setter.setOne(s1);
//                        setter.setTwo(s2);
//                        getter.display();
//                    } finally {
//                        Resource1.remove();
//                        Resource2.remove();
//                    }
//
//                }
//            }.start();
//        }
//
//
//    }
//
//    //非static的内部类不能有static属性
//    static class ResourceClass {
//        static final ThreadLocal<String> Resource1 = new ThreadLocal<>();
//        static final ThreadLocal<String> Resource2 = new ThreadLocal<>();
//    }
//
//    static class Setter {
//         void setOne(String value) {
//            Resource1.set(value);
//        }
//
//         void setTwo(String value) {
//            ResourceClass.Resource2.set(value);
//        }
//    }
//
//    static class Getter {
//
//         void display() {
//            System.out.println(Resource1.get() + ":" + Resource2.get());
//
//        }
//
//    }
//}
