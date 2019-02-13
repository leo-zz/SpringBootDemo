package com.proxy.staticproxy;

/**
 * 业务实现类
 */
public class HelloService implements HelloInterface {

    public void hello(){
        System.out.println("ByeService  hello invoke!");
    }

    @Override
    public void bye() {
        System.out.println("ByeService  bye invoke!");
    }
}
