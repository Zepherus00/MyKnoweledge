package com.example.javaapp.a_basic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println();
    }
}

class Array {
    public static void main() {
        String[] namesOfMonth = new String[12];
        System.out.println(Arrays.toString(namesOfMonth));
        namesOfMonth[1] = "1";
        namesOfMonth[2] = "2";

        int[] numbers = {1, 2, 3, 5, 6};
        for (int number : numbers) {
            System.out.println(namesOfMonth[number]);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(numbers);
        }

        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};
        int[][] multiArray = {array1, array2};

        upper:
        for (int[] array : multiArray) {
            for (int number : array) {
                if (number == 5) {
                    break upper;
                }
            }
        }

        int[] group = {1, 4, 6, 2};
        for (int count : group) {
            if (count == 6) {
                continue;
            }
            System.out.println("Ok");
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("sfg");
        for (String value: list) {
            System.out.println(value);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Set setExample = new HashSet();

        Map<String, Integer> mapExample = new HashMap<>();
        for (String key: mapExample.keySet()) {

        }
    }
}

class SwitchCase {
    public static void main() {
        int month = 5;
        switch (month) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                System.out.println("5");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("6 or 7 or 8");
                break;
            case 9:
                System.out.println("9");
                break;
            default:
                System.out.println("nothing");
                break;
        }
    }
}

class Ternary {
    public static void main(String[] args) {
        int money = 100;
        int price = 20;
        int moneyAfterPrice = money - price;
        int childMoney = moneyAfterPrice >= 50 ? 1 : 0;
        moneyAfterPrice = moneyAfterPrice >= 50 ? 1 : 0;
    }
}

class Strings {
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static void main(String[] args) {
        //Одинаковые объекты в памяти
        String a = "abc";
        String b = "abc";
        Boolean x = a == b;

        //Разные объекты в памяти
        String e = new String("abc");
        String d = new String("abc");
        Boolean y = e.equals(d);

        //Верхний регистр
        String textUpper = e.toUpperCase();
        //Нижний регистр
        String textLower = e.toLowerCase();
        //Есть ли в тексте другой текст
        boolean containsText = e.contains("hello");
        //Замена кусков текста на другой текст
        String replacedText = e.replaceAll("r", "a");
        //Повторить текст n раз
        String repeatedText = e.repeat(5);
        //Разбить текст на массив String
        String names = "a;b;c;d";
        String[] splited = names.split(";");
        //Объединение строк
        String st1 = "st1";
        String st2 = "st2";
        String sumStr = st1 + st2;
        //Начинается на подстроку
        boolean started = e.startsWith("Hel");
        //Заканчивается на подстроку
        boolean ended = e.endsWith("Hel");
        //Обрезка строки (Первые 5 символов)
        String substringText = e.substring(0, 5);

        String name = "Андрей";
        int age = 30;

        String formatText = String.format("Меня зовут %s, мне %d лет", name, age);
        System.out.println(formatText);
        //Или вместо можно:
        System.out.printf("Меня зовут %s, мне %d лет", name, age);

        String name1 = "Имя";
        String name2 = "Фамилия";
        String text = """
                    Привет, как тебя зовут?
                    Меня зовут %s. А тебя?
                    Меня зовут %s.
                """.formatted(name1, name2);
    }
}