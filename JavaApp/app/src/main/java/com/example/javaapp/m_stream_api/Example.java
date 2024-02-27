package com.example.javaapp.m_stream_api;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example {
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static void main(String[] args) {
        List<String> products = List.of("apple", "milk", "cookie", "apple", "butter");
        int count = 0;
        for (String product : products) {
            if (product.equals("apple")) count++;
        }

        products.stream().filter(e -> e.equals("apple")).count();

        //Лямба выражения
        Flyable airplane = () -> System.out.println("Airplane flying");
        Calc calculator = (a, b) -> a + b;
        int result = calculator.sum(5, 6);

        //Интерфейс Predicate который возвращает boolean и принимает один аргумент
        Predicate<Integer> isAdult = (age) -> age > 20;
        isAdult.test(30);

        //Интерфейс Function который принимает входящий и выходящий тип данных
        Function<Integer, String> automat = (money) -> {
            if (money == 100) return "Cola";
            else return "Water";
        };
        automat.apply(77);

        //Принимает 1 аргумент и ничего не возвращает
        Consumer<String> fire = (item) -> {
            if (item == "wood") System.out.println("Горит долго");
            else System.out.println("Горит быстро");
        };

        //Не принимает аргумент и возвращает указанный тип данных
        Supplier<Integer> talons = () -> {
            return new Random().nextInt(100);
        };

        //Функция сравнения, принимает 2 аргумента и возвращает отрицательное число если первый объект
        //меньше второго, 0 если объекты равны, положит-ое число если первый больше второго
        Student ivan = new Student(80);
        Student petr = new Student(87);

        Student[] studentsGroup = new Student[]{ivan, petr};
        Comparator<Student> studentComparator = (st1, st2) -> st1.getWeight() - st2.getWeight();

        //Stream - последовательность элементов, к которой можно применять лямбда выражения
        Circle circle1 = new Circle("red");
        Circle circle2 = new Circle("red");
        Circle circle3 = new Circle("red");
        List<Circle> circleList = List.of(circle1, circle2, circle3);
        circleList.stream().forEach(circle -> circle.setColor("yellow"));

        //Параллельные stream
        circleList.parallelStream().forEach(circle -> circle.setColor("yellow"));

        //Функция высшего порядка
        Operator operator = new Operator();
        OperatorAction creditCardAction = () -> System.out.println("");
        operator.call(creditCardAction);

        //Создание stream
        int[] list = {1, 2, 3};
        IntStream stream = Arrays.stream(list);

        List<Integer> integers = List.of(1, 2, 3);
        Stream<Integer> stream1 = integers.stream();

        Stream<Integer> stream2 = Stream.of(1, 2, 3);

        Stream<Integer> limitFrom1 = Stream.generate(() -> 1).limit(3);

        //Терминальные операции в Stream
        Stream<Integer> stream3 = Stream.of(1, 2, 3);
        stream3.count();
        stream3.count(); //Эта операция не выполнится

        //Логические терминальные операции
        List<Integer> integers1 = List.of(2, 4, 8);
        integers1.stream().anyMatch(number -> number == 8);//если хотя бы один элемент со значением 8
        integers1.stream().allMatch(number -> number < 10);//все элементы меньше 10
        integers1.stream().noneMatch(number -> number < 0);//ни один элемент не меньше 0

        //Optional
        Car audi = new Car("audi");
        Car bmw = new Car("bmw");

        Car[] garage = new Car[2];
        Car firstCar = garage[0] = audi;
        garage[1] = bmw;

        Optional<Car> carOptional = Optional.ofNullable(firstCar);

        //Получение первого элемента
        integers1.stream().findFirst().orElseThrow();
        integers1.stream().findFirst().orElse(10);

        //Получение максимального элемента
        integers1.stream().max((a, b) -> a - b);

        //Получение минимального элемента
        integers1.stream().min((a, b) -> a - b);

        //Сумма всех элементов, если не будет элементов в List, то выполнится код в ifPresent
        integers1.stream().reduce((a, b) -> a + b).ifPresent(e -> System.out.println(e));

        //Собрать элементы в List, Set, Map(количество повторов в List)
        List<Integer> collect = integers1.stream().collect(Collectors.toList());
        Set<Integer> set = integers1.stream().collect(Collectors.toSet());
        integers1.stream().collect(Collectors.toMap(
                number -> number,
                number -> 1,
                (valueLast, valueCurrent) -> valueLast + valueCurrent));

        //Промежуточные операции
        //Фильтрация списка
        integers1.stream().filter(number -> number > 3).forEach(number -> System.out.println(number));

        //Удалим дубликаты
        integers1.stream().distinct().forEach(number -> System.out.println(number));

        //Убираем первые n элементов
        integers1.stream().skip(3).forEach(number -> System.out.println(number));

        //Оставляет первые n элементов
        integers1.stream().limit(3).forEach(number -> System.out.println(number));

        //Преобразует элемент в другой тип данных(.map) и действие с элементом не меняя тип данных(.peek)
        //    }
        Teacher tom = new Teacher(20, "Tom");
        Teacher yuri = new Teacher(24, "Yuri");
        Teacher rog = new Teacher(28, "Rog");

        List<Teacher> teachers = List.of(tom, yuri, rog);
        teachers.stream()
                .peek(e -> System.out.println(e))
                .peek(student -> student.setName("newName"))
                .map(teacher -> teacher.getAge())
                .peek(e -> System.out.println(e))
                .forEach(e -> {
                });

        //Обединить стримы в единый stream
        List<List<Integer>> lists = List.of(List.of(1, 2, 3), List.of(3, 4, 5), List.of(5, 6, 7));
        lists.stream().flatMap(e -> e.stream());

        //Сортирует элементы
        integers1.stream()
                .sorted()
                .forEach(e -> System.out.println(e));

        integers1.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(e -> System.out.println(e));

        teachers.stream()
                .sorted((a, b) -> a.getAge() - b.getAge())
                .forEach(teacher -> System.out.println(teacher));

        //Работа с внешними переменными
        AtomicInteger sum = new AtomicInteger();
        integers1.stream().forEach(e -> {
            sum.addAndGet(e);
        });
        sum.get();

        List<String> strings = List.of("a", "b", "c");
        StringBuffer text = new StringBuffer();
        strings.stream().forEach(e -> {
            text.append(e);
        });

        text.toString();

        //Работа с исключениями
        List<Car> cars = List.of(new Car(1));
        cars.stream().forEach(e -> {
            try {
                e.start();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Method Reference
        FactoryBrick.createBrick();
        Class<FactoryBrick> factoryBrickClass = FactoryBrick.class;
        factoryBrickClass.getMethods();
    }
}