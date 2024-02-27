package com.example.javaapp.i_anonymous_class;

public class Runner {
    public static void main(String[] args) {
        Alive dog = new Alive() {
            @Override
            public void eat() {

            }

            @Override
            public void breath() {

            }
        };
        //Это анонимный класс, у него только интерфейс или абстрактный класс

        dog.breath();
        dog.eat();
    }
}
