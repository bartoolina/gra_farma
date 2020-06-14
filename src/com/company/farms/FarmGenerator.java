package com.company.farms;

import com.company.buildings.Building;
import com.company.buildings.BuildingFactory;
import com.company.buildings.BuildingType;
import com.company.farmlands.Farmland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FarmGenerator {
    public Farm newFarm() {
        Random random = new Random();
        Double cost = 0.0;
        int maxBuildingsSpace = random.nextInt(7) + 4;
        int buildingUsedSpace = 0;

        int pick, buildingSpace;
        BuildingType buildingType;
        List<Building> buildingList = new ArrayList<>();
        BuildingFactory buildingFactory = new BuildingFactory();
        while (random.nextBoolean() && buildingUsedSpace < maxBuildingsSpace) {
            pick = random.nextInt(BuildingType.values().length);
            buildingType = BuildingType.values()[pick];

            buildingSpace = random.nextInt(3) + 1;
            buildingSpace = Math.min(buildingSpace, (maxBuildingsSpace - buildingUsedSpace));

            buildingList.add(buildingFactory.create(buildingType, buildingSpace));
            cost += buildingList.get(buildingList.size() - 1).cost * 0.5;
            buildingUsedSpace += buildingSpace;
        }

        int maxFarmland = random.nextInt(12) + 4;
        int farmlands = random.nextInt(maxFarmland - 3);
        List<Farmland> farmlandList = new ArrayList<>();
        for (int i = 0; i < farmlands; i++) {
            farmlandList.add(new Farmland());
            cost += farmlandList.get(farmlandList.size() - 1).cost * 0.5;
        }

        cost = cost + 100.0 + maxBuildingsSpace * 50.0 + maxFarmland * 50;


        return new Farm(maxBuildingsSpace, buildingUsedSpace, buildingList, maxFarmland, farmlands, farmlandList, cost);
    }
}
