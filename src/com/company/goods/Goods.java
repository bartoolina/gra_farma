package com.company.goods;

import com.company.farmlands.Food;

public class Goods {
    private Food foodType;
    public Double AmountOfFood;

    public Goods(Food foodType, Double amountOfFood) {
        this.foodType = foodType;
        AmountOfFood = amountOfFood;
    }

    public Food getFoodType() {
        return foodType;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "foodType=" + foodType +
                ", AmountOfFood=" + AmountOfFood +
                '}';
    }
}
