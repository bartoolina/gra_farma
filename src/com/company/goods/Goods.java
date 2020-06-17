package com.company.goods;

public class Goods {
    private Food foodType;
    public Double amountOfFood;
    public Double cost;

    public Goods(Food foodType, Double amountOfFood) {
        this.foodType = foodType;
        this.amountOfFood = amountOfFood;
        cost = 0.0;
    }

    public Goods(Food foodType, Double amountOfFood, Double cost) {
        this.foodType = foodType;
        this.amountOfFood = amountOfFood;
        this.cost = cost;
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
