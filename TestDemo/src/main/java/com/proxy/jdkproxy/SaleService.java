package com.proxy.jdkproxy;

public class SaleService implements SaleInterface {

    @Override
    public void sale() {
        System.out.println("SaleService  sale invoke!");
    }

    @Override
    public void buy() {
        System.out.println("SaleService  buy invoke!");
    }
}
