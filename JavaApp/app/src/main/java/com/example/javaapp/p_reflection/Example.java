package com.example.javaapp.p_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Example {
    public static void main(String[] args) {
        Student student = new Student();

        Class classStudent = student.getClass();

        int mode = classStudent.getModifiers();

        if (Modifier.isFinal(mode)) {}
        if (Modifier.isPrivate(mode)) {}
        if (Modifier.isPublic(mode)) {}

        classStudent.getSuperclass();

        Field[] fields = classStudent.getFields();
    }
}
