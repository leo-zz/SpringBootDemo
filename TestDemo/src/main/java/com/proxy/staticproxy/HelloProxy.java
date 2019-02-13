package com.proxy.staticproxy;

/**
 *  业务代理类：通过组合在代理类中创建一个业务实现类对象来调用具体的业务方法；
 *  通过实现业务逻辑接口，来统一业务方法；
 *
 */
public class HelloProxy implements HelloInterface{

    private HelloInterface service;

    //如果不指定修饰符，默认是package-private
    HelloProxy(HelloInterface service) {
        this.service = service;
    }

    @Override
    public void hello() {
        System.out.println("before invoke from static proxy");
        service.hello();
        System.out.println("after invoke from static proxy");
    }

    @Override
    public void bye() {
        System.out.println("before invoke from static proxy");
        service.bye();
        System.out.println("after invoke from static proxy");
    }
}

