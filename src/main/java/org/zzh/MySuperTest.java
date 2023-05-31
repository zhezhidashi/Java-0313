package org.zzh;

class A{
//    public void f(){
//        System.out.println("A.f()");
//    }
}

public class MySuperTest extends A{
    public void f(){
        System.out.println("MySuperTest.f()");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> c = Class.forName("org.zzh.MySuperTest");
        A a = (A) c.newInstance();

    }
}
