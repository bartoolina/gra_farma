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
                    capacity += insertedGoods;
                    goodsIsIn = true;
                    break;
                }
            }
            if (!goodsIsIn) {
                goodsList.add(new Goods(goods.getFoodType(), insertedGoods));
                capacity += insertedGoods;
            }
        } else {
            sendMsg("Magazyn jest pelny.");
        }
        if((goods.amountOfFood - insertedGoods) < 0.001) {
            goods.amountOfFood = 0.0;
            return true;
        } else {
            goods.amountOfFood -= insertedGoods;
            System.out.println("W jednym w Twoich magazynow skonczylo sie miejsce ");
            System.out.println("Dodano tylko " + insertedGoods + "kg "+ goods.getFoodType());
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
            System.out.println("W jednym w Twoich magazynow skonczylo sie " + goods.getFoodType());
            return false;
        }
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    @Override
    public String toString() {
        return
//                "goods=" + goodsList +
                buildingType.toString();
//                ", capacity=" + capacity +
//                ", maxCapacity=" + maxCapacity +
//                ", space=" + space +
//                '}';
    }

    @Override
    public boolean isEmpty() {
        return goodsList.isEmpty();
    }

    public Double takeGoods(List<Food> acceptedFood, Double neededGoods) {
        //Warning:(81,9) Type may be primitive <- o co ... chodzi?
        Double takenGoods = 0.0;
        for (Goods goods : goodsList) {
            if (acceptedFood.contains(goods.getFoodType())) {
                takenGoods = Math.min(goods.amountOfFood, neededGoods);
                goods.amountOfFood -= takenGoods;
                if (Math.abs(takenGoods - neededGoods) < 0.001){
                    takenGoods = neededGoods;
                    break;
                } else {
                    neededGoods -= takenGoods;
                    sendMsg("W jednym w Twoich magazynow skonczylo sie " + goods.getFoodType());
                }
            }
        }
        return takenGoods;
    }

    @Override
    public void sendMsg(String msg) {
        assignedToFarm.sendMsg(msg);
    }
}
