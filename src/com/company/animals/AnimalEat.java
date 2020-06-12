package com.company.animals;

import java.util.Map;

public final class AnimalEat {
    private static Map<AnimalType, Double> animalEating;

    private AnimalEat() {
        animalEating.put(AnimalType.HORSE, 10.0);
        animalEating.put(AnimalType.CHICKEN, 1.0);
        animalEating.put(AnimalType.RABBIT, 2.0);
        animalEating.put(AnimalType.PIG, 8.0);
        animalEating.put(AnimalType.SHEEP, 4.0);
        animalEating.put(AnimalType.COW, 12.0);
        animalEating.put(AnimalType.GOAT, 5.0);
        animalEating.put(AnimalType.GOOSE, 2.0);
    }

    public static Double getEatingRate(AnimalType animalType) {
        return animalEating.get(animalType);
    }
}
