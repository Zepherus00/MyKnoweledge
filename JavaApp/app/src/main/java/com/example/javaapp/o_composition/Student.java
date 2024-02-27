package com.example.javaapp.o_composition;

public class Student {
    private int sum;
    private People people;

    public Student(People people) {
        this.people = people;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getName() {
        return people.getName();
    }

    public void setName(String name) {
        people.setName(name);
    }
}
