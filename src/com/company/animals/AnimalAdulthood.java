package com.company.animals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class AnimalAdulthood {
    static Map<AnimalType, Integer> adulthood = new HashMap<>() {{
        put(AnimalType.HORSE, 80);
        put(AnimalType.CHICKEN, 28);
        put(AnimalType.RABBIT, 28);
        put(AnimalType.PIG, 112);
        put(AnimalType.SHEEP, 56);
        put(AnimalType.COW, 56);
        put(AnimalType.GOAT, 56);
        put(AnimalType.GOOSE, 28);
    }};

    private AnimalAdulthood() {
    }

    public static Integer getAdulthood(AnimalType animalType) {
        return adulthood.get(animalType);
    }
}
