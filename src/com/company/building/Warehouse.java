package com.company.building;

import com.company.goods.Food;
import com.company.goods.Goods;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Building<Goods> {
    private List<Goods> goodsList;

    public Warehouse(BuildingType buildingType, Double cost, Double costToDestroy, Double maxCapacity, Integer space) {
        super(buildingType, cost, costToDestroy, maxCapacity, space);
        this.goodsList = new ArrayList<>();
    }

    public boolean put(Goods goods) {
        Double insertedGoods = Math.min(maxCapacity - capacity, goods.amountOfFood);
        if (insertedGoods > 0.001) {
            boolean goodsIsIn = false;
            for (Goods goodsInBuilding : goodsList) {
                if (goodsInBuilding.getFoodType().equals(goods.getFoodType())) {
                    goodsInBuilding.amountOfFood += insertedGoods;
                    goodsIsIn = true;
                    break;
                }
            }
            if (!goodsIsIn) {
                goodsList.add(new Goods(goods.getFoodType(), insertedGoods));
            }
        } else {
            assignedToFarm.assignetToPlayer.sendMsg("Magazyn jest pelny.", assignedToFarm);
        }
        if((goods.amountOfFood - insertedGoods) < 0.001) {
            goods.amountOfFood = 0.0;
            return true;
        } else {
            goods.amountOfFood -= insertedGoods;
            return false;
        }
    }

    public boolean remove(Goods goods) {
        Double takenGoods = 0.0;
        for (Goods goodsInBuilding : goodsList) {
            if (goodsInBuilding.getFoodType().equals(goods.getFoodType())) {
                takenGoods = Math.min(goods.amountOfFood, goodsInBuilding.amountOfFood);
                goodsInBuilding.amountOfFood -= takenGoods;
                break;
            }
        }
        if((goods.amountOfFood - takenGoods) < 0.001) {
            goods.amountOfFood = 0.0;
            return true;
        } else {
            goods.amountOfFood -= takenGoods;
            return false;
        }
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

    @Override
    public boolean isEmpty() {
        return goodsList.isEmpty();
    }

    public Double takeGoods(List<Food> acceptedFood, Double neededGoods) {
        Double takenGoods = 0.0;
        for (Goods goods : goodsList) {
            if (acceptedFood.contains(goods.getFoodType())) {
                takenGoods = Math.min(goods.amountOfFood, neededGoods);
                goods.amountOfFood -= takenGoods;
                if (Math.abs(takenGoods - neededGoods) < 0.001){
                    takenGoods = neededGoods;
                    break;
                }
            }
        }
        return takenGoods;
    }
}
