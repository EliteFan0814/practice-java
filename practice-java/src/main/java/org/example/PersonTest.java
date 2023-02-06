package org.example;

import org.test.HighStudent;

public class PersonTest {
    public static void main(String[] args) {
        var people = new Person[2];
        people[0] = new Employee("fpc", 6000, 2021, 11, 1);
        people[1] = new Student("syq", "english");
        for (Person item : people) {
            System.out.println(item.getName() + "," + item.getDescription());
        }
        System.out.println(people[1].toString());
    }
}
