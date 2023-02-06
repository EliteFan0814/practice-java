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

    @Override
    public String toString() {
        return this.getClass().getName() +
                " {type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
