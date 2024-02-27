package com.example.javaapp.h_functionInterface;

public class Runner {
    public static void main(String[] args) {
        Sounding cat = new Cat();
        cat.makeSound();

        Sounding bird = () -> System.out.println("Птица поет");
        bird.makeSound();

        Sounding dog = new Dog();

        Sounding[] zoo = {cat, dog};
        for (Sounding animal: zoo) {
            if (animal instanceof Cat selectedCat) selectedCat.makeSound();
        }
    }
}