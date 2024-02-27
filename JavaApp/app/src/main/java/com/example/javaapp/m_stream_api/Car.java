package com.example.javaapp.m_stream_api;

public class Car {
    private String brand;
    private int fuel;
    public Car(String brand) {
        this.brand = brand;
    }

    public Car(int fuel) {
        this.fuel = fuel;
    }

    public void start() throws Exception {
        if (fuel < 1) {
            throw new Exception("Нет бензина");
        }
    }

    public String getBrand() {
        return brand;
    }
}
