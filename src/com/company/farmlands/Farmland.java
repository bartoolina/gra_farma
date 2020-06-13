package com.company.farmlands;

import java.util.List;

public class Farmland {
    Food foodType;
    Double cost;
    Double costPrepare;
    Double costWeekly;
    Double costHarvest;
    Double amountOfGoods;
    List<Integer> weeksPlanting;
    Integer weeksGrowing;

    public Farmland(Food foodType, Double cost, Double costPrepare, Double costWeekly, Double costHarvest, Double amountOfGoods, List<Integer> weeksPlanting, Integer weeksGrowing) {
        this.foodType = foodType;
        this.cost = cost;
        this.costPrepare = costPrepare;
        this.costWeekly = costWeekly;
        this.costHarvest = costHarvest;
        this.amountOfGoods = amountOfGoods;
        this.weeksPlanting = weeksPlanting;
        this.weeksGrowing = weeksGrowing;
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
