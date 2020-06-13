package com.company.players;

import com.company.buildings.AnimalHouse;
import com.company.buildings.Building;
import com.company.buildings.BuildingType;
import com.company.farms.Farm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    static Integer counterPlayer = 0;
    final String name;
    Set<Farm> farmSet;
    Double credits;

    public Player(String name, Double credits) {
        this.name = name;
        this.farmSet = new HashSet<>();
        this.credits = credits;
        counterPlayer++;
    }

    public void buyFarm (Farm farm) {
        if (farm.getCost() <= credits) {
            credits -= farm.getCost();
            farm.assignetTo = this;
            farmSet.add(farm);
        }
    }

    public void sellFarm (Farm farm) {
        if (farmSet.contains(farm)) {
            farm.getBuildings().forEach(building -> {
                if(building.buildingType != BuildingType.MAGAZYN){
                    credits += ((AnimalHouse)building).getValueAnimals();
                }
            });
            credits += farm.getValue();
        }
    }

}
