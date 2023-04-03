package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    interface UserCondition {
        boolean conditon(User user);
    }

    //判断是否姓王
    static class WangCondition implements UserCondition {
        @Override
        public boolean conditon(User user) {
            return user.getName().startsWith("王");
        }
    }

    //判断是否姓李
    static class LiCondition implements Predicate<User> {
        @Override
        public boolean test(User user) {
            return user.getName().startsWith("李");
        }
    }

    public static List<User> filter(List<User> users) {
        WangCondition wangCd = new WangCondition();
        List<User> result = new ArrayList<>();
        for (User user :
                users) {
            if (wangCd.conditon(user)) {
                result.add(user);
            }
        }
        return result;
    }
}
