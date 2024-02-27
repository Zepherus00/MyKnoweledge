package com.example.javaapp.g_interfaces;

public interface Alive extends Movable, Eatable {

    @Override
    default void move() {

    }

    @Override
    default void eat() {
        Movable.super.eat();
    }

    @Override
    default void back() {
        Movable.super.back();
    }
}
