package com.company.game;

import com.company.farm.Farm;

import java.util.Set;

public class Player implements NextWeekObservable {
    Set<NextWeekObserver> observers;
    public Double credits;

    @Override
    public void registerObserver(NextWeekObserver nextWeekObserver) {
        observers.add(nextWeekObserver);
    }

    @Override
    public void unregisterObserver(NextWeekObserver nextWeekObserver) {
        if(observers.contains(nextWeekObserver)) {
            observers.remove(nextWeekObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for(NextWeekObserver observer : observers) {
            observer.nextWeek(this);
        }
    }

    public void sendMsg(String msg, Farm farm) {

    }

    public Integer getWeek() {
        return null;
    }
}
