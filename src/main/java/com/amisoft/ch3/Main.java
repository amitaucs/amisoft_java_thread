package com.amisoft.ch3;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Class cls = Car.class;

        Car car = (Car) cls.newInstance();

        Field field = cls.getDeclaredField("people");

        Array.set(field.get(car), 1, "Kevin");

        Object obj = Array.get(field.get(car), 1);

        System.out.println(obj);

        Class clazz = String[].class;
        clazz = Class.forName("[Ljava.lang.String;");
        System.out.println(clazz.getName());
    }

    private static void Fields(Class cls, Car car) throws NoSuchFieldException, IllegalAccessException {
        Field f = cls.getDeclaredField("speed");
        Object obj = f.get(car);
        System.out.println(obj);

        car.drive('D', 33);

        obj = f.get(car);
        System.out.println(obj);

        f.set(car, 44);
        obj = f.get(car);
        System.out.println(obj);
    }

    private static void invokingMethods(Class cls) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Constructor[] ctors = cls.getDeclaredConstructors();
        Car car = (Car) ctors[1].newInstance(4);
        Method method = cls.getDeclaredMethod("drive", char.class, int.class);
        method.invoke(car, 'D', 6);

        method = cls.getDeclaredMethod("print");
        method.invoke(null);

        method = cls.getDeclaredMethod("accelerate");
        method.setAccessible(true);
        method.invoke(car);
    }

    private static void lookingAtArrays(Class cls) throws NoSuchFieldException {
        Field field = cls.getDeclaredField("people");
        Class t = field.getType();
        System.out.println(t.getTypeName());
        System.out.println(t.isArray());
    }

    private static void showFields(Class cls) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            System.out.printf("%s (%s)\r\n", field.getName(), field.getType());
        }
    }

    private static void methodParameters(Class cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method: methods){
            System.out.printf("%s", method.getName());
            int count = method.getParameterCount();
            System.out.printf(" (%d) ", count);
            Parameter[] parameters = method.getParameters();
            for (Parameter p : parameters){
                System.out.printf("%s, %s ", p.getName(), p.getType());
            }
            System.out.println();
        }
    }

    private static void listCtorParameters(Class cls) {
        Constructor[] ctors = cls.getDeclaredConstructors();
        for (Constructor c : ctors){
            System.out.printf("%s ", c.getName());
            System.out.printf(" (%d)", c.getParameterCount());
            Parameter[] parameters = c.getParameters();
            for (Parameter p : parameters){
                System.out.printf("%s, %s", p.getName(), p.getType());
            }
            System.out.println();
        }
    }

    private static void superClasses(Class cls) {
        Class superClass = cls.getSuperclass();
        while(superClass != null){
            System.out.println(superClass.getName());
            superClass = superClass.getSuperclass();
        }
    }


    private static void showMethods(Class cls) {
        Method[] methods = cls.getMethods();
        for (Method method: methods){
            System.out.printf("%s", method.getName());
            int count = method.getParameterCount();
            System.out.printf(" (%d) ", count);
            System.out.println();
        }
        System.out.println();
        methods = cls.getDeclaredMethods();
        for (Method method: methods){
            System.out.printf("%s", method.getName());
            int count = method.getParameterCount();
            System.out.printf(" (%d) ", count);
            System.out.println();
        }
    }
}
