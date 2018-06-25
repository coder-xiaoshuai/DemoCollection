package com.zhangs.javabasicuse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args){
        InvocationHandler handler=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };

        Class clazz= Proxy.getProxyClass(ClassLoader.getSystemClassLoader(),new Class[]{ProxyInterface.class});
        try {
            Constructor constructor=clazz.getConstructor(new Class[]{InvocationHandler.class});
            ProxyInterface proxyInterface= (ProxyInterface) constructor.newInstance(new Object[]{handler});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
