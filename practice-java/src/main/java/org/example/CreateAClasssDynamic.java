package org.example;

import java.lang.reflect.InvocationTargetException;

public class CreateAClasssDynamic {
    private String className;
    private String methodName;
    public CreateAClasssDynamic(String className,String methodName){
        this.className = className;
        this.methodName = methodName;
    }
    public void createInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class c = Class.forName(this.className);
        Object o = c.getConstructor().newInstance();
        Object res = o.getClass().getMethod(this.methodName).invoke(o);
        System.out.println(res);
    }
}
