package com.example.javaapp.c_inherite;

public class RedWolf extends Wolf{
    public RedWolf() {
        super(28);
    }

    @Override
    public void bark() {
        System.out.println("Собственная реализация");
        super.bark();
        int a = super.countLeg;
    }

    @Override
    public void run() {
        
    }
}
