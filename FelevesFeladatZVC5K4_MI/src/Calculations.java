import java.util.*;
import java.util.stream.IntStream;

public class Calculations {

    public static int result(int[] order, Map<Integer, Place> map, int[] pass) {
        int sum = 0;
        int sum2 = 0;
        int number = 0;
        Map.Entry<Integer, Place> actualValue = map.entrySet()
                .stream()
                .findFirst()
                .get();
        Place base = actualValue.getValue();
        List<Place> passedRoad = new ArrayList<>();

        IntStream
                .range(0, order.length)
                .forEach(index -> passedRoad.add(map.get(order[index])));

        for (int i = 0; i < pass.length; i++) {
            sum = distance(base, passedRoad.get(0));
            for (int j = 0; j < pass[i]; j++) {
                if (j == pass[i] - 1) {
                    sum += distance(passedRoad.get(0), base);
                    passedRoad.remove(passedRoad.get(0));
                } else {
                    sum += distance(passedRoad.get(0), passedRoad.get(1));
                    passedRoad.remove(passedRoad.get(0));
                }
            }
            sum2 += sum;
        }
        return sum2;
    }

    public static int[][] neighbour(int[] v) {
        int[] actualRow = v;
        int sum = 0;
        for (int i = 1; i < actualRow.length; i++) {
            sum += i;
        }
        int[][] matrix = new int[sum][actualRow.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                matrix[i][j] = actualRow[j];
            }
        }
        int temp;
        int counter1 = 0;
        int counter2 = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            temp = matrix[i][counter1];
            matrix[i][counter1] = matrix[i][counter2];
            matrix[i][counter2] = temp;
            counter2++;
        }
        if (counter2 == matrix[1].length) {
            counter1++;
            counter2 = counter1 + 1;
        }

        List<int[]> test = new ArrayList<>();
        int[] need = new int[matrix[1].length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                need[j] = matrix[i][j];
            }
            test.add(Arrays.stream(need).toArray());
        }

        return matrix;
    }

    public static boolean search(int[] base, List<int[]> taboo) {
        return taboo.contains(base);
    }

    public static void tabooSearch(int[] pass, int tabooMax, Map<Integer, Place> map) {
        int iteration = 100;
        List<int[]> taboo = new ArrayList<>();
        int[] actualRow = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        for (int i = 0; i < iteration; i++) {
            List<int[]> ints = new ArrayList<>();
            int[][] neighbours = neighbour(actualRow);

            for (int j = 0; j < neighbours[0].length; j++) {
                int[] neighb = new int[neighbours[1].length];
                for (int k = 0; k < neighbours[1].length; k++) {
                    neighb[k] = neighbours[j][k];
                }
                for (int f = 0; f < neighbours[0].length; f++) {
                    int max = result(actualRow, map, pass);
                    if (search(neighb, taboo)) {
                        int a = result(neighb, map, pass);
                        if (a < max) {
                            max = a;
                            actualRow = neighb;
                        }
                    }
                }
            }
            taboo.add(Arrays.stream(actualRow).toArray());
            if (taboo.stream().count() > tabooMax) {
                for (int j = 0; j < tabooMax; j++) {
                    taboo.remove(taboo.get(0));
                }
            }
        }
    }

    public static int distance(Place c, Place d) {
        int a = Math.abs(c.getX() - d.getX());
        int b = Math.abs(c.getY() - d.getY());

        return a + b;
    }

    public static void shuffle(int[] arr) {
        Random rand = new Random();
        IntStream
                .range(arr.length - 1, 0)
                .forEach(i -> {
                    int index = rand.nextInt(i + 1);
                    int a = arr[index];
                    arr[index] = arr[i];
                    arr[i] = a;
                });
    }
}
