package com.company.farmlands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlantingDate {
    static Map<Food, List<Integer>> plantingDate = new HashMap<>(){{
        put(Food.KUKURYDZA, Arrays.asList(15,16,17,18,19,20,21,22,23,24,25));
        put(Food.KAPUSTA, Arrays.asList(10,11,12,13,14,15,16,17,18,31,32,33,34,35,36,37));
        put(Food.BURAKI, Arrays.asList(14,15,16,17,18,19,20,21,22,23,24,25));
        put(Food.SALATA, Arrays.asList(15,16,17));
        put(Food.MARCHEW, Arrays.asList(20,21,22,23,24,25));
        put(Food.ZIEMNIAKI, Arrays.asList(15,16,17,18,19,20,21,22,23,24,25));
        put(Food.OGORKI, Arrays.asList(16,17,18,19,20,21));
        put(Food.JABLKA, Arrays.asList(40,41,42,43,44,45));
        put(Food.ZBOZE, Arrays.asList(15,16,17,18,19,20,21,22));
        put(Food.PSZENICA, Arrays.asList(15,16,17,18,19,20,21,22));
        put(Food.POMIDORY, Arrays.asList(20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36));
        put(Food.RUKOLA, Arrays.asList(10,11,12,13,14,15,16,17,18,31,32,33,34,35,36,37));
        put(Food.RZODKIEWKA, Arrays.asList(10,11,12,13,14,15,16,17,18,31,32,33,34,35,36,37));
        put(Food.SZPINAK, Arrays.asList(10,11,12,13,14,31,32));
    }};

    private PlantingDate() {
    }

    public static List<Integer> get(Food food) {
        return plantingDate.get(food);
    }
}
