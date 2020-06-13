package com.company.buildings;

public class BuildingFactory extends Factory {
    @Override
    public AnimalHouse create(BuildingType buildingType, Integer size) {
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

            default -> throw new IllegalStateException("Unexpected value: " + buildingType);
        }
    }

    @Override
    public Warehouse create(Integer size) {
        return new Warehouse(BuildingType.MAGAZYN, 500.0, 100.0, 100 * size, size);
    }
}
