package com.company;

import com.company.farm.Farm;
import com.company.farm.FarmGenerator;

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

        Player bart = new Player("Bart",5000.0);
        FarmGenerator farmGenerator = new FarmGenerator();
        Farm farm1 = farmGenerator.newFarm();
        System.out.println(farm1);
        bart.buyFarm(farm1);


    }
}
