package com.company.buildings;

import com.company.goods.Goods;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Building<Goods> {
    private List<Goods> goodsList;

    public Warehouse(BuildingType buildingType, Double cost, Double costToDestroy, Integer maxCapacity, Integer space) {
        super(buildingType, cost, costToDestroy, maxCapacity, space);
        this.goodsList = new ArrayList<>();
    }

    public void put(Goods goods) {
        if (maxCapacity >= capacity + goods.AmountOfFood) {
            boolean goodsIsIn = false;
            for (Goods x : goodsList) {
                if (x.getFoodType().equals(goods.getFoodType())) {
                    x.AmountOfFood += goods.AmountOfFood;
                    goodsIsIn = true;
                    break;
                }
            }
            if (!goodsIsIn) {
                goodsList.add(goods);
            }
        }
    }

    public void remove(Goods goods) {
        goodsList.remove(goods);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
//                "goods=" + goodsList +
                ", buildingType=" + buildingType +
//                ", capacity=" + capacity +
//                ", maxCapacity=" + maxCapacity +
                ", space=" + space +
                '}';
    }
}
