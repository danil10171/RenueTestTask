package Airports;

public class Arguments {
    private static final int DEFAULT_COLUMN_NUMBER = 1;

    public static int checkArguments(String[] args) {
        if (args.length > 1) {
            throw new RuntimeException("Количество аргументов запуска программы превышает допустимое значение 1");
        } else if (args.length == 0) {
            System.out.println("Аргумент запуска программы не обнаружен, по умолчанию - 2 колонка");
            return DEFAULT_COLUMN_NUMBER;
        } else {
            return Integer.parseInt(args[0]) - 1;
        }
    }
}
