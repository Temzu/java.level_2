package task_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] strings = createArrayOfString();
        printSetOfString(strings);
        System.out.println();
        countHowManyTimesAStringsOccurs(strings);
    }

    static String[] createArrayOfString() {
        return new String[]{
                "Различные", "слова", "через", "запятую", "их", "здесь", "будет", "двадцать",
                "плюс", "некоторые", "будут", "повторяться", "как", "например", "такие", "слова",
                "как", "через", "и", "здесь"
        };
    }

    static void printSetOfString(String[] strings) {
        System.out.println("Список слов, которые встречаются в переданном массиве строк(без дубликатов):");
        System.out.println(new LinkedHashSet<>(Arrays.asList(strings)));
    }

    static void countHowManyTimesAStringsOccurs(String[] strings) {
        Map<String, Integer> map = new HashMap<>();
        for (String string : strings) {
            if (map.containsKey(string)) {
                map.put(string, map.get(string) + 1);
            } else {
                map.put(string, 1);
            }
        }
        for (String str : map.keySet()) {
            System.out.println("Слово \"" + str + "\" встречается " + map.get(str) + " раз/раза");
        }
    }
}
