package com.example.javaapp.b_oop;

public class Car {
    private String brand;
    public static int countCars = 0;
    public final static String BMW_INFO = "";
    public final static String AUDI_INFO = "";
    public final static String KIA_INFO = "";
    //Константы
    public final static String[] BRAND_INFO = new String[3];

    static {
        BRAND_INFO[0] = BMW_INFO;
        BRAND_INFO[1] = AUDI_INFO;
        BRAND_INFO[2] = KIA_INFO;
    }

    public Car(String brand) {
        this.brand = brand;
        countCars++;
    }

    public int getCountCars() {
        return countCars;
    }

    public static void printModels() {
        System.out.println("Доступные бреды: ...");
    }
}

class Runner {
    public static void main(String[] args) {
        Car audi = new Car("audi");
        Car bmw = new Car("bmw");

        audi.getCountCars();
        Car.printModels();

        Car.BRAND_INFO[0] = "Новое щначений";
    }
}
