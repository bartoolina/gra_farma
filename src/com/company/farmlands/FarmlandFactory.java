package com.company.farmlands;

public class FarmlandFactory {
    public Farmland create(Food foodtype) {
        switch (foodtype) {
            case TRAWA -> {
                return new Farmland(Food.TRAWA, 100.0, 0.0, 0.0,
                        0.0,0.0,null, 0);
            }
            case KUKURYDZA -> {
                return new Farmland(foodtype, 100.0,100.0,
                        10.0,50.0,200.0,
                        FarmlandData.getFarmlandData(foodtype),20);
            }
            case KAPUSTA -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,100.0,
                        FarmlandData.getFarmlandData(foodtype),16);
            }
            case BURAKI -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,150.0,
                        FarmlandData.getFarmlandData(foodtype),20);
            }
            case SALATA -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,100.0,
                        FarmlandData.getFarmlandData(foodtype),10);
            }
            case MARCHEW -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,150.0,
                        FarmlandData.getFarmlandData(foodtype),25);
            }
            case ZIEMNIAKI -> {
                return new Farmland(foodtype, 100.0,100.0,
                        5.0,50.0,200.0,
                        FarmlandData.getFarmlandData(foodtype),16);
            }
            case OGORKI -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,100.0,
                        FarmlandData.getFarmlandData(foodtype),15);
            }
            case JABLKA -> {
                return new Farmland(foodtype, 100.0,30.0,
                        10.0,50.0,50.0,
                        FarmlandData.getFarmlandData(foodtype),10);
            }
            case ZBOZE -> {
                return new Farmland(foodtype, 100.0,100.0,
                        5.0,100.0,200.0,
                        FarmlandData.getFarmlandData(foodtype),20);
            }
            case PSZENICA -> {
                return new Farmland(foodtype, 100.0,100.0,
                        5.0,50.0,200.0,
                        FarmlandData.getFarmlandData(foodtype),20);
            }
            case POMIDORY -> {
                return new Farmland(foodtype, 100.0,20.0,
                        10.0,50.0,100.0,
                        FarmlandData.getFarmlandData(foodtype),10);
            }
            case RUKOLA -> {
                return new Farmland(foodtype, 100.0,50.0,
                        10.0,50.0,100.0,
                        FarmlandData.getFarmlandData(foodtype),12);
            }
            case RZODKIEWKA -> {
                return new Farmland(foodtype, 100.0,50.0,
                        5.0,50.0,50.0,
                        FarmlandData.getFarmlandData(foodtype),12);
            }
            case SZPINAK -> {
                return new Farmland(foodtype, 100.0,20.0,
                        10.0,50.0,50.0,
                        FarmlandData.getFarmlandData(foodtype),15);
            }
            default -> throw new IllegalStateException("Unexpected value: " + foodtype);
        }
    }
}
