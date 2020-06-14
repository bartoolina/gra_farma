package com.company.farmlands;

import com.company.farms.Farm;
import com.company.goods.Goods;

import java.util.ArrayList;

public class Farmland extends Land{
    public Double cost;
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

    public Goods harvestGoods (Integer actualWeek) {
        if (actualWeek >= weekPlanting + weeksGrowing) {
            Goods harvest =  new Goods(foodType, amountOfGoods);
            this.foodType = Food.TRAWA;
            this.costPrepare = 0.0;
            this.costWeekly = 0.0;
            this.costHarvest = 0.0;
            this.amountOfGoods = 0.0;
            this.weeksPlanting = null;
            this.weeksGrowing = 0;
            this.weekPlanting = actualWeek;
            return harvest;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Farmland{" +
                "foodType=" + foodType +
//                ", cost=" + cost +
//                ", costPrepare=" + costPrepare +
//                ", costWeekly=" + costWeekly +
//                ", costHarvest=" + costHarvest +
//                ", amountOfGoods=" + amountOfGoods +
//                ", weeksPlanting=" + weeksPlanting +
//                ", weeksGrowing=" + weeksGrowing +
//                ", weekPlanting=" + weekPlanting +
                '}';
    }
}
