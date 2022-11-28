import java.util.*;
import java.util.stream.IntStream;

public class VehicleRoutingProblem {

    public static void main(String[] args) {
        Map<Integer, Place> placeMap = new HashMap<>();

        //int townNumb = 10;
        //int townNumb = 20;
        //int townNumb = 50;
        //int townNumb = 100;
        int townNumb = 200;
        //int townNumb = 500;

        //int courier = 1;
        int courier = 2;
        //int courier = 4;
        //int courier = 5;

        Random random = new Random();
        IntStream.range(0, townNumb)
                .forEach(i -> placeMap
                        .put(i, new Place(random
                                .nextInt((i * 100) + 1), random
                                .nextInt((i * 100) + 1))));

        int[] order = new int[placeMap.size()];
        IntStream
                .range(0, order.length)
                .forEach(i -> order[i] = i);
        Calculations.shuffle(order);

        List<Integer> passableRoad = new ArrayList<>();
        if (townNumb % courier != 0) {
            double d = townNumb / courier;

            int a = (int) Math.round(d);

            int b = courier * a;
            int c = townNumb - b;

            for (int i = 0; i < a - c; i++) {
                passableRoad.add(a);
            }
            for (int i = 0; i < c; i++) {
                passableRoad.add(a + 1);
            }
        } else {
            IntStream
                    .range(0, townNumb / courier)
                    .forEach(n -> passableRoad.add(townNumb / courier));
        }

        int result = Calculations.result(new int[]{8, 6, 2, 5, 7, 1, 4, 3}, placeMap, new int[]{4, 4});
        System.out.println("Travelled distance: " + result + "m");
    }
}
