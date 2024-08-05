package org.example.DZ_Sem6.Task1;

//-Создать множество ноутбуков.
//-Написать метод, который будет запрашивать у пользователя критерий (или критерии)
//фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
//Например:
//Введите цифру, соответствующую необходимому критерию:
//1 - ОЗУ
//2 - Объем ЖД
//3 - Операционная система
//4 - Цвет …
//-Далее нужно запросить минимальные значения для указанных критериев
//- сохранить параметры фильтрации можно также в Map.
//-Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Notebook note1 = new Notebook();
        note1.id = 1;
        note1.name = "Apple";
        note1.ram = 8;
        note1.storage = 256;
        note1.os = "MacOS";
        note1.color = "Silver";

        Notebook note2 = new Notebook();
        note2.id = 2;
        note2.name = "Lenovo";
        note2.ram = 16;
        note2.storage = 512;
        note2.os = "Windows";
        note2.color = "Black";

        Notebook note3 = new Notebook();
        note3.id = 3;
        note3.name = "Dell";
        note3.ram = 8;
        note3.storage = 256;
        note3.os = "Linux";
        note3.color = "Black";

        Notebook note4 = new Notebook();
        note4.id = 4;
        note4.name = "Asus";
        note4.ram = 32;
        note4.storage = 1024;
        note4.os = "Windows";
        note4.color = "Pink";

        Notebook note5 = new Notebook();
        note5.id = 5;
        note5.name = "Lenovo";
        note5.ram = 16;
        note5.storage = 512;
        note5.os = "Windows";
        note5.color = "White";

        System.out.println(Arrays.asList(note1, note2, note3, note4, note5));

        Set<Notebook> notebooks = new HashSet<>(Arrays.asList(note1, note2, note3, note4, note5));

        displayCriteriaValues(notebooks);
    }
    public static void displayCriteriaValues(Set<Notebook> notebooks) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ОЗУ");
        criteriaMap.put(2, "ЖД");
        criteriaMap.put(3, "ОС");
        criteriaMap.put(4, "Цвет");

        System.out.println("Введите цифру, соответствующую необходимому критерию выбора:");
        criteriaMap.forEach((key, value) -> System.out.println(key + " - " + value));

        int criterion = scanner.nextInt();
        scanner.nextLine();

        switch (criterion) {
            case 1:
                printAllRams(notebooks);
                System.out.println("Введите минимальное значение ОЗУ:");
                int minRam = scanner.nextInt();
                scanner.nextLine();
                filterAndPrintNotebooks(notebooks, "RAM", minRam);
                break;
            case 2:
                printAllStorages(notebooks);
                System.out.println("Введите минимальный объем ЖД:");
                int minStorage = scanner.nextInt();
                scanner.nextLine();
                filterAndPrintNotebooks(notebooks, "Storage", minStorage);
                break;
            case 3:
                printAllOS(notebooks);
                System.out.println("Введите операционную систему:");
                String os = scanner.nextLine();
                filterAndPrintNotebooks(notebooks, "OS", os);
                break;
            case 4:
                printAllColors(notebooks);
                System.out.println("Введите цвет:");
                String color = scanner.nextLine();
                filterAndPrintNotebooks(notebooks, "Color", color);
                break;
            default:
                System.out.println("Неверный критерий. Пожалуйста, введите цифру от 1 до 4.");
        }
    }

    public static void printAllRams(Set<Notebook> notebooks) {
        System.out.println("Значения ОЗУ всех ноутбуков:");
        notebooks.stream()
                .map(Notebook::getRam)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public static void printAllStorages(Set<Notebook> notebooks) {
        System.out.println("Объемы ЖД всех ноутбуков:");
        notebooks.stream()
                .map(Notebook::getStorage)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public static void printAllOS(Set<Notebook> notebooks) {
        System.out.println("Операционные системы всех ноутбуков:");
        notebooks.stream()
                .map(Notebook::getOs)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public static void printAllColors(Set<Notebook> notebooks) {
        System.out.println("Цвета всех ноутбуков:");
        notebooks.stream()
                .map(Notebook::getColor)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public static void filterAndPrintNotebooks(Set<Notebook> notebooks, String criterion, Object value) {
        Set<Notebook> filteredNotebooks = notebooks.stream()
                .filter(notebook -> {
                    switch (criterion) {
                        case "RAM":
                            return notebook.getRam() <= (int) value;
                        case "Storage":
                            return notebook.getStorage() <= (int) value;
                        case "OS":
                            return notebook.getOs().equalsIgnoreCase((String) value);
                        case "Color":
                            return notebook.getColor().equalsIgnoreCase((String) value);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toSet());

        if (filteredNotebooks.isEmpty()) {
            System.out.println("Ноутбуки, соответствующие критериям, не найдены.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям:");
            filteredNotebooks.forEach(System.out::println);
        }
    }
}
