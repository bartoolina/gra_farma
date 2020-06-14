package com.company.animal;

import java.util.HashMap;
import java.util.Map;

public final class AnimalGrow {
    private static Map<AnimalType, Double> animalGrowRate = new HashMap<>(){{
        put(AnimalType.HORSE, 6.0);
        put(AnimalType.CHICKEN, 0.1);
        put(AnimalType.RABBIT, 0.06);
        put(AnimalType.PIG, 2.85);
        put(AnimalType.SHEEP, 1.8);
        put(AnimalType.COW, 12.5);
        put(AnimalType.GOAT, 2.1);
        put(AnimalType.GOOSE, 0.12);
    }};

    private AnimalGrow() {
    }

    public static Double getGrowRate(AnimalType animalType) {
        return animalGrowRate.get(animalType);
    }
}
