package com.example.javaapp.g_interfaces;

public interface Movable {
    void move();

    default void eat() {

    }

    default void back() {
        System.out.println("ВОт так обозначается дефолтное поведение");
    }
}
