package com.company.farmland;

import com.company.goods.Food;

import java.util.List;

public class Land {
    Food foodType;
    Double costPrepare;
    Double costWeekly;
    Double costHarvest;
    Double amountOfGoods;
    List<Integer> weeksPlanting;
    Integer weekPlanting;
    Integer weeksGrowing;

    public Land(Food foodType, Double costPrepare, Double costWeekly, Double costHarvest, Double amountOfGoods, List<Integer> weeksPlanting, Integer weekPlanting, Integer weeksGrowing) {
        this.foodType = foodType;
        this.costPrepare = costPrepare;
        this.costWeekly = costWeekly;
        this.costHarvest = costHarvest;
        this.amountOfGoods = amountOfGoods;
        this.weeksPlanting = weeksPlanting;
        this.weekPlanting = weekPlanting;
        this.weeksGrowing = weeksGrowing;
    }

    public Food getFoodType() {
        return foodType;
    }

    public Double getCostPrepare() {
        return costPrepare;
    }

    public Double getCostWeekly() {
        return costWeekly;
    }

    public Double getCostHarvest() {
        return costHarvest;
    }

    public Double getAmountOfGoods() {
        return amountOfGoods;
    }

    public List<Integer> getWeeksPlanting() {
        return weeksPlanting;
    }

    public Integer getWeekPlanting() {
        return weekPlanting;
    }

    public Integer getWeeksGrowing() {
        return weeksGrowing;
    }

    @Override
    public String toString() {
        return "Land{" +
                "weeksPlanting=" + weeksPlanting +
                '}';
    }
}

