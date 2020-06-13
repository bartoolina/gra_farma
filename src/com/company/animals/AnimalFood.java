package com.company.animals;

import com.company.farmlands.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AnimalFood {
    static private Map<AnimalType, List<Food>> animalfood = new HashMap<>(){{
        put(AnimalType.HORSE, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.ZBOZE);
            add(Food.MARCHEW);
            add(Food.BURAKI);
        }});

        put(AnimalType.CHICKEN, new ArrayList<>(){{
            add(Food.ZBOZE);
            add(Food.PSZENICA);
            add(Food.KUKURYDZA);
        }});

        put(AnimalType.RABBIT, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.KAPUSTA);
            add(Food.SALATA);
        }});

        put(AnimalType.PIG, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.ZIEMNIAKI);
            add(Food.KAPUSTA);
            add(Food.SALATA);
            add(Food.KUKURYDZA);
        }});

        put(AnimalType.SHEEP, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
        }});

        put(AnimalType.COW, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
        }});

        put(AnimalType.GOAT, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
            add(Food.ZIEMNIAKI);
            add(Food.KAPUSTA);
            add(Food.KUKURYDZA);
        }});

        put(AnimalType.GOOSE, new ArrayList<>(){{
            add(Food.MARCHEW);
            add(Food.KAPUSTA);
            add(Food.SALATA);
            add(Food.BURAKI);
            add(Food.ZIEMNIAKI);
        }});
    }};

    private AnimalFood() {
    }

    public static List<Food> getAnimalFood (AnimalType animalType) {
        return animalfood.get(animalType);
    }

}
