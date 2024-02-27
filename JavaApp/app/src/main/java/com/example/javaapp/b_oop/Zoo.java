package com.example.javaapp.b_oop;

public class Zoo {
    private Cat cat;
    private Dog dog;

    public Zoo(Cat newCat, Dog newDog) {
        this.dog = newDog;
        this.cat = newCat;
    }

    private void feed() {
        cat.feed();
        dog.feed();
    }
}
