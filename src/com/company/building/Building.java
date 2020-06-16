package com.company.building;

import com.company.farm.Farm;
import com.company.game.ISendMsg;
import com.company.game.Player;

import java.util.List;

abstract public class Building<T> implements ISendMsg {
    public final BuildingType buildingType;
    protected Double capacity;
    final Double maxCapacity;
    public final Integer space;
    public final Double cost;
    public final Double costToDestroy;
    public Farm assignedToFarm;


    public Building(BuildingType buildingType, Double cost , Double costToDestroy ,
                    Double maxCapacity, Integer space) {
        this.buildingType = buildingType;
        this.maxCapacity = maxCapacity;
        this.space = space;
        this.cost = cost;
        this.costToDestroy = costToDestroy;

        capacity = 0.0;
    }

    abstract public boolean put (T type);
    abstract public boolean remove (T type);
    abstract public String toString();
    abstract public boolean isEmpty();
    abstract public List<T> getAccepted();



    public Double getFreeCapacity() {
        return maxCapacity - capacity;
    }
}
