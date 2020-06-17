package com.company.building;

import com.company.animal.Animal;
import com.company.animal.AnimalType;
import com.company.game.ISendMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnimalHouse extends Building<Animal> implements ISendMsg {
    private List<Animal> animalList;
    public final List<AnimalType> acceptedAnimals;


    public AnimalHouse(BuildingType buildingType, Double cost, Double costToDestroy,
                       Double maxCapacity, Integer space, AnimalType... animalTypes) {

        super(buildingType, cost, costToDestroy, maxCapacity, space);
        animalList = new ArrayList<>();
        this.acceptedAnimals = new ArrayList<>(Arrays.asList(animalTypes));
    }

    @Override
    public boolean put(Animal animal) {
        if (animalList.contains(animal)) {
            throw new UnsupportedOperationException("probujesz dodac zwierze ktore juz jest przypisane do tego budynku");
        }
        if (maxCapacity > capacity) {
            animalList.add(animal);
            animal.assignedToBuilding = this;
            capacity++;
            return true;
        } else {
            System.out.println("Nie masz miejsca w " + buildingType);
            return false;
        }

    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public Integer countAmial (AnimalType animalType)
    {
        int count = 0;
        if (acceptedAnimals.contains(animalType)){
            for (Animal animal:animalList){
                if (animal.species == animalType) count++;
            }
        }
        return count;
    }


    @Override
    public boolean remove(Animal animal) {
        if (animalList.contains(animal)) {
            animalList.remove(animal);
            animal.assignedToBuilding = null;
            capacity--;
            return true;
        } else throw new UnsupportedOperationException("proba usunicia zwierzecia z budynku, w ktorym go nie ma.");
    }

    public Double getValueAnimals () {
        Double valueAnimals = 0.0;
        for (Animal animal : animalList) {
            valueAnimals += animal.cost;
        }
        return valueAnimals;
    }




    @Override
    public String toString() {
        return
//                "AnimalHouse{" +
//                "animals=" + animals +
                 buildingType.toString();
//                ", capacity=" + capacity +
//                ", maxCapacity=" + maxCapacity +
//                "," + space +
//                '}';
    }

    @Override
    public boolean isEmpty() {
        return animalList.isEmpty();
    }

    @Override
    public void sendMsg(String msg) {
        assignedToFarm.sendMsg(msg);
    }
}
