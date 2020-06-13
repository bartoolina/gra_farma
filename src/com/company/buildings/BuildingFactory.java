package com.company.buildings;

import com.company.animals.Animal;

public class BuildingFactory {

    public Building create(BuildingType buildingType, Integer size) {
        switch (buildingType) {
            case OBORA -> {
                return new AnimalHouse(buildingType, 300.0 * size, 100.0, 10 * size, size);
            }
            case ZAGRODA -> {
                return new AnimalHouse(buildingType, 200.0 * size, 100.0, 20 * size, size);
            }
            case STAJNIA -> {
                return new AnimalHouse(buildingType, 350.0 * size, 100.0, 10 * size, size);
            }
            case OWCZARNIA -> {
                return new AnimalHouse(buildingType, 300.0 * size, 100.0, 15 * size, size);
            }
            case KURNIK -> {
                return new AnimalHouse(buildingType, 250.0 * size, 100.0, 20 * size, size);
            }
            case MAGAZYN -> {
                return new Warehouse(buildingType, 500.0 * size, 100.0, 200 * size, size);
            }
            default -> throw new IllegalStateException("Unexpected value: " + buildingType);
        }
    }

}
