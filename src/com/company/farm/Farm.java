package com.company.farm;

import com.company.building.Building;
import com.company.building.BuildingType;
import com.company.building.Warehouse;
import com.company.farmland.Farmland;
import com.company.game.ISendMsg;
import com.company.goods.Food;
import com.company.game.Player;

import java.util.ArrayList;
import java.util.List;

public class Farm implements ISendMsg {
    Integer buildingMaxSpaces;
    Integer buildingUsedSpaces;
    List<Building> buildingList;
    Integer farmlandMaxSpaces;
    Integer farmlandUsedSpaces;
    List<Farmland> farmlandList;
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
        this.farmlandList = farmlands;
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
            farmlandList.add(farmland);
            farmland.assignedTo = this;
            farmlandUsedSpaces++;
            return true;
        } else {
            assignetToPlayer.sendMsg(" nie mozesz dodac wiecej pol uprawnych.", this);
            return false;
        }
    }

    public void removeFarmland(Farmland farmland) {
        if (farmlandList.contains(farmland)) {
            farmlandList.remove(farmland);
            farmland.assignedTo = null;
            farmlandUsedSpaces--;
        } else {
            throw new UnsupportedOperationException("proba usuniecia pola uprawnego nie nalezacego do farmy.");
        }
    }


    public Double getValue() {
        Double value = 100.0 + buildingMaxSpaces * 50.0 + farmlandMaxSpaces * 50;
        for (Building building : buildingList) {
            value += building.cost * 0.5;
        }
        for (Farmland farmland : farmlandList) {
            value += farmland.cost * 0.5;
        }
        return value;
    }

    @Override
    public String toString() {
//        return "Farm{" +
//                "buildingMaxSpaces=" + buildingMaxSpaces +
//                ", buildingUsedSpaces=" + buildingUsedSpaces +
//                ", buildings=" + buildingList +
//                ", farmlandMaxSpaces=" + farmlandMaxSpaces +
//                ", farmlandUsedSpaces=" + farmlandUsedSpaces +
//                ", farmlands=" + farmlands +
//                ", cost=" + cost +
//                ", assignetTo=" + assignetToPlayer +
//                '}';

        return "Ilość budynkow: " + buildingList.size() + ", ilość pól uprawnych: " + farmlandList.size();
    }

    public String toStringBuy() {
        return "\nMiejsce pod budynki: " + buildingMaxSpaces +
                "\nMiejsce zajęte przez budynki: " + buildingUsedSpaces +
                "\nBudynki na farmie: " + buildingList +
                "\nMiejsce pod pola uprawne: " + farmlandMaxSpaces +
                "\nWykupione miejsca pod pola uprawne: " + farmlandUsedSpaces +
                "\nCena zakupu: " + cost + "\n";
    }

    public String toStringSell() {
        return "\nMiejsce pod budynki: " + buildingMaxSpaces +
                "\nMiejsce zajęte przez budynki: " + buildingUsedSpaces +
                "\nBudynki na farmie: " + buildingList +
                "\nMiejsce pod pola uprawne: " + farmlandMaxSpaces +
                "\nWykuione miejsca pod pola uprawne: " + farmlandUsedSpaces +
                "\nCena sprzedaży: " + cost + "\n";
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

    @Override
    public void sendMsg(String msg) {
        assignetToPlayer.sendMsg(msg, this);
    }
}
