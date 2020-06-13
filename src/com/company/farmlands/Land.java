package com.company.farmlands;

import com.company.farms.Farm;

import java.util.ArrayList;
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

    @Override
    public String toString() {
        return "Land{" +
                "weeksPlanting=" + weeksPlanting +
                '}';
    }
}

