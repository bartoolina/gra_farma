package com.company.game;

import com.company.farm.Farm;
import com.company.farm.FarmGenerator;
import com.company.menu.Menu;
import com.company.menu.MenuItem;

import java.util.*;

public class Game {
    Menu menu;
    Scanner scanner;
    int numberOfPlayers;
    int actualPlayer;
    List<Player> playerList;
    Random random;

    public Game() {
        actualPlayer = 0;
        random = new Random();
        scanner = new Scanner(System.in);
        menu = new Menu();
        menu.setTitle("Witam w grze \"Upośledzona Farma\".");
        menu.addItem(new MenuItem("Nowa gra.", this, "newGame"));
        menu.addItem(new MenuItem("Zapisz gre.", this, "saveGame"));
        menu.addItem(new MenuItem("Załaduj gre.", this, "loadGame"));
    }

    public void start() {
        menu.execute(false);
    }

    public void newGame() {
        System.out.print("Podaj ilosc graczy: ");
        String input = scanner.nextLine();
        numberOfPlayers = Integer.parseInt(input);
        playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Imie gracza nr " + (i + 1) + ": ");
            playerList.add(new Player(scanner.nextLine()));
        }
        startNewGame();
    }

    public void saveGame() {
        System.out.println("Wersja alfa. Opcja niedostępna.");
    }

    public void loadGame() {
        System.out.println("Wersja alfa. Opcja niedostępna.");
    }

    public void startNewGame() {
        Menu gameMenu = new Menu();
        gameMenu.setTitle("Menu Farma");
        gameMenu.addItem(new MenuItem("Zakup/sprzedaż farmy.", this, "buySellFarm"));
        gameMenu.addItem(new MenuItem("Zakup/sprzedaż ziemi uprawnej.", this, "buySellFarmland"));
        gameMenu.addItem(new MenuItem("Zbudowanie/zniszczenie budynku.", this, "buildDestroyBuilding"));
        gameMenu.addItem(new MenuItem("Zakup/sprzedaż zwierząt.", this, "buySellAnimal"));
        gameMenu.addItem(new MenuItem("Zakup/sprzedaż roślin", this, "buySellGoods"));
        gameMenu.addItem(new MenuItem("Sadzenie/zbiory upraw.", this, "plantHarvestFarmland"));
        gameMenu.addItem(new MenuItem("Sprawdz stan zapasow.", this, "checkWarehouse"));
        gameMenu.addItem(new MenuItem("Sprawdz stan zwierzeta.", this, "checkAnimals"));
        gameMenu.addItem(new MenuItem("Sprawdz stan pól uprawnych. ", this, "checkFarmlands"));
        gameMenu.execute(false);
    }

    public void buySellFarm() {
        Menu buySellFarmMenu = new Menu();
        buySellFarmMenu.setTitle("Zakup/sprzedaż farmy.");
        buySellFarmMenu.addItem(new MenuItem("Zakup nowej farmy", this, "buyFarm"));
        buySellFarmMenu.addItem(new MenuItem("Sprzedaż swojej farmy", this, "sellFarm"));
        buySellFarmMenu.execute(false);
    }

    public void buyFarm() {
        Menu buyFarm = new Menu();
        System.out.println("Posiadane pieniądze:" + playerList.get(actualPlayer).credits);
        buyFarm.setTitle("Lista aktualnie dostępnych farm:");
        List<Farm> buyFarmList = new ArrayList<>();
        int numberFarms = 5;
        FarmGenerator farmGenerator = new FarmGenerator();
        for (int i = 0; i < numberFarms; i++) {
            Farm farm = farmGenerator.newFarm();
            buyFarmList.add(farm);
            buyFarm.addItem(new MenuItem("<<<Nowa farma>>>" + farm.toStringBuy(),playerList.get(actualPlayer),"addFarm", farm));
        }
        buyFarm.execute(true);
    }

    public void sellFarm() {
        Menu farmMenu = new Menu();
        farmMenu.setTitle("Która farme chcesz sprzedać?");
        for (Farm farm : playerList.get(actualPlayer).farmList) {
            farmMenu.addItem(new MenuItem(farm.toStringSell(), playerList.get(actualPlayer),"removeFarm", farm));
        }
        farmMenu.execute(true);
    }

    public void buySellFarmland() {
    }

    public void buildDestroyBuilding() {
    }

    public void buySellAnimal() {
    }

    public void buySellGoods() {
    }

    public void plantHarvestFarmland() {
    }

    public void checkWarehouse() {
    }

    public void checkAnimals() {
    }

    public void checkFarmlands() {
    }
}
