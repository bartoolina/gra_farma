package com.company.building;

import com.company.animal.AnimalType;

public class BuildingFactory {

    public Building create(BuildingType buildingType, Integer size) {
        switch (buildingType) {
            case OBORA -> {
                return new AnimalHouse(buildingType, 300.0 + (size - 1) * 300.0, 100.0, 10.0 * size, size,AnimalType.COW, AnimalType.PIG);
            }
            case ZAGRODA -> {
                return new AnimalHouse(buildingType, 200.0 + (size - 1) * 200.0, 100.0, 20.0 * size, size, AnimalType.RABBIT);
            }
            case STAJNIA -> {
                return new AnimalHouse(buildingType, 350.0 + (size - 1) * 350.0, 100.0, 10.0 * size, size, AnimalType.HORSE);
            }
            case OWCZARNIA -> {
                return new AnimalHouse(buildingType, 300.0 + (size - 1) * 300.0, 100.0, 15.0 * size, size, AnimalType.SHEEP, AnimalType.GOAT);
            }
            case KURNIK -> {
                return new AnimalHouse(buildingType, 250.0 + (size - 1) * 250.0, 100.0, 20.0 * size, size, AnimalType.CHICKEN, AnimalType.GOOSE);
            }
            case MAGAZYN -> {
                return new Warehouse(buildingType, 500.0 + (size - 1) * 500.0, 100.0, 200.0 * size, size);
            }
            default -> throw new IllegalStateException("Unexpected value: " + buildingType);
        }
    }

}
