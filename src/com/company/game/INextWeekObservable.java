package com.company.game;

public interface INextWeekObservable {
    void registerObserver (INextWeekObserver nextWeekObserver);
    void unregisterObserver (INextWeekObserver nextWeekObserver);
    void notifyObservers();
}
