package com.company.buildings;

import com.company.animals.Animal;

import java.util.List;

abstract public class Building<T> {
    final BuildingType buildingType;
    Integer capacity;
    final Integer maxCapacity;
    final Integer space;
    final Double cost;
    final Double costToDestroy;

    public Building(BuildingType buildingType,Double cost ,Double costToDestroy ,Integer maxCapacity, Integer space) {
        this.buildingType = buildingType;
        this.maxCapacity = maxCapacity;
        this.space = space;
        this.cost = cost;
        this.costToDestroy = costToDestroy;
        capacity = 0;
    }

    abstract public void put (T type);
    abstract public void remove (T type);
    abstract public String toString();


}
