import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Начальные переменные
        final String right = "right";
        final String left = "left";
        final String up = "up";
        final String down = "down";
        String direction = right;
        int[][] arr = {
                {20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29},
                {30, 31, 32, 33, 34},
                {35, 36, 37, 38, 39},
                {40, 41, 42, 43, 44},
                {45, 46, 47, 48, 49},
                {50, 51, 52, 53, 54},
                {55, 56, 57, 58, 59},
                {60, 61, 62, 63, 64},
                {65, 66, 67, 68, 69},
        };


        // Решение
        // Создаем одномерный ArrayList всех элементов
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list_result = new ArrayList<Integer>(); // результирующий
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
        }

        // Посчитаем количество строк и столбцов
        int rows = arr.length;
        int columns = arr[0].length;

        // В зависимости от направления производим действия
        stop:
        while (true) {
            switch (direction) {
                case right: {
                    for (int i = 0; i < columns; i++) {
                        list_result.add(list.get(i));
                        list.set(i, -1);
                    }
                    list.removeIf(new Integer(-1)::equals);
                    rows--;
                    if (list.size() == 0)
                        break stop;
                    direction = down;
                }
                case down: {
                    for (int i = 1; i <= list.size(); i++) {
                        if (i % columns == 0) {
                            list_result.add(list.get(i - 1));
                            list.set(i - 1, -1);
                        }
                    }
                    list.removeIf(new Integer(-1)::equals);
                    columns--;
                    if (list.size() == 0)
                        break stop;
                    direction = left;
                }
                case left: {
                    for (int i = list.size(); i > list.size() - columns; i--) {
                        list_result.add(list.get(i - 1));
                        list.set(i - 1, -1);
                    }
                    list.removeIf(new Integer(-1)::equals);
                    rows--;
                    if (list.size() == 0)
                        break stop;
                    direction = up;
                }
                case up: {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        if (i % columns == 0) {
                            list_result.add(list.get(i));
                            list.set(i, -1);
                        }
                    }
                    list.removeIf(new Integer(-1)::equals);
                    columns--;
                    if (list.size() == 0)
                        break stop;
                    direction = right;
                }
            }
        }

        System.out.print("Спиральный массив: ");
        for (int i = 0; i < list_result.size(); i++) {
            System.out.print(list_result.get(i) + " ");
        }
    }
}