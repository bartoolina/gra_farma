package com.company.buildings;

import com.company.goods.Good;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Building<Good> {
    private List<Good> goods;

    public Warehouse(BuildingType buildingType, Double cost, Double costToDestroy, Integer maxCapacity, Integer space) {
        super(buildingType, cost, costToDestroy, maxCapacity, space);
        this.goods = new ArrayList<>();
    }

    public void put (Good good) {
        goods.add(good);
    }

    public void remove (Good good) {
        goods.remove(good);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "goods=" + goods +
                ", buildingType=" + buildingType +
                ", capacity=" + capacity +
                ", maxCapacity=" + maxCapacity +
                ", space=" + space +
                '}';
    }
}
