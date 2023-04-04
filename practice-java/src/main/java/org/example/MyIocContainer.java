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
    public static Map<String, Object> initBeans;

    public static void init() {
        Properties properties = new Properties();
        try {
            // 加载Bean配置文件（类映射文件）
            properties.load(MyIocContainer.class.getResourceAsStream("/ioc.properties"));
            System.out.println(properties);
            Map<String, Object> beans = new HashMap<>();
            // properties：{orderDao=org.example.OrderDao, orderService=org.example.OrderService}
            // 实例化配置文件中所有的 Bean 并放到Map集合 beans 中
            properties.forEach((beanName, fullyQualifiedClassName) -> {
                try {
                    Class klass = Class.forName((String) fullyQualifiedClassName);
                    Object beanInstance = klass.getConstructor().newInstance();
                    beans.put((String) beanName, beanInstance);
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            });
            // 遍历beans中的实例化对象，使用反射获取对应实例的依赖
            beans.forEach((beanName, beanInstance) -> {
                dependencyInject(beanName, beanInstance, beans);
            });
            initBeans = beans;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dependencyInject(String beanName, Object beanInstance, Map<String, Object> beans) {
        // 获取有Autowired注解的字段
        List<Field> fieldsTobeAutowired = Stream.of(beanInstance.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .collect(Collectors.toList());
        fieldsTobeAutowired.forEach(field -> {
            try {
                String fieldName = field.getName();
                // 在已经实例化所有所需要的bean对象的beans中查找依赖的那个实例对象
                Object depencyBeanInstance = beans.get(fieldName);
                field.setAccessible(true);
                // 将当前对象所依赖的对象注入
                field.set(beanInstance, depencyBeanInstance);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        // 执行
        init();
        OrderService orderService = (OrderService) initBeans.get("orderService");
        OrderDao orderDao = (OrderDao) initBeans.get("orderDao");
    }
}
