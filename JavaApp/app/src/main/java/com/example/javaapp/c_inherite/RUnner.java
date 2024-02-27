package com.example.javaapp.c_inherite;

public class RUnner {
    public static void main(String[] args) {
        RedWolf someRedWolf = new RedWolf();
        someRedWolf.bark();
        int countTeeth = someRedWolf.countTeeth;

        BlackWolf blackWolf = new BlackWolf(34);
    }
}
