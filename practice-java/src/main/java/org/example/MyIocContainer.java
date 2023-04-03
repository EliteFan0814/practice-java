package org.example;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyIocContainer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(MyIocContainer.class.getResourceAsStream("/ioc.properties"));
            System.out.println(properties);
            Map<String, Object> beans = new HashMap<>();
            properties.forEach((beanName, beanClass) -> {
                try {
                    Class klass = Class.forName((String) beanClass);
                    Object beanInstance = klass.getConstructor().newInstance();
                    beans.put((String) beanName, beanInstance);
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            });
            beans.forEach((beanName, beanInstance) -> {
                dependencyinject(beanName, beanInstance, beans);
            });
            OrderService orderService = (OrderService) beans.get("orderService");
            OrderDao orderDao = (OrderDao) beans.get("orderDao");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dependencyinject(String beanName, Object beanInstance, Map<String, Object> beans) {
        List<Field> fieldsTobeAutowired = Stream.of(beanInstance.getClass()
                        .getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .collect(Collectors.toList());
        fieldsTobeAutowired.forEach(field -> {
            try {
                String fieldName = field.getName();
                Object depencyBeanInstance = beans.get(fieldName);
                field.setAccessible(true);
                field.set(beanInstance, depencyBeanInstance);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
