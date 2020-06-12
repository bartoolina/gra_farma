package com.company.animals;

import java.util.List;

abstract public class Animal {
    public final AnimalType species;
    public final Integer cost;
    private Integer weight;
    public final Double growPerWeek;
    private Integer oldInWeeks;
    public final Double foodPerWeek;
    public final List<Food> acceptedFood;

    Animal(AnimalType species, Integer cost, Integer oldInWeeks, Integer weight) {
        this.species = species;
        this.cost = cost;
        this.oldInWeeks = oldInWeeks;
        this.weight = weight;
        acceptedFood = AnimalFood.getAnimalFood(species);
        growPerWeek = AnimalGrow.getGrowRate(species);
        foodPerWeek = AnimalEat.getEatingRate(species);

    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getOldInWeeks() {
        return oldInWeeks;
    }

    public void nextWeek () {

    }
}
