package com.example.javaapp.b_oop;

public class Dog {
    private String name = "ExampleName";
    //По умолчанию null, и все ссылочные null
    private int weight = 20;
    //По умолчанию 0
    private String owner = "ExampleOwner";
    private boolean man;
    //По умолчанию false

    {
        System.out.println("Собака создалась");
    }
    //Блок инициализации

    public Dog() {

    }

    public Dog(String name, int weight, String owner) {
        if (weight < 1) {
            this.weight = 5;
        } else {
            this.weight = weight;
        }
        this.name = name;
        this.owner = owner;
    }

    public void setWeight(int newWeight) {
        if (newWeight < 1) {
            this.weight = 5;
        } else {
            this.weight = newWeight;
        }
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void feed() {

    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", owner='" + owner + '\'' +
                '}';
    }
}
