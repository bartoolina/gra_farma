package com.company.animals;

import java.util.HashMap;
import java.util.Map;

public final class AnimalEat {
    private static Map<AnimalType, Double> animalEating = new HashMap<>(){{
        put(AnimalType.HORSE, 10.0);
        put(AnimalType.CHICKEN, 1.0);
        put(AnimalType.RABBIT, 2.0);
        put(AnimalType.PIG, 8.0);
        put(AnimalType.SHEEP, 4.0);
        put(AnimalType.COW, 12.0);
        put(AnimalType.GOAT, 5.0);
        put(AnimalType.GOOSE, 2.0);
    }};

    private AnimalEat() {
    }

    public static Double getEatingRate(AnimalType animalType) {
        return animalEating.get(animalType);
    }
}
