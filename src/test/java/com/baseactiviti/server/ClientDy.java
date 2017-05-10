package com.baseactiviti.server;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ClientDy implements InvocationHandler {
  private Object sub;

  public ClientDy(Object obj) {
    this.sub = obj;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before calling: " + method);
    // 调用真实对象中的要执行的方法，只不过这里是通过反射来调用
    method.invoke(sub, args);
    System.out.println(args == null);
    System.out.println("after calling: " + method);
    return null;
  }
}

class RealSubject implements Subject {
  public void request() {
    System.out.println("From real subject.");
  }
}

interface Subject {
  public void request();
}