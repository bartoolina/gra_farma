package com.company.animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class AnimalFood {
    static private Map<AnimalType, List<Food>> animalfood;

    private AnimalFood() {

        animalfood.put(AnimalType.HORSE, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.ZBOZE);
            add(Food.MARCHEW);
        }});

        animalfood.put(AnimalType.CHICKEN, new ArrayList<>(){{
            add(Food.ZBOZE);
            add(Food.PSZENICA);
            add(Food.KUKURYDZA);
        }});

        animalfood.put(AnimalType.RABBIT, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.KAPUSTA);
        }});

        animalfood.put(AnimalType.PIG, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.ZIEMNIAKI);
            add(Food.KAPUSTA);
            add(Food.KUKURYDZA);
        }});

        animalfood.put(AnimalType.SHEEP, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
        }});

        animalfood.put(AnimalType.COW, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
        }});

        animalfood.put(AnimalType.GOAT, new ArrayList<>(){{
            add(Food.SIANO);
            add(Food.TRAWA);
            add(Food.ZIEMNIAKI);
            add(Food.KAPUSTA);
            add(Food.KUKURYDZA);
        }});

        animalfood.put(AnimalType.GOOSE, new ArrayList<>(){{
            add(Food.MARCHEW);
            add(Food.KAPUSTA);
            add(Food.BURAKI);
            add(Food.ZIEMNIAKI);
        }});
    }

    public static List<Food> getAnimalFood (AnimalType animalType) {
        return animalfood.get(animalType);
    }

}
