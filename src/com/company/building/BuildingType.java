package com.company.building;

import com.company.animal.AnimalType;

public enum BuildingType {
    OBORA, ZAGRODA, STAJNIA, OWCZARNIA, KURNIK, MAGAZYN;

    public static BuildingType get (AnimalType animalType) {
        switch (animalType) {
            case COW, PIG -> {
                return BuildingType.OBORA;
            }
            case RABBIT -> {
                return BuildingType.ZAGRODA;
            }
            case HORSE -> {
                return BuildingType.STAJNIA;
            }
            case SHEEP, GOAT -> {
                return BuildingType.OWCZARNIA;
            }
            case CHICKEN, GOOSE -> {
                return BuildingType.KURNIK;
            }


            default -> throw new IllegalStateException("Unexpected value: " + animalType);
        }
    }
}
