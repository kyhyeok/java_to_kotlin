package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Fruit {
    public static void main(String[] args) {
        List<Fruit> fruits = Arrays.asList(
                new Fruit("사과", 1_000),
                new Fruit("사과", 1_200),
                new Fruit("사과", 1_200),
                new Fruit("사과", 1_500),
                new Fruit("바나나", 3_000),
                new Fruit("바나나", 3_200),
                new Fruit("바나나", 2_500),
                new Fruit("수박", 10_000)
        );

        filterFruits(fruits, new FruitFilter() {
            @Override
            public boolean isSelected(Fruit fruit) {
                return Arrays.asList("사과", "바나나")
                        .contains(fruit.getName()) && fruit.getPrice() > 5_000;
            }
        });

        // fruit -> fruit.getName().equals("사과") 자바의 람다로 코드 조각을 넘김
        filterFruitsLambda(fruits, fruit -> fruit.getName().equals("사과"));

        // stream을 활용
        filterFruitsStream(fruits, fruit -> fruit.getName().equals("사과"));

        // 메서드 참조 활용
        filterFruitsStream(fruits, Fruit::isApple);

        String targetFruitName = "바나나";
        targetFruitName = "수박";
//         filterFruits(fruits, (fruit) -> targetFruitName.equals(fruit.getName())); // Variable used in lambda expression should be final or effectively final
    }

    private final String name;

    private final int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isApple() {
        return this.name.equals("사과");
    }

    // 사과만
    private List<Fruit> findApples(List<Fruit> fruits) {
        List<Fruit> apples = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals("사과")) {
                apples.add(fruit);
            }
        }
        return apples;
    }

    // 바나나만
    private List<Fruit> findBananas(List<Fruit> fruits) {
        List<Fruit> bananas = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals("바나나")) {
                bananas.add(fruit);
            }
        }
        return bananas;
    }

    // 코드상 중복 존재해서 이름을 매개변수로 받아서 해결
    private List<Fruit> findFruitsWithName(List<Fruit> fruits, String name) {
        List<Fruit> results = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals(name)) {
                results.add(fruit);
            }
        }
        return results;
    }

    private static List<Fruit> filterFruits(List<Fruit> fruits, FruitFilter filter) {
        List<Fruit> results = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (filter.isSelected(fruit)) {
                results.add(fruit);
            }
        }
        return results;
    }

    private static List<Fruit> filterFruitsLambda(List<Fruit> fruits, Predicate<Fruit> filter) {
        List<Fruit> results = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (filter.test(fruit)) {
                results.add(fruit);
            }
        }
        return results;
    }

    private static List<Fruit> filterFruitsStream(List<Fruit> fruits, Predicate<Fruit> filter) {
        return fruits.stream()
                .filter(filter)
                .toList();
    }
}

