package com.company;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory;
import com.company.animals.AnimalType;
import com.company.buildings.*;
import com.company.farmlands.Farmland;
import com.company.farmlands.Land;
import com.company.farmlands.LandFactory;
import com.company.farmlands.Food;
import com.company.farms.Farm;
import com.company.farms.FarmGenerator;
import com.company.goods.Goods;
import com.company.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        Menu menu = new Menu();
//        menu.show();
//        menu.getLine(menu.getInput());

//        AnimalFactory animalFactory = new AnimalFactory();
//        Animal animal1 = animalFactory.create(AnimalType.CHICKEN);
//        Animal animal2 = animalFactory.create(AnimalType.CHICKEN);
//        Animal animal3 = animalFactory.create(AnimalType.CHICKEN);
//        System.out.println(animal1);
//        System.out.println(animal2);
//        System.out.println(animal3);
//
//        BuildingFactory buildingFactory = new BuildingFactory();
//        Building animalHouse = buildingFactory.create(BuildingType.KURNIK, 1);
//
//        List<Building> tada = new ArrayList<>();
//        tada.add(animalHouse);
//        System.out.println(tada);
//        animalHouse.put(animal1);
//        animalHouse.put(animal3);
//        animalHouse.put(animal2);
//
//        Building warehouse = buildingFactory.create(BuildingType.MAGAZYN, 3);
//        tada.add(warehouse);
//        System.out.println(tada);
//
//        Farm farma = new Farm(10);
//        farma.addBuilding(animalHouse);
//        System.out.println(farma);
//
//        LandFactory landFactory = new LandFactory();
//        Land cos = landFactory.create(Food.KAPUSTA);
//        System.out.println(cos);
//        Farmland farmland = new Farmland();
//        farmland.prepareFarmland(cos, 10);
//        System.out.println(farmland);
//        Goods zwrot = farmland.harvestGoods(26);
//        System.out.println(zwrot );

        Player bart = new Player("Bart",1000.0);
        FarmGenerator farmGenerator = new FarmGenerator();
        Farm farm1 = farmGenerator.newFarm();
        Farm farm2 = farmGenerator.newFarm();
        Farm farm3 = farmGenerator.newFarm();
        Farm farm4 = farmGenerator.newFarm();
        Farm farm5 = farmGenerator.newFarm();
        System.out.println(farm1);
        System.out.println(farm2);
        System.out.println(farm3);
        System.out.println(farm4);
        System.out.println(farm5);

    }
}
