package com.company.goods;

import com.company.animal.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class GoodsCost {
    private static Map<Food, Double> goodsCost = new HashMap<>(){{
        put(Food.KAPUSTA, 1.0);
        put(Food.SIANO, .5);
        put(Food.KUKURYDZA, 0.8);
        put(Food.SALATA, 2.0);
        put(Food.ZIEMNIAKI, 1.0);
        put(Food.BURAKI, 1.5);
        put(Food.MARCHEW, 2.2);
        put(Food.RUKOLA, 1.8);
        put(Food.ZBOZE, 2.5);
        put(Food.PSZENICA, 2.5);
        put(Food.JABLKA, 1.9);
        put(Food.OGORKI, 2.1);
        put(Food.POMIDORY, 3.0);
        put(Food.RZODKIEWKA, 3.5);
        put(Food.SZPINAK, 1.8);

    }};

    private GoodsCost() {
    }

    public static Double getGoodsCost(Food food) {
        return goodsCost.get(food);
    }
}
