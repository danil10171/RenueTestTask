package Airports;

import java.util.ArrayList;

public class Search {
    public static ArrayList<String> binarySearch(ArrayList<String> sortArr, String inputString) {
        int low = 0;
        int high = sortArr.size() - 1;
        int mid;
        ArrayList<String> result = new ArrayList<>();

        while (low <= high) {
            mid = (low + high) / 2;
            if (sortArr.get(mid).compareTo(inputString) < 0) {
                low = mid + 1;
                if (sortArr.get(mid).startsWith(inputString)) {
                    result = smallSearch(sortArr, result, mid, inputString);
                    break;
                }
            } else if (sortArr.get(mid).compareTo(inputString) > 0) {
                high = mid - 1;
                if (sortArr.get(mid).startsWith(inputString)) {
                    result = smallSearch(sortArr, result, mid, inputString);
                    break;
                }
            } else {
                result = smallSearch(sortArr, result, mid, inputString);
                break;
            }
        }
        return result;
    }

    //Поиск вверх и вниз от найденной строки
    private static ArrayList<String> smallSearch(ArrayList<String> sortArr, ArrayList<String> result, int index, String inputString) {
        boolean notFindAllWords = true;
        boolean checkIndexUp = true;
        boolean checkIndexDown = true;
        int indexUp = index;
        int indexDown = index;

        while (notFindAllWords) {
            if (indexUp < sortArr.size() - 1 && sortArr.get(indexUp + 1).startsWith(inputString)) {
                indexUp++;
            } else checkIndexUp = false;
            if (indexDown >= 1 && sortArr.get(indexDown - 1).startsWith(inputString)) {
                indexDown--;
            } else checkIndexDown = false;
            notFindAllWords = checkIndexDown || checkIndexUp;
        }

        for (; indexDown <= indexUp; indexDown++) {
            result.add(sortArr.get(indexDown));
        }
        return result;
    }
}

