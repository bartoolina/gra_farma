package com.company.farmlands;

import java.util.ArrayList;

public class LandFactory {

    public Land create(Food foodType) {
        switch (foodType) {
            case TRAWA -> {
                return new Land(Food.TRAWA, 0.0, 0.0,
                        0.0, 0.0, new ArrayList<>(), 0, 0);
            }
            case KUKURYDZA -> {
                return new Land(foodType, 100.0,
                        10.0, 50.0, 200.0,
                        PlantingDate.get(foodType), 0, 20);
            }
            case KAPUSTA -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 100.0,
                        PlantingDate.get(foodType), 0, 16);
            }
            case BURAKI -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 150.0,
                        PlantingDate.get(foodType), 0, 20);
            }
            case SALATA -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 100.0,
                        PlantingDate.get(foodType), 0, 10);
            }
            case MARCHEW -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 150.0,
                        PlantingDate.get(foodType), 0, 25);
            }
            case ZIEMNIAKI -> {
                return new Land(foodType, 100.0,
                        5.0, 50.0, 200.0,
                        PlantingDate.get(foodType), 0, 16);
            }
            case OGORKI -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 100.0,
                        PlantingDate.get(foodType), 0, 15);
            }
            case JABLKA -> {
                return new Land(foodType, 30.0,
                        10.0, 50.0, 50.0,
                        PlantingDate.get(foodType), 0, 10);
            }
            case ZBOZE -> {
                return new Land(foodType, 100.0,
                        5.0, 100.0, 200.0,
                        PlantingDate.get(foodType), 0, 20);
            }
            case PSZENICA -> {
                return new Land(foodType, 100.0,
                        5.0, 50.0, 200.0,
                        PlantingDate.get(foodType), 0, 20);
            }
            case POMIDORY -> {
                return new Land(foodType, 20.0,
                        10.0, 50.0, 100.0,
                        PlantingDate.get(foodType), 0, 10);
            }
            case RUKOLA -> {
                return new Land(foodType, 50.0,
                        10.0, 50.0, 100.0,
                        PlantingDate.get(foodType), 0, 12);
            }
            case RZODKIEWKA -> {
                return new Land(foodType, 50.0,
                        5.0, 50.0, 50.0,
                        PlantingDate.get(foodType), 0, 12);
            }
            case SZPINAK -> {
                return new Land(foodType, 20.0,
                        10.0, 50.0, 50.0,
                        PlantingDate.get(foodType), 0, 15);
            }
            default -> throw new IllegalStateException("Unexpected value: " + foodType);
        }
    }
}
