package com.example.javaapp.j_equals_and_hashcode;

import androidx.annotation.Nullable;

public class Child implements Cloneable{
    private String name;
    private int year;

    public Child(String newName, int newYear) {
        this.name = newName;
        this.year = newYear;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj instanceof Child child2) {
            return this.getName().equals(child2.getName());
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
