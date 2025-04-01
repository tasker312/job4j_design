package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    private static DirFileCache cache;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    setCacheDirectory(scanner);
                    break;
                case 2:
                    loadFileToCache(scanner);
                    break;
                case 3:
                    getFileFromCache(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Указать кэшируемую директорию");
        System.out.println("2. Загрузить содержимое файла в кэш");
        System.out.println("3. Получить содержимое файла из кэша");
        System.out.println("4. Выход");
        System.out.print("Выберите опцию: ");
    }

    private static void setCacheDirectory(Scanner scanner) {
        System.out.print("Введите путь к кэшируемой директории: ");
        String dir = scanner.nextLine();
        cache = new DirFileCache(dir);
    }

    private static void loadFileToCache(Scanner scanner) {
        if (cache == null) {
            System.out.println("Сначала укажите кэшируемую директорию.");
            return;
        }
        System.out.print("Введите имя файла для загрузки в кэш: ");
        String fileToLoad = scanner.nextLine();
        cache.get(fileToLoad);
        System.out.println("Файл загружен в кэш.");

    }

    private static void getFileFromCache(Scanner scanner) {
        if (cache == null) {
            System.out.println("Сначала укажите кэшируемую директорию.");
            return;
        }
        System.out.print("Введите имя файла для получения из кэша: ");
        String fileToGet = scanner.nextLine();
        String content = cache.get(fileToGet);
        System.out.println("Содержимое файла: " + content);
    }
}
