package com.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    //这其实业务实现类对象，用来调用具体的业务方法
    private Object target;

    public Object bind(Object target) {
        this.target = target;//接收业务实现类对象参数
        //通过反射机制，创建一个代理类对象实例并返回。用户进行方法调用时使用
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 包装调用方法：进行预处理、调用后处理
     * @param proxy  当前被调用的代理
     * @param method 当前调用的业务方法对象
     * @param args  方法调用传入的参数。
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        System.out.println("before invoke from jdk dynamic proxy");
        result = method.invoke(target, args);
        System.out.println("after invoke from jdk dynamic proxy");
        return result;
    }
}
