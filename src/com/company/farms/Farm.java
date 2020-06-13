package com.company.farms;

import com.company.buildings.Building;
import com.company.farmlands.Farmland;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    Integer buildingMaxSpaces;
    Integer buildingUsedSpaces;
    List<Building> buildings;
    Integer farmlandMaxSpaces;
    Integer farmlandUsedSpaces;
    List<Farmland> farmlands;
    Double cost;
    // owner

    public Farm(Integer buildingMaxSpaces) {
        this.buildingMaxSpaces = buildingMaxSpaces;
        this.buildingUsedSpaces = 0;
        buildings = new ArrayList<>();
    }

    public void addBuilding(Building building) {
        if (buildingUsedSpaces + building.space <= buildingMaxSpaces) {
            buildings.add(building);
            building.assignedTo = this;
            buildingUsedSpaces += building.space;
        }
    }

    public void removeBuilding(Building building) {
        if (buildings.contains(building)) {
            buildings.remove(building);
            building.assignedTo = null;
            buildingUsedSpaces -= building.space;
        }
    }

    public void addFarmland(Farmland farmland) {
        if (farmlandUsedSpaces < farmlandMaxSpaces) {
            farmlands.add(farmland);
            farmland.assignedTo = this;
            farmlandUsedSpaces++;
        }
    }

    public void removeFarmland(Farmland farmland) {
        if (farmlands.contains(farmland)) {
            farmlands.remove(farmland);
            farmland.assignedTo = null;
            farmlandUsedSpaces--;
        }
    }

    public Integer getBuildingUsedSpaces() {
        return buildingUsedSpaces;
    }

    public Integer getFarmlandUsedSpaces() {
        return farmlandUsedSpaces;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "buildingMaxSpaces=" + buildingMaxSpaces +
                ", buildingUsedSpaces=" + buildingUsedSpaces +
                ", buildings=" + buildings +
                '}';
    }
}
