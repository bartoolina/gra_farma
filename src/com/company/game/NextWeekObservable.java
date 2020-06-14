package com.company.game;

public interface NextWeekObservable {
    void registerObserver (NextWeekObserver nextWeekObserver);
    void unregisterObserver (NextWeekObserver nextWeekObserver);
    void notifyObservers();
}
