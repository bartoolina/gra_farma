package com.company.animals;

import java.util.Map;

public final class AnimalGrow {
    private static Map<AnimalType, Double> animalGrowRate;

    private AnimalGrow() {
        animalGrowRate.put(AnimalType.HORSE, 6.0);
        animalGrowRate.put(AnimalType.CHICKEN, 0.1);
        animalGrowRate.put(AnimalType.RABBIT, 0.2);
        animalGrowRate.put(AnimalType.PIG, 4.0);
        animalGrowRate.put(AnimalType.SHEEP, 1.0);
        animalGrowRate.put(AnimalType.COW, 4.0);
        animalGrowRate.put(AnimalType.GOAT, 1.0);
        animalGrowRate.put(AnimalType.GOOSE, 0.3);
    }

    public static Double getGrowRate(AnimalType animalType) {
        return animalGrowRate.get(animalType);
    }
}
