package com.example.javaapp.l_record;

import java.util.Objects;

public class Human {
    private final int birthYear;
    private final String name;
    private final String surname;
    private final String lastName;

    public Human(int birthYear,
                 String name,
                 String surname,
                 String lastName) {
        this.birthYear = birthYear;
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Human{" +
                "birthYear=" + birthYear +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthYear == human.birthYear && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(lastName, human.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, name, surname, lastName);
    }
}
