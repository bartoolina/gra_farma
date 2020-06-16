package com.company.game;

import com.company.building.Building;
import com.company.building.BuildingFactory;
import com.company.building.BuildingType;
import com.company.farm.Farm;
import com.company.farm.FarmGenerator;
import com.company.farmland.Farmland;
import com.company.goods.Food;

import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Player> playerList;
    private int numberOfPlayers;
    private int actualPlayer;

    public void starMenu() {
        int choice = 0;
        while (true) {
            choice = choiceMenu("Witam w grze \"Upośledzona Farma\"", new String[]{"Nowa gra", "Załaduj gre", "Zapisz gre"});
            switch (choice) {
                case 1 -> {
                    newGame();
                }
                case 2 -> {
                    //loadGame();
                }
                case 3 -> {
                    //saveGame();
                }
                case 4 -> {
                    return;
                }

            }
        }

    }

    private void newGame() {
        System.out.print("Podaj ilość graczy [1-8]: ");
        numberOfPlayers = getInput(8);
        playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Imie gracza nr " + (i + 1) + ": ");
            playerList.add(new Player(scanner.nextLine()));
        }
        mainMenuGame();
    }

    private void mainMenuGame() {
        int choice;
        while (true) {
            choice = choiceMenu(playerList.get(actualPlayer).name + ": Główne Menu", new String[]{
                    "Zakup/sprzedaż farmy.",
                    "Zakup/sprzedaż ziemi uprawnej",
                    "Zbudowac/zniszczyc budynek",
                    "Zakup/sprzedaż zwierząt",
                    "Zakup/sprzedaż roślin",
                    "Sadzenie/zbiory upraw",
                    "Sprawdz stan zapasow",
                    "Sprawdz stan zwierzeta",
                    "Sprawdz stan pól uprawnych",
            });
            switch (choice) {
                case 1 -> {
                    buySellFarm();
                }
                case 2 -> {
                    buySellFarmland();
                }
                case 3 -> {
                    buildDestroyBuilding();
                }
                case 4 -> {
                    buySellAnimals();
                }
                case 5 -> {
                    buySellGoods();
                }
                case 6 -> {
                    plantHarvestGoods();
                }
                case 7 -> {
                    checkWarehouse();
                }
                case 8 -> {
                    checkAnimals();
                }
                case 9 -> {
                    checkFarmlands();
                }
                case 10 -> {
                    return;
                }

            }
        }
    }

    private void checkFarmlands() {

    }

    private void checkAnimals() {

    }

    private void checkWarehouse() {

    }

    private void plantHarvestGoods() {

    }

    private void buySellGoods() {

    }

    private void buySellAnimals() {

    }

    private void buySellFarm() {
        int choice;
        while (true) {
            choice = choiceMenu("Zakup/sprzedaż farmy.", new String[]{
                    "Zakup nowej farmy",
                    "Sprzedaż swojej farmy"
            });
            switch (choice) {
                case 1 -> {
                    buyFarm();
                }
                case 2 -> {
                    sellFarm();
                }
                case 3 -> {
                    return;
                }
            }
        }
    }

    private void buyFarm() {
        System.out.println("Posiadane pięniądze: " + String.format("%.2f", playerList.get(actualPlayer).credits) + "$");

        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"max miejsce pod budynki", "użyte miejsce", "Budynki", "max pól uprawnych", "pola uprawne", "Koszt"});

        FarmGenerator farmGenerator = new FarmGenerator();
        List<Farm> farmList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Farm farm = farmGenerator.newFarm();
            farmList.add(farm);
            menu.add(new String[]{
                    farm.getBuildingMaxSpaces().toString(),
                    farm.getBuildingUsedSpaces().toString(),
                    farm.getBuildingList().toString(),
                    farm.getFarmlandMaxSpaces().toString(),
                    farm.getFarmlandList().toString(),
                    farm.getValue().toString()
            });
        }


        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        playerList.get(actualPlayer).addFarm(farmList.get(choice - 1));


    }

    private void sellFarm() {
        Farm farm = chooseFarm();
        if (farm == null) return;
        else playerList.get(actualPlayer).removeFarm(farm);
    }

    private void buySellFarmland() {
        Farm farm = chooseFarm();
        if (farm == null) return;
        else {
            int choice;
            while (true) {
                choice = choiceMenu("Co chesz zrobic?", new String[]{"Kupić pole uprawne", "Sprzedać pole uprawne"});
                switch (choice) {
                    case 1 -> {
                        playerList.get(actualPlayer).buyFarmland(farm);
                    }
                    case 2 -> {
                        sellFarmland(farm);
                    }
                    case 3 -> {
                        return;
                    }

                }
            }
        }
    }

    private void sellFarmland(Farm farm) {
        System.out.println("Które pole uprawne chcesz sprzedać?");
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"Typ pola", "Cena"});
        for (Farmland farmland : farm.getFarmlandList()) {
            menu.add(new String[]{
                    farmland.getFoodType(),
                    farmland.cost.toString()
            });
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        farm.removeFarmland(farm.getFarmlandList().get(choice - 1));
    }

    private void buildDestroyBuilding() {
        Farm farm = chooseFarm();
        if (farm == null) return;
        else {
            int choice;
            while (true) {
                choice = choiceMenu("Co chesz zrobic?", new String[]{"Wybudować nowy budynek", "Zburzyć istniejący budynek"});
                switch (choice) {
                    case 1 -> {
                        addBuilding(farm);
                    }
                    case 2 -> {
                        removeBuilding(farm);
                    }
                    case 3 -> {
                        return;
                    }

                }
            }
        }
    }

    private void addBuilding(Farm farm) {
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"typ budynku", "wielkość", "przeznaczenie", "pojemność", "Koszt"});
        BuildingFactory buildingFactory = new BuildingFactory();
        for (BuildingType buildingType : BuildingType.values()) {
            for (int i=1;i<4;i++){
                Building building = buildingFactory.create(buildingType,i);
                menu.add(new String[]{
                        building.buildingType.toString(),
                        String.format("%d", i),
                        building.
                });

            }
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return null;
    }

    private Farm chooseFarm() {
        if (playerList.get(actualPlayer).getFarmList().size() < 1) {
            System.out.println("Nie posiadasz żadnej farmy!");
            return null;
        } else if (playerList.get(actualPlayer).getFarmList().size() == 1) {
            return playerList.get(actualPlayer).getFarmList().get(0);
        } else {
            System.out.println("Najpierw wybierz farme");
            List<String[]> menu = new ArrayList<>();
            menu.add(new String[]{"max miejsce pod budynki", "użyte miejsce", "Budynki", "max pól uprawnych", "pola uprawne", "Koszt"});
            for (Farm farm : playerList.get(actualPlayer).getFarmList()) {
                menu.add(new String[]{
                        farm.getBuildingMaxSpaces().toString(),
                        farm.getBuildingUsedSpaces().toString(),
                        farm.getBuildingList().toString(),
                        farm.getFarmlandMaxSpaces().toString(),
                        farm.getFarmlandList().toString(),
                        farm.getValue().toString()
                });
            }
            int choice = tableMenu(menu);
            if (choice == menu.size()) return null;
            Farm farm = playerList.get(actualPlayer).getFarmList().get(choice - 1);
            return farm;
        }

    }

    private int choiceMenu(String title, String[] menuItem) {
        int maxSize = 0;
        maxSize = Math.max(title.length(), maxSize);
        for (String item : menuItem) {
            maxSize = Math.max(item.length() + 3, maxSize);
        }
        String titleFormater = "| %-" + (maxSize) + "s |%n";
        String itemFormater = "| %d. %-" + (maxSize - 3) + "s |%n";
        String rowSeparator = String.format("+%s+%n", chars(maxSize + 2, '-'));

        String printMenu = rowSeparator;
        printMenu += String.format(titleFormater, title);
        printMenu += rowSeparator;
        for (int i = 1; i <= menuItem.length; i++) {
            printMenu += String.format(itemFormater, i, menuItem[i - 1]);
            if (i == menuItem.length) {
                printMenu += String.format(itemFormater, i + 1, "Cofnij");
            }
        }
        printMenu += rowSeparator;

        int result = 0;
        while (result == 0) {
            System.out.println(printMenu);
            System.out.print(">");
            result = getInput(menuItem.length + 1);
        }
        return result;
    }

    private int tableMenu(List<String[]> table) {
        List<Integer> colWidth = new ArrayList<>();
        int maxWidth;
        int column = 0;
        for (String[] row : table) {

            for (String col : row) {
                if (table.indexOf(row) == 0) colWidth.add(0);

                colWidth.set(column, Math.max(colWidth.get(column), col.length()));

                column++;
            }
            column = 0;
        }

        maxWidth = colWidth.stream().mapToInt(integer -> (integer + 3)).sum();

        String idFormater = "| %d. |";
        String rowSeparator = String.format("+%s+%n", chars(maxWidth + 4, '-'));

        String printMenu = rowSeparator;
        for (int r = 0; r < table.size(); r++) {
            if (r == 0) printMenu += "| ID |";
            else printMenu += String.format(idFormater, r);

            for (int c = 0; c < table.get(r).length; c++) {
                String itemFormater = " %-" + colWidth.get(c) + "s |";
                printMenu += String.format(itemFormater, table.get(r)[c]);
            }
            printMenu += "\n";
            if (r == 0) printMenu += rowSeparator;
        }
        printMenu += rowSeparator;
        printMenu += table.size() + ". Cofnij";

        int result = 0;
        while (result == 0) {
            System.out.println(printMenu);
            System.out.print(">");
            result = getInput(table.size());
        }
        return result;
    }

    private int getInput(Integer maxNumber) {
        String input = null;
        int result = 0;
        try {
            input = scanner.nextLine();
            result = Integer.parseInt(input);

            if (result < 1 || result > maxNumber)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("\nError: '" + input + "' to nie odpowiada żadnej opcji z menu.");
            result = 0;
        }
        return result;
    }

    private String chars(int lenght, char c) {
        return CharBuffer.allocate(lenght).toString().replace('\0', c);
    }

    public void setNextPlayer() {
        actualPlayer++;
        if (actualPlayer == numberOfPlayers) actualPlayer = 0;
    }
}
