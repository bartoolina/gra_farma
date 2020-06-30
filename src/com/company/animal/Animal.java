package com.company.animal;

import com.company.building.AnimalHouse;
import com.company.game.INextWeekObserver;
import com.company.game.Player;
import com.company.goods.Food;

import java.util.List;

public class Animal implements INextWeekObserver {
    public final AnimalType species;
    public final Double cost;
    private Double weight;
    public final Double growPerWeek;
    private Integer oldInWeeks;
    private final Integer adulthood;
    public final Double foodPerWeek;
    public final List<Food> acceptedFood;

    public Integer getWeeksStarving() {
        return weeksStarving;
    }

    private Integer weeksStarving;
    public AnimalHouse assignedToBuilding;

    Animal(AnimalType species, Double cost, Integer oldInWeeks, Double weight) {
        this.species = species;
        this.cost = cost;
        this.oldInWeeks = oldInWeeks;
        this.weight = weight;
        weeksStarving = 0;
        acceptedFood = AnimalFood.getAnimalFood(species);
        growPerWeek = AnimalGrow.getGrowRate(species);
        foodPerWeek = AnimalEat.getEatingRate(species);
        adulthood = AnimalAdulthood.getAdulthood(species);
    }

    public void nextWeek (Player player) {
        if (oldInWeeks < adulthood){
            oldInWeeks++;
            if (oldInWeeks.equals(adulthood)) animalIsAdult(player);
        }

        if (takeFood(acceptedFood, foodPerWeek)){
            weight += growPerWeek;
            weeksStarving = 0;
        } else {
            weight -= growPerWeek;
            if (weeksStarving > 1) animalIsDead(player);
            else {
                System.out.println(player.name + ": " +species+ " głoduje. Za mało jedzenia!");
            }
        }
    }

    private boolean takeFood(List<Food> acceptedFood, Double foodPerWeek) {
        return assignedToBuilding.assignedToFarm.getFood(acceptedFood, foodPerWeek);
    }

    private void animalIsDead(Player player) {
        System.out.println(species + " zmarlo z glodu.");
        assignedToBuilding.remove(this);
        player.unregisterObserver(this);
    }

    private void animalIsAdult(Player player) {
        System.out.println(species + " osiagnelo doroslowsc.");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species=" + species +
                ", cost=" + cost + "$" +
                ", weight=" + String.format("%.2f",weight) + "kg" +
                ", oldInWeeks=" + oldInWeeks +
                ", foodPerWeek=" + foodPerWeek + "kg" +
                ", acceptedFood=" + acceptedFood +
                ", weeksStarving=" + weeksStarving +
                '}';
    }

    public Integer getAge() {
        return oldInWeeks;
    }

    public Double getWeight() {
        return weight;
    }


}