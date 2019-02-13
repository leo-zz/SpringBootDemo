package com.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloProxy implements MethodInterceptor {

    public HelloProxy() {
        System.out.println("create a new Proxy");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoke before！");
        Object obj = methodProxy.invoke(o, objects);
        System.out.println("invoke after!");
        return obj;
    }
}
