package edu.hw10.task1test;

import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class POJO {

    @NotNull private String firstName;
    @NotNull private String lastName;
    @Min(minVal = 20) private int age;

    public POJO() {
    }

    public POJO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
