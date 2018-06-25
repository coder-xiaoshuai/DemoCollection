package com.zhangs.javabasicuse;

public class Client {

    public static void main(String[] args) {

        Subject proxy = DynProxyFactory.getInstance();
        proxy.dealTask("DBQueryTask");
    }

}
