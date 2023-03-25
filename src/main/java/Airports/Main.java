package Airports;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
    private static final String PATH_TO_CSV_FILE = "src/main/resources/airports.csv";
    private static ArrayList<String> column = new ArrayList<String>();

    public static void main(String[] args) {
        //0. Проверка аргументов запуска прораммы
        int columnNumber = Arguments.checkArguments(args);

        //1. Записать весь столбец в массив, парсинг CSV
        FileCSV.parseColumn(columnNumber, column, PATH_TO_CSV_FILE);

        //2. Сортировка массива записанного столбца
        column.sort(Comparator.naturalOrder());

        //3. Считывание строки поиска
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите строку для поиска (!quit - для завершения работы): ");
            String input = in.nextLine();
            while (input.isEmpty()) {
                System.out.println("Вы ввели пустую строку, введите заново");
                input = in.nextLine();
            }
            if (input.equals("!quit")) {
                break;
            }

            //4. Засекаем время
            long time = System.currentTimeMillis();

            //5. Бинарный поиск, записываем результирующий массив
            ArrayList<String> result = Search.binarySearch(column, input.toLowerCase());

            //6. Останавливаем время
            time = System.currentTimeMillis() - time;

            //7. Выводим результат
            FileCSV.parseResult(columnNumber, result, PATH_TO_CSV_FILE);

            System.out.println(result);
            for (String s : result) {
                System.out.println(s);
            }
            System.out.println("Количество найденных строк: " + result.size() + "\tВремя затраченное на поиск: " + time + "мс");
        }
    }
}



