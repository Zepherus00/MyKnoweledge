package com.example.javaapp.b_oop;

public class Cat {
    public String color;
    double weight;
    private String name;

    public Cat(String colorName, double weightAnimal, String nameAnimal) {
        this.color = colorName;
        this.weight = weightAnimal;
        this.name = nameAnimal;
    }

    public void mya(String name) {
        System.out.println("$name mya!");
    }

    public String findItem() {
        return "Item";
    }

    public String findItem(boolean isDay) {
        if (isDay) {
            return "Success";
        }
        return "Nothing";
    }

    public void feed(String ...product) {

    }
    //vararg должен быть последним в конструкторе

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}

class MainOop {
    public static void main(String[] args) {
        Cat cat = new Cat("Black", 10.0, "Tom");
//        cat.name = "Jerry";
        cat.mya("Tom");
        String findItem = cat.findItem();
        String findItem2 = cat.findItem(true);

        cat.feed("рыбы", "овощи", "мясо");

        Dog dogge = new Dog("Muhtar", 10, "I");
        dogge.setWeight(12);

        Dog doggie2 = new Dog();
    }
}

class Recursion {
    public int sum(int number) {
        if (number < 1) {
            return 0;
        }
        int result = number + sum(number - 1);
        return result;
    }
}