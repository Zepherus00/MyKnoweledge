package com.example.javaapp.c_inherite;

public abstract class Wolf {
    protected int countLeg = 4;
    public int countTeeth;
    protected final int countIyes = 2;

    public Wolf(int countTeeth) {
        this.countTeeth = countTeeth;
    }
    public void bark() {

    }

    public final void eat() {

    }

    public abstract void run();
}
