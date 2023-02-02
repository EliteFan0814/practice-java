package org.example;

public class Student extends Person {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in" + major;
    }

    public void getGrade() {
        System.out.println("xxxxxx");
    }
}
