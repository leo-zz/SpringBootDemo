package com.proxy.staticproxy;

public class TestStatic {

    /**
     * 静态代理的缺点很明显：一个代理类只能对一个业务接口的实现类进行包装，
     * 如果有多个业务接口的话就要定义很多实现类和代理类才行。
     */
    public static void main(String[] args) {
        HelloService service = new HelloService();
        HelloInterface proxy = new HelloProxy(service);

        proxy.hello();
        proxy.bye();
    }
}
