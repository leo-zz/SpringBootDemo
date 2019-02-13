package com.proxy.cglibproxy;

public class TestCiglib {

    public static void main(String[] args) {
        ByeInterface byeService = new ByeService();
        SaleInterface saleService = new SaleService();

        CglibProxy byeProxy = new CglibProxy();
        CglibProxy saleProxy = new CglibProxy();

        ByeInterface byeProxyInstance = (ByeInterface)byeProxy.getInstance(byeService);
        SaleService saleProxyInstance = (SaleService)saleProxy.getInstance(saleService);

        byeProxyInstance.bye();
        byeProxyInstance.hello();

        saleProxyInstance.buy();
        saleProxyInstance.sale();

    }

}
