package Airports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileCSV {
    public static File directory = new File("");

    public static ArrayList<String> parseColumn(int columnNumber, ArrayList<String> column, String FilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(directory.getAbsolutePath() + "/" + FilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] columns = line.split(",");
                column.add(columns[columnNumber].toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return column;
    }


    public static ArrayList<String> parseResult(int columnNumber, ArrayList<String> result, String FilePath) {
        sortNumeric(result);
        try (BufferedReader reader = new BufferedReader(new FileReader(directory.getAbsolutePath() + "/" + FilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lineWithReplace = line.replaceAll("\"", "");
                String[] columnsWithReplace = lineWithReplace.split(",");
                String[] columns = line.split(",");
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).equals(columnsWithReplace[columnNumber].toLowerCase())) {
                        result.set(i, columns[columnNumber] + " [" + line + "]");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // Сортировка чисел перед выводом
    private static ArrayList<String> sortNumeric(ArrayList<String> result) {
        try {
            Integer[] columnNumeric = new Integer[result.size()];
            for (int i = 0; i < result.size(); i++) {
                columnNumeric[i] = Integer.parseInt(result.get(i));
            }
            Arrays.sort(columnNumeric);
            for (int i = 0; i < result.size(); i++) {
                result.set(i, String.valueOf(columnNumeric[i]));
            }
        } catch (NumberFormatException exc) {
            try {
                Double[] columnNumeric = new Double[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    columnNumeric[i] = Double.parseDouble(result.get(i));
                }
                Arrays.sort(columnNumeric);
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, String.valueOf(columnNumeric[i]));
                }
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }
}
