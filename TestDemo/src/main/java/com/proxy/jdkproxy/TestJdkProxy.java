package com.proxy.jdkproxy;

public class TestJdkProxy {

    /**
     * JDK动态代理的代理对象在创建时，需要使用业务实现类所实现的接口作为参数（因为在后面代理方法时需要根据接口内的方法名进行调用）。
     * 如果业务实现类是没有实现接口而是直接定义业务方法的话，就无法使用JDK动态代理了。
     * 并且，如果业务实现类中新增了接口中没有的方法，这些方法是无法被代理的（因为无法被调用）。
     */
    public static void main(String[] args) {
        ByeInterface byeService = new ByeService();
        SaleService saleService = new SaleService();
        ByeInterface byeProxy = (ByeInterface)new JdkProxy().bind(byeService);
        SaleInterface saleProxy = (SaleInterface)new JdkProxy().bind(saleService);
        byeProxy.bye();
        byeProxy.hello();

        saleProxy.buy();
        saleProxy.sale();

    }
}
