package com.example.javaapp.g_interfaces;

public class Runner {
    public static void main(String[] args) {
        Car car = new Car();
        Dog dog = new Dog();
        car.move();
        dog.move();

        Movable[] movable = new Movable[2];
        movable[0] = car;
        movable[1] = dog;
        for (Movable move: movable) {
            move.move();
        }
    }
}
