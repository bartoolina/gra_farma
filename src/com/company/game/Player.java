package com.company.game;

import com.company.animal.Animal;
import com.company.animal.AnimalType;
import com.company.building.AnimalHouse;
import com.company.building.Building;
import com.company.building.BuildingType;
import com.company.building.Warehouse;
import com.company.farm.Farm;
import com.company.farmland.Farmland;
import com.company.goods.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Player implements INextWeekObservable {
    Set<INextWeekObserver> observers;
    public Double credits;
    public String name;
    List<Farm> farmList;

    public Player(String name) {
        this.name = name;
        farmList = new ArrayList<>();
        credits = 10000.0;
    }

    public List<Farm> getFarmList() {
        return farmList;
    }

    public boolean addFarm(Farm farm) {
        if (!farmList.contains(farm)) {
            if (farm.getValue() <= credits) {
                farmList.add(farm);
                credits -= farm.getValue();
                System.out.println("Kupiłeś farme!");
                return true;
            } else {
                System.out.println("Masz za mało pięniedzy!");
                return false;
            }
        } else {
            throw new UnsupportedOperationException("proba dodania farmy posiadanej farmy.");
        }
    }

    public void removeFarm(Farm farm) {
        if (farmList.contains(farm)) {
            farmList.remove(farm);
            credits += farm.getValue();
            System.out.println("Sprzedałeś farme za: " + farm.getValue());
        } else {
            throw new UnsupportedOperationException("proba sprzedazy nie swojej farmy.");
        }
    }

    public boolean buyFarmland(Farm farm) {
        if (farmList.contains(farm)) {
            Farmland newFarmland = new Farmland();
            if (newFarmland.cost <= credits) {
                if (farm.addFarmland(newFarmland)) {
                    System.out.println("Kupiłeś pole uprawne.");
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("Masz za mało pięniedzy!");
                return false;
            }
        } else {
            throw new UnsupportedOperationException("proba dodania pola uprawnego do nie swojej farmy.");
        }
    }

    public void sellFarmland(Farmland farmland) {
        for (Farm farm : farmList) {
            if (farm.getFarmlandList().contains(farmland)) {
                farm.removeFarmland(farmland);
                credits += farmland.cost;
                System.out.println("Sprzedałeś pole uprawne.");
            } else {
                throw new UnsupportedOperationException("proba sprzedania pola nie nalezacego do famry.");
            }
        }

    }

    @Override
    public void registerObserver(INextWeekObserver nextWeekObserver) {
        observers.add(nextWeekObserver);
    }

    @Override
    public void unregisterObserver(INextWeekObserver nextWeekObserver) {
        if (observers.contains(nextWeekObserver)) {
            observers.remove(nextWeekObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for (INextWeekObserver observer : observers) {
            observer.nextWeek(this);
        }
    }

    public void sendMsg(String msg, Farm farm) {
        //TODO
    }

    public Integer getWeek() {
        //TODO
        return null;
    }

    public void buyBuilding(Building building, Farm farm) {
        if (farmList.contains(farm)) {

            if (building.cost <= credits) {
                if (farm.addBuilding(building)) {
                    System.out.println("Wybudowales nowy budynek - " + building.buildingType);
                }
            } else {
                System.out.println("Masz za mało pięniedzy!");
            }
        } else {
            throw new UnsupportedOperationException("proba dodania pola uprawnego do nie swojej farmy.");
        }
    }

    public List<AnimalHouse> getAnimalHouses(Farm farm, AnimalType animalType) {
        if (farmList.contains(farm)) {
            return farm.getAnimalHouse(animalType);
        } else return null;
    }

    public List<Warehouse> getWarehouses(Farm farm) {
        if (farmList.contains(farm)) {
            return farm.getWarehouses();
        } else return null;
    }

    public boolean buyAnmial(Animal animal, Farm farm, AnimalHouse animalHouse) {
        if (farmList.contains(farm)) {
            if (farm.getBuildingList().contains(animalHouse)) {
                if (animal.cost <= credits) {
                    credits -= animal.cost;
                    if (animalHouse.put(animal)) {
                        System.out.println("Dodałeś " + animal.species + " do " + animalHouse.buildingType + ". Pozostao wolnego miejsca: " + animalHouse.getFreeCapacity());
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    System.out.println("Masz za mało pięniedzy!");
                    return false;
                }

            } else {
                throw new UnsupportedOperationException("proba dodania zwierzecia do budynku nie nzalezacego do gracza");
            }
        } else {
            throw new UnsupportedOperationException("proba dodania zwierzecia do farmy nie nzalezacego do gracza");
        }
    }

    public void removeBuilding(Farm farm, Building building) {
        if (farmList.contains(farm)) {
            if (farm.getBuildingList().contains(building)) {
                if (building.isEmpty()) {
                    if (credits >= building.costToDestroy) {
                        credits -= building.costToDestroy;
                        farm.getBuildingList().remove(building);
                        System.out.println("Usunąłęś budynek - " + building.buildingType);
                    } else {
                        System.out.println("Masz za mało pięniedzy!");
                    }
                } else {
                    if (building.buildingType == BuildingType.MAGAZYN) {
                        System.out.println("Nie możesz usunąć magazynu, poniewąż jest wypełniony towarami.");
                        System.out.println("Sprzedaj , nakarm, lub zasadż coś i sprobuj ponownie.");
                    } else {
                        System.out.println("Nie możesz usunąć " + building.buildingType + ", poniewąż mieszkają w nim zwięrezta.");
                        System.out.println("Sprzedaj zwięrzeta a potem sprobuj ponownie.");
                    }
                }
            } else {
                throw new UnsupportedOperationException("proba usuniecia budynku, ktory nie nalezy do farmy");
            }
        } else {
            throw new UnsupportedOperationException("proba usuniecia budynku z nie nalazej do gracza farmy");
        }
    }

    public boolean buyGoods(Farm farm, Goods goods, Warehouse warehouse) {
        if (farmList.contains(farm)) {
            if (farm.getWarehouses().contains(warehouse)) {
                if (credits >= goods.cost * goods.amountOfFood) {
                    Double amount = goods.amountOfFood;
                    warehouse.put(goods);
                    credits -= goods.cost * goods.amountOfFood;
                    System.out.println("Kupiłeś " + goods.getFoodType() + " w ilości " + (amount - goods.amountOfFood) + " kg");
                    return true;
                } else {
                    System.out.printf("Nie masz wystaczajacych pieniedzy.");
                    return false;
                }
            } else {
                throw new UnsupportedOperationException("proba dodania towarow do nie swojego magazynu");
            }

        } else {
            throw new UnsupportedOperationException("proba dodania towarow do nie swojej farmy");
        }
    }

    public boolean sellGoods(Farm farm, Goods goods, Warehouse warehouse, Double amountToSell) {
        if (farmList.contains(farm)) {
            if (farm.getWarehouses().contains(warehouse)) {
                if (warehouse.getGoodsList().contains(goods)) {
                    Goods goodsToSell = new Goods(goods.getFoodType(), amountToSell, goods.cost);
                    warehouse.remove(goodsToSell);
                    credits += goodsToSell.cost * (amountToSell - goodsToSell.amountOfFood);
                    System.out.println("Sprzedałeś " + goods.getFoodType() + " w ilosci " + (amountToSell - goodsToSell.amountOfFood) + " kg. Zarobiles " + (amountToSell - goodsToSell.amountOfFood) * goods.cost);
                    return true;
                } else {
                    System.out.println("Nie masz " + goods.getFoodType() + " w tym magazynie.");
                    return false;
                }
            } else {
                throw new UnsupportedOperationException("proba sprzedania towaru z nie swojego magazynu.");
            }
        } else {
            throw new UnsupportedOperationException("proba sprzedania towaru z nie swojej farmy.");
        }
    }
}
