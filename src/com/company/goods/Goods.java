package com.company.goods;

public class Goods {
    private Food foodType;
    public Double amountOfFood;

    public Goods(Food foodType, Double amountOfFood) {
        this.foodType = foodType;
        this.amountOfFood = amountOfFood;
    }

    public Food getFoodType() {
        return foodType;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "foodType=" + foodType +
                ", AmountOfFood=" + amountOfFood +
                '}';
    }
}
