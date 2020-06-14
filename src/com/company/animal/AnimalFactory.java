package com.company.animal;

import java.util.Random;

public class AnimalFactory {

    public Animal create(AnimalType animalType) {
        Integer old = AnimalAdulthood.getAdulthood(animalType);
        Double weight = AnimalGrow.getGrowRate(animalType) * old;
        Random generator = new Random();

        old = old + generator.nextInt(old);
        weight = weight + generator.nextDouble() * (weight / 10);

        switch (animalType) {
            case COW -> {
                return new Animal(animalType, 100.0, old, weight);
            }
            case PIG -> {
                return new Animal(animalType, 101.0, old, weight);
            }
            case RABBIT -> {
                return new Animal(animalType, 102.0, old, weight);
            }
            case HORSE -> {
                return new Animal(animalType, 103.0, old, weight);
            }
            case SHEEP -> {
                return new Animal(animalType, 104.0, old, weight);
            }
            case GOAT -> {
                return new Animal(animalType, 105.0, old, weight);
            }
            case CHICKEN -> {
                return new Animal(animalType, 106.0, old, weight);
            }
            case GOOSE -> {
                return new Animal(animalType, 107.0, old, weight);
            }
            default -> throw new IllegalStateException("Unexpected value: " + animalType);
        }
    }
}
