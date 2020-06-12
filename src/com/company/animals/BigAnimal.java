package com.company.animals;

import java.util.List;

public class BigAnimal extends Animal {
    private BigAnimal(AnimalType species, Integer cost, Integer growPerWeek, Double foodPerWeek, List<Food> acceptedFood) {
        super(species, cost, growPerWeek, foodPerWeek, acceptedFood);
    }

}
