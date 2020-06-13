package com.company.animals;

import java.util.Map;

public final class AnimalAdulthood {
    static Map<AnimalType, Integer> adulthood;

    private AnimalAdulthood() {
        adulthood.put(AnimalType.HORSE, 80);
        adulthood.put(AnimalType.CHICKEN, 28);
        adulthood.put(AnimalType.RABBIT, 28);
        adulthood.put(AnimalType.PIG, 112);
        adulthood.put(AnimalType.SHEEP, 56);
        adulthood.put(AnimalType.COW, 56);
        adulthood.put(AnimalType.GOAT, 56);
        adulthood.put(AnimalType.GOOSE, 28);
    }

    public static Integer getAdulthood(AnimalType animalType) {
        return adulthood.get(animalType);
    }
}
