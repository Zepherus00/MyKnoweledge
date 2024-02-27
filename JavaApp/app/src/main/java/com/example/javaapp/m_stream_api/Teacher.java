package com.example.javaapp.m_stream_api;

public class Teacher {
    private int age;
    private String name;

    public Teacher(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
