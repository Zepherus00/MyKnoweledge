package com.example.javaapp.d_polymorphism;

public class Runner {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        feedTwoAnimals(
                dog, cat
        );
    }

    public static void feedTwoAnimals(Animal animal1, Animal animal2) {
        animal1.eatAnimals();
        animal2.eatAnimals();
    }
}
