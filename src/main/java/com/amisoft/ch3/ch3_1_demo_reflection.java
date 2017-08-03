package com.amisoft.ch3;

import java.lang.reflect.*;

public class ch3_1_demo_reflection {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        Class cls = Car.class;
        cls.newInstance();

        Class superClass = cls.getSuperclass();
        System.out.println(superClass.getName());
        System.out.println(superClass.getSuperclass().getName());

        System.out.println("Constructors .....");
        System.out.println();

        showConstructor(cls);

        Method[] methods = cls.getMethods();

        showMethods(cls, methods);

        Field field = cls.getDeclaredField("people");
        Type t = field.getType();
        System.out.println(t.getTypeName());


    }

    private static void showMethods(Class cls, Method[] methods) {
        System.out.println();
        System.out.println("Show all class");
        System.out.println();
        showClass(methods);

        System.out.println("Show declared methods");
        System.out.println();
        Method[] declaredMethods = cls.getDeclaredMethods();
        showClass(declaredMethods);
    }

    private static void showConstructor(Class cls) {
        Constructor[] contrArr = cls.getDeclaredConstructors();
        for(Constructor contr : contrArr){
            System.out.printf("%s",contr.getName());
            System.out.printf("(%d)",contr.getParameterCount());
            System.out.println();

            Parameter[] parameters = contr.getParameters();
            for (Parameter p : parameters){
                System.out.printf("%s, %s", p.getName(), p.getType());
            }
            System.out.println();
        }
    }


    private static void showClass(Method[] methods) {
        for (Method method : methods) {
            System.out.printf("%s", method.getName());
            int count = method.getParameterCount();
            System.out.printf("%d)", count);
            System.out.println();

        }
        System.out.println();
    }
}
