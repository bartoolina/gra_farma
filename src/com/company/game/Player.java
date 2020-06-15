package com.company.game;

import com.company.farm.Farm;

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

    public void addFarm (Farm farm){
        if (!farmList.contains(farm)) {
            if (farm.getValue() <= credits){
                farmList.add(farm);
                credits -= farm.getValue();
                System.out.println("Kupiłeś farme!");
            } else {
                System.out.println("Masz za mało pięniedzy!");
            }
        } else {
            throw new UnsupportedOperationException("proba dodania farmy posiadanej farmy.");
        }
    }

    public void removeFarm (Farm farm){
        if (farmList.contains(farm)) {
            farmList.remove(farm);
            credits += farm.getValue();
            System.out.println("Sprzedałeś farme za: " + farm.getValue());
        } else {
            throw new UnsupportedOperationException("proba sprzedazy nie swojej farmy.");
        }
    }

    @Override
    public void registerObserver(INextWeekObserver nextWeekObserver) {
        observers.add(nextWeekObserver);
    }

    @Override
    public void unregisterObserver(INextWeekObserver nextWeekObserver) {
        if(observers.contains(nextWeekObserver)) {
            observers.remove(nextWeekObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for(INextWeekObserver observer : observers) {
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
}
