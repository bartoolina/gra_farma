package com.company.farmlands;

import com.company.farms.Farm;

import java.util.ArrayList;

public class Farmland extends Land{
    Double cost;
    public Farm assignedTo;

    public Farmland() {
        super(Food.TRAWA, 0.0,0.0,0.0,0.0,
                new ArrayList<>(),0,0);
        cost = 100.0;
    }

    public void prepareFarmland (Land land, Integer actualWeek) {
        if (land.weeksPlanting.contains(actualWeek)) {
            this.foodType = land.foodType;
            this.costPrepare = land.costPrepare;
            this.costWeekly = land.costWeekly;
            this.costHarvest = land.costHarvest;
            this.amountOfGoods = land.amountOfGoods;
            this.weeksPlanting = land.weeksPlanting;
            this.weeksGrowing = land.weeksGrowing;
            this.weekPlanting = actualWeek;
        }
    }

    @Override
    public String toString() {
        return "Farmland{" +
                "foodType=" + foodType +
                ", cost=" + cost +
                ", costPrepare=" + costPrepare +
                ", costWeekly=" + costWeekly +
                ", costHarvest=" + costHarvest +
                ", amountOfGoods=" + amountOfGoods +
                ", weeksPlanting=" + weeksPlanting +
                ", weeksGrowing=" + weeksGrowing +
                '}';
    }
}
