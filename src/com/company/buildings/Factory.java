package com.company.buildings;

public abstract class Factory {
    abstract AnimalHouse create(BuildingType buildingType, Integer size);
    abstract Warehouse create(Integer size);
}
