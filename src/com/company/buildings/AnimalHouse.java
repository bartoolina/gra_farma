package com.company.buildings;

import com.company.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalHouse extends Building<Animal> {
    private List<Animal> animals;

    public AnimalHouse(BuildingType buildingType, Double cost, Double costToDestroy, Integer maxCapacity, Integer space) {
        super(buildingType, cost, costToDestroy, maxCapacity, space);
        animals = new ArrayList<>();
    }

    @Override
    public void put(Animal animal) {
        if (animals.contains(animal)) {
            alreadyContains();
            return;
        }
        if (maxCapacity > capacity) {
            animals.add(animal);
            animal.assignedTo = this;
            capacity++;
        } else notEnoughSpece();
    }

    private void alreadyContains() {
    }

    private void notEnoughSpece() {
    }

    @Override
    public void remove(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
            animal.assignedTo = null;
            capacity--;
        } else animalNotExist();
    }

    private void animalNotExist() {
        System.out.println("nie ma zwierzewcia");
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return "AnimalHouse{" +
                "animals=" + animals +
                ", buildingType=" + buildingType +
                ", capacity=" + capacity +
                ", maxCapacity=" + maxCapacity +
                ", space=" + space +
                '}';
    }
}
