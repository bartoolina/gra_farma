package com.company.farm;

import com.company.building.Building;
import com.company.building.BuildingType;
import com.company.building.Warehouse;
import com.company.farmland.Farmland;
import com.company.goods.Food;
import com.company.game.Player;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    Integer buildingMaxSpaces;
    Integer buildingUsedSpaces;
    List<Building> buildingList;
    Integer farmlandMaxSpaces;
    Integer farmlandUsedSpaces;
    List<Farmland> farmlands;
    Double cost;
    public Player assignetToPlayer;

    public Farm(Integer buildingMaxSpaces, Integer buildingUsedSpaces, List<Building> buildings,
                Integer farmlandMaxSpaces, Integer farmlandUsedSpaces, List<Farmland> farmlands,
                Double cost) {
        this.buildingMaxSpaces = buildingMaxSpaces;
        this.buildingUsedSpaces = buildingUsedSpaces;
        this.buildingList = buildings;
        this.farmlandMaxSpaces = farmlandMaxSpaces;
        this.farmlandUsedSpaces = farmlandUsedSpaces;
        this.farmlands = farmlands;
        this.cost = cost;
    }

    public boolean addBuilding(Building building) {
        if (buildingUsedSpaces + building.space <= buildingMaxSpaces) {
            buildingList.add(building);
            building.assignedToFarm = this;
            buildingUsedSpaces += building.space;
            return true;
        } else {
            assignetToPlayer.sendMsg(" nie masz wystaczajaco miejsca by zbudowac ten budynak.", this);
            return false;
        }
    }

    public void removeBuilding(Building building) {
        if (buildingList.contains(building)) {
            buildingList.remove(building);
            building.assignedToFarm = null;
            buildingUsedSpaces -= building.space;
        } else {
            throw new UnsupportedOperationException("proba usuniecia budynku, ktory nie nalezy do farmy.");
        }
    }

    public boolean addFarmland(Farmland farmland) {
        if (farmlandUsedSpaces < farmlandMaxSpaces) {
            farmlands.add(farmland);
            farmland.assignedTo = this;
            farmlandUsedSpaces++;
            return true;
        } else {
            assignetToPlayer.sendMsg(" nie mozesz dodac wiecej pol uprawnych.", this);
            return false;
        }
    }

    public void removeFarmland(Farmland farmland) {
        if (farmlands.contains(farmland)) {
            farmlands.remove(farmland);
            farmland.assignedTo = null;
            farmlandUsedSpaces--;
        } else {
            throw new UnsupportedOperationException("proba usuniecia pola uprawnego nie nalezacego do farmy.");
        }
    }


    public Double getValue() {
        Double value = cost;
        for (Building building : buildingList) {
            value += building.cost;
        }
        for (Farmland farmland : farmlands) {
            value += farmland.cost;
        }
        return value;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "buildingMaxSpaces=" + buildingMaxSpaces +
                ", buildingUsedSpaces=" + buildingUsedSpaces +
                ", buildings=" + buildingList +
                ", farmlandMaxSpaces=" + farmlandMaxSpaces +
                ", farmlandUsedSpaces=" + farmlandUsedSpaces +
                ", farmlands=" + farmlands +
                ", cost=" + cost +
                ", assignetTo=" + assignetToPlayer +
                '}';
    }

    public boolean getFood(List<Food> acceptedFood, Double foodPerWeek) {
        List<Warehouse> warehouses = getWarehouses();
        Double takenFood = 0.0;
        if (warehouses.size() > 0) {
            int i = 0;
            while (warehouses.size() > i || Math.abs(foodPerWeek - takenFood) > 0.001) {
                takenFood = warehouses.get(i).takeGoods(acceptedFood, foodPerWeek - takenFood);
                i++;
            }
        }
        return Math.abs(foodPerWeek - takenFood) < 0.001;
    }

    private List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        for (Building building : buildingList) {
            if (building.buildingType == BuildingType.MAGAZYN) {
                warehouses.add((Warehouse) building);
            }
        }
        return warehouses;
    }
}
