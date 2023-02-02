package org.example;

public abstract class Person {
    protected String type = "Person";
    public abstract String getDescription();

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
