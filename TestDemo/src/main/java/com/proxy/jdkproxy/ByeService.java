package com.proxy.jdkproxy;

import com.proxy.staticproxy.HelloInterface;

/**
 * 业务实现类
 */
public class ByeService implements ByeInterface {

    public void hello(){
        System.out.println("ByeService  hello invoke!");
    }

    @Override
    public void bye() {
        System.out.println("ByeService  bye invoke!");
    }
}
