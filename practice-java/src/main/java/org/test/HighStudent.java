package org.test;

import org.example.Person;

public class HighStudent extends Person {
    public HighStudent(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "这是test包中的实例";
    }
}
