package com.company.farmland;

import com.company.farm.Farm;
import com.company.game.INextWeekObserver;
import com.company.game.Player;
import com.company.goods.Food;
import com.company.goods.Goods;

import java.util.ArrayList;

public class Farmland extends Land implements INextWeekObserver {
    public Double cost;
    public Farm assignedTo;
    private LandFactory landFactory;

    public Farmland() {
        super(Food.TRAWA, 0.0, 0.0, 0.0, 0.0,
                new ArrayList<>(), 0, 0);
        cost = 100.0;
        landFactory = new LandFactory();
    }

    private void setFarmland(Land land) {
        this.foodType = land.foodType;
        this.costPrepare = land.costPrepare;
        this.costWeekly = land.costWeekly;
        this.costHarvest = land.costHarvest;
        this.amountOfGoods = land.amountOfGoods;
        this.weeksPlanting = land.weeksPlanting;
        this.weeksGrowing = land.weeksGrowing;
    }

    public boolean prepareFarmland(Land land, int week) {
        if (land.weeksPlanting.contains(week)) {
            setFarmland(land);
            this.weekPlanting = week;
            System.out.println("Zasadziłeś " + land.foodType + ". Za " + land.weeksGrowing + " tygodni możesz zebrać plony.");
            return true;
        } else {
            System.out.println("To nie jest czas na sadzenie " + land.getFoodType());
            return false;
        }

    }

    public Goods harvestGoods(int week) {
        if (week >= weekPlanting + weeksGrowing) {
            Goods harvest = new Goods(foodType, amountOfGoods);
            setFarmland(landFactory.create(Food.TRAWA));
            this.weekPlanting = week;
            return harvest;
        }
        return null;
    }




    @Override
    public String toString() {
        return
//                "Farmland{" +
                 foodType.toString();
//                ", cost=" + cost +
//                ", costPrepare=" + costPrepare +
//                ", costWeekly=" + costWeekly +
//                ", costHarvest=" + costHarvest +
//                ", amountOfGoods=" + amountOfGoods +
//                ", weeksPlanting=" + weeksPlanting +
//                ", weeksGrowing=" + weeksGrowing +
//                ", weekPlanting=" + weekPlanting +
//                '}';
    }

    @Override
    public void nextWeek(Player player) {
        player.credits -= costWeekly;
        if (player.getWeek() > 51 && foodType != Food.TRAWA) {
            setFarmland(landFactory.create(Food.TRAWA));
            player.sendMsg(foodType + " nastala zima i plony umarly.", assignedTo);
        }
        if (player.getWeek() >= weekPlanting + weeksGrowing && foodType != Food.TRAWA) {
            player.sendMsg(foodType + " jest gotowe do zbioru.", assignedTo);
        }
    }
}
