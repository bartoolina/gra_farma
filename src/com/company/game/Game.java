package com.company.game;

import com.company.animal.Animal;
import com.company.animal.AnimalFactory;
import com.company.animal.AnimalType;
import com.company.building.*;
import com.company.farm.Farm;
import com.company.farm.FarmGenerator;
import com.company.farmland.Farmland;
import com.company.farmland.Land;
import com.company.farmland.LandFactory;
import com.company.goods.Food;
import com.company.goods.Goods;
import com.company.goods.GoodsCost;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Player> playerList;
    private int numberOfPlayers;
    private int actualPlayer;
    private int weekNumber;

    Random random = new Random();
    private List<Animal> buyAnimalList;
    private List<Goods> buyGoodsList;
    private List<Farm> buyFarmList;

    public void starMenu() {
        int choice;
        while (true) {
            choice = choiceMenu("Witam w grze \"Upośledzona Farma\"", new String[]{"Nowa gra", "Załaduj gre", "Zapisz gre"});
            switch (choice) {
                case 1 -> newGame();

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
        buyAnimalList = new ArrayList<>();
        generateNewAnimals();
        buyFarmList = new ArrayList<>();
        generateNewFarms();
        buyGoodsList = new ArrayList<>();
        generateGoodsCost();
        weekNumber = 0;
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
                    "Zakończ tydzień"
            });
            switch (choice) {
                case 1 -> buySellFarm();
                case 2 -> buySellFarmland();
                case 3 -> buildDestroyBuilding();
                case 4 -> buySellAnimals();
                case 5 -> buySellGoods();
                case 6 -> plantHarvestGoods();
                case 7 -> checkWarehouse();
                case 8 -> checkAnimals();
                case 9 -> checkFarmlands();
                case 10 -> NextPlayer();
                case 11 -> {
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
        Farm farm = chooseFarm();
        if (farm == null) return;
        int choice;
        while (true) {
            choice = choiceMenu("Sadzenie/zbiory upraw.", new String[]{
                    "Sadzenie upraw",
                    "Zebranie upraw"
            });
            switch (choice) {
                case 1 -> plant(farm);
                case 2 -> harvest(farm);
                case 3 -> {
                    return;
                }
            }
        }
    }

    private void harvest(Farm farm) {

    }

    private void plant(Farm farm) {
        Farmland farmland = chooseFarmland(farm.getFarmlandList());
        String input = "t";
        do{
            if (farmland == null) return;
            else if (!farmland.getFoodType().equals(Food.TRAWA)) {
                System.out.println("Na tym polu rośnie już " + farmland.getFoodType() + ". Czy chcesz na tym polu posadzić coś nowego?[t/n]");
                input = scanner.nextLine();
                if (input.equals("n") || input.equals("N")) return;
            }
        } while (!(input.equals("t") || input.equals("T")));

        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"Rodzaj rośliny", "Koszt zasadzenia", "Koszt utrzymania", "Koszt zebrania", "Ilość zebrnia jedzienia", "Ile rośnie [tygodni]", "W jakich tygoniach można zasadzić"});

        LandFactory landFactory = new LandFactory();
        List<Land> landList = new ArrayList<>();
        for (Food food : Food.values()) {
            if (food == Food.TRAWA || food == Food.SIANO) continue;
            Land land = landFactory.create(food);
            menu.add(new String[]{
                    land.getFoodType().toString(),
                    land.getCostPrepare().toString(),
                    land.getCostWeekly().toString(),
                    land.getCostHarvest().toString(),
                    land.getAmountOfGoods().toString(),
                    land.getWeeksGrowing().toString(),
                    land.getWeeksPlanting().toString()
            });
            landList.add(land);
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;

        playerList.get(actualPlayer).plantOnFarmland(farm, farmland, landList.get(choice-1), 20);
    }

    private void buySellGoods() {
        Farm farm = chooseFarm();
        int choice;
        while (true) {
            choice = choiceMenu("Zakup/sprzedaż zapasów.", new String[]{
                    "Zakup zapasów",
                    "Sprzedaż zapasów"
            });
            switch (choice) {
                case 1 -> buyGoods(farm);
                case 2 -> sellGoods(farm);
                case 3 -> {
                    return;
                }
            }
        }

    }

    private void sellGoods(Farm farm) {
        List<Warehouse> warehouses = playerList.get(actualPlayer).getWarehouses(farm);
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"magazyn", "towar", "dostępna ilość", "Cena za kg"});

        for (int i = 0; i < warehouses.size(); i++) {
            for (Goods goods : warehouses.get(i).getGoodsList()) {
                Double price = 0.0;
                for (Goods buySellGoods : buyGoodsList) {
                    if (buySellGoods.getFoodType() == goods.getFoodType()) {
                        goods.cost = buySellGoods.cost;
                    }
                }

                menu.add(new String[]{
                        "magazyn nr " + (i + 1),
                        goods.getFoodType().toString(),
                        String.format("%.2f", goods.amountOfFood),
                        String.format("%.2f$", goods.cost)
                });
            }
        }

        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        Double input;
        do {
            System.out.println("Ile kg chcesz sprzedac?");
            input = getInput(buyGoodsList.get(choice - 1).amountOfFood);
        } while (input == -1.0);

        Warehouse selectedWarehouse = warehouses.get(0);
        for (Warehouse warehouse : warehouses) {
            if (choice > warehouse.getGoodsList().size()) {
                choice -= warehouse.getGoodsList().size();
            } else {
                selectedWarehouse = warehouse;
                break;
            }
        }

        Goods goodsIn = selectedWarehouse.getGoodsList().get(choice - 1);
        Double amountOfGoodsBefore = goodsIn.amountOfFood;
        if (playerList.get(actualPlayer).sellGoods(farm, goodsIn, selectedWarehouse, input)) {
            for (Goods goods1 : buyGoodsList) {
                if (goods1.getFoodType().equals(goodsIn.getFoodType())) {
                    //poprawic
                    goods1.amountOfFood += (amountOfGoodsBefore - goodsIn.amountOfFood);
                }
            }
        }
    }

    private void buyGoods(Farm farm) {
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"towar", "dostępna ilość", "Koszt za kg"});

        for (Goods goods : buyGoodsList) {
            menu.add(new String[]{
                    goods.getFoodType().toString(),
                    String.format("%.2f", goods.amountOfFood),
                    String.format("%.2f$", goods.cost)
            });
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        Double input;
        do {
            System.out.println("Ile kg chcesz kupić?");
            input = getInput(buyGoodsList.get(choice - 1).amountOfFood);
        } while (input == -1.0);

        List<Warehouse> warehouses = playerList.get(actualPlayer).getWarehouses(farm);

        Food foodtype = buyGoodsList.get(choice - 1).getFoodType();
        Goods goods = new Goods(buyGoodsList.get(choice - 1).getFoodType(), input, buyGoodsList.get(choice - 1).cost);
        if (warehouses.size() == 0) {
            System.out.println("Nie masz żadnych budynków dla " + foodtype);
        } else if (warehouses.size() == 1) {
            if (playerList.get(actualPlayer).buyGoods(farm, goods, warehouses.get(0)))
                buyGoodsList.get(choice - 1).amountOfFood -= (input - goods.amountOfFood);
        } else {
            Warehouse destinationHouse = chooseWarehose(warehouses);
            if (playerList.get(actualPlayer).buyGoods(farm, goods, destinationHouse))
                buyGoodsList.get(choice - 1).amountOfFood -= (input - goods.amountOfFood);
        }
    }

    private void buySellFarm() {
        int choice;
        while (true) {
            choice = choiceMenu("Zakup/sprzedaż farmy.", new String[]{
                    "Zakup nowej farmy",
                    "Sprzedaż swojej farmy"
            });
            switch (choice) {
                case 1 -> buyFarm();
                case 2 -> sellFarm();
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

        for (Farm farm : buyFarmList) {
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
        if (playerList.get(actualPlayer).addFarm(buyFarmList.get(choice - 1)))
            buyFarmList.remove(choice - 1);


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
                    case 1 -> playerList.get(actualPlayer).buyFarmland(farm);
                    case 2 -> sellFarmland(farm);
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
                    farmland.getFoodType().toString(),
                    farmland.cost.toString()
            });
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        playerList.get(actualPlayer).sellFarmland(farm.getFarmlandList().get(choice - 1));
    }

    private void buildDestroyBuilding() {
        Farm farm = chooseFarm();
        if (farm == null) return;
        else {
            int choice;
            while (true) {
                choice = choiceMenu("Co chesz zrobic?", new String[]{"Wybudować nowy budynek", "Zburzyć istniejący budynek"});
                switch (choice) {
                    case 1 -> addBuilding(farm);
                    case 2 -> removeBuilding(farm);
                    case 3 -> {
                        return;
                    }

                }
            }
        }
    }

    private void removeBuilding(Farm farm) {
        System.out.println("Które budynek chcesz zburzyć?");
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"Typ budynku", "Ilość zwierząt/towaru", "Koszt zburzenia"});
        for (Building building : farm.getBuildingList()) {
            menu.add(new String[]{
                    building.buildingType.toString(),
                    String.format("%.0f", building.getCapacity()),
                    String.format("%.2f$", building.costToDestroy)
            });
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        playerList.get(actualPlayer).removeBuilding(farm, farm.getBuildingList().get(choice - 1));

    }

    private void addBuilding(Farm farm) {
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"typ budynku", "wielkość", "przeznaczenie", "pojemność", "Koszt"});
        BuildingFactory buildingFactory = new BuildingFactory();
        List<Building> buildings = new ArrayList<>();
        for (BuildingType buildingType : BuildingType.values()) {
            for (int i = 1; i < 4; i++) {
                Building building = buildingFactory.create(buildingType, i);
                buildings.add(building);
                menu.add(new String[]{
                        building.buildingType.toString(),
                        String.format("%d", i),
                        building.getClass() == AnimalHouse.class ? ((AnimalHouse) building).acceptedAnimals.toString() : "",
                        String.format("%.0f", building.getFreeCapacity()),
                        String.format("%.2f$", building.cost),

                });

            }
        }
        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        playerList.get(actualPlayer).buyBuilding(buildings.get(choice - 1), farm);

    }

    private void buySellAnimals() {
        Farm farm = chooseFarm();
        if (farm == null) return;
        else {
            int choice;
            while (true) {
                choice = choiceMenu("Co chesz zrobic?", new String[]{"Kupić zwierze", "Sprzedać zwierze"});
                switch (choice) {
                    case 1 -> buyAnimal(farm);
//                    case 2 -> sellAnimal(farm);
                    case 3 -> {
                        return;
                    }

                }
            }
        }
    }

    private void buyAnimal(Farm farm) {
        List<String[]> menu = new ArrayList<>();
        menu.add(new String[]{"Zwierze", "wiek", "waga", "jedzenie", "Koszt"});

        for (Animal animal : buyAnimalList) {
            menu.add(new String[]{
                    animal.species.toString(),
                    animal.getAge().toString(),
                    String.format("%.2f", animal.getWeight()),
                    animal.acceptedFood.toString(),
                    animal.cost.toString()
            });
        }


        int choice = tableMenu(menu);
        if (choice == menu.size()) return;
        AnimalType animalType = buyAnimalList.get(choice - 1).species;
        List<AnimalHouse> animalHouse = playerList.get(actualPlayer).getAnimalHouses(farm, animalType);
        if (animalHouse.size() == 0) {
            System.out.println("Nie masz żadnych budynków dla " + animalType);
        } else if (animalHouse.size() == 1) {
            if (playerList.get(actualPlayer).buyAnmial(buyAnimalList.get(choice - 1), farm, animalHouse.get(0)))
                buyAnimalList.remove(choice - 1);
        } else {
            AnimalHouse destinationHouse = chooseAnimalHouse(animalHouse);
            if (playerList.get(actualPlayer).buyAnmial(buyAnimalList.get(choice - 1), farm, destinationHouse))
                buyAnimalList.remove(choice - 1);
        }


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
            return playerList.get(actualPlayer).getFarmList().get(choice - 1);
        }

    }

    private AnimalHouse chooseAnimalHouse(List<AnimalHouse> animalHouseList) {
        if (animalHouseList.size() < 1) {
            System.out.println("Nie posiadasz żadnej domu dla zwierząt!");
            return null;
        } else if (animalHouseList.size() == 1) {
            return animalHouseList.get(0);
        } else {
            System.out.println("Wybierz dom dla zwierzecia");
            List<String[]> menu = new ArrayList<>();
            menu.add(new String[]{"ilosc wolnego miejsca", animalHouseList.get(0).acceptedAnimals.get(0).toString(), animalHouseList.get(0).acceptedAnimals.get(1).toString()});
            for (AnimalHouse animalHouse : animalHouseList) {
                menu.add(new String[]{
                        animalHouse.getFreeCapacity().toString(),
                        animalHouse.countAmial(animalHouse.acceptedAnimals.get(0)).toString(),
                        animalHouse.countAmial(animalHouse.acceptedAnimals.get(1)).toString()
                });
            }
            int choice = tableMenu(menu);
            if (choice == menu.size()) return null;

            return animalHouseList.get(choice - 1);
        }

    }

    private Warehouse chooseWarehose(List<Warehouse> warehouseList) {
        if (warehouseList.size() < 1) {
            System.out.println("Nie posiadasz żadnego magazynu!");
            return null;
        } else if (warehouseList.size() == 1) {
            return warehouseList.get(0);
        } else {
            System.out.println("Wybierz magazyn");
            List<String[]> menu = new ArrayList<>();

            menu.add(new String[]{"ilosc wolnego miejsca"});
            for (Warehouse warehouse : warehouseList) {
                menu.add(new String[]{
                        warehouse.getFreeCapacity().toString()
                });
            }
            int choice = tableMenu(menu);
            if (choice == menu.size()) return null;

            return warehouseList.get(choice - 1);
        }

    }

    private Farmland chooseFarmland(List<Farmland> farmlandList) {
        if (farmlandList.size() < 1) {
            System.out.println("Nie posiadasz żadnego pola uprawnego!");
            return null;
        } else if (farmlandList.size() == 1) {
            return farmlandList.get(0);
        } else {
            System.out.println("Wybierz pole uprawne");
            List<String[]> menu = new ArrayList<>();

            menu.add(new String[]{"Rodzaj rośliny", "Koszt przygotowania", "Koszt zbioru", "Koszt utrzymania", "Ile można zebrać", "Kiedy zasadzone", "Jak długo rośnie", "Kiedy można zasiać"});
            for (Farmland farmland : farmlandList) {
                menu.add(new String[]{
                        farmland.getFoodType().toString(),
                        farmland.getCostPrepare().toString(),
                        farmland.getCostHarvest().toString(),
                        farmland.getCostWeekly().toString(),
                        farmland.getAmountOfGoods().toString(),
                        farmland.getWeekPlanting().toString(),
                        farmland.getWeeksGrowing().toString(),
                        farmland.getWeeksPlanting().toString()
                });
            }
            int choice = tableMenu(menu);
            if (choice == menu.size()) return null;

            return farmlandList.get(choice - 1);
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

        String idFormater = "| %2d. |";
        String rowSeparator = String.format("+%s+%n", chars(maxWidth + 5, '-'));

        String printMenu = rowSeparator;
        for (int r = 0; r < table.size(); r++) {
            if (r == 0) printMenu += "|  ID |";
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
        int result;
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

    private Double getInput(Double maxNumber) {
        String input = null;
        Double result;
        try {
            input = scanner.nextLine();
            result = Double.parseDouble(input);

            if (result < 0.0 || result > maxNumber)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("\nError: '" + input + "' to za dużo. Nie ma tyle na aukcji.");
            result = -1.0;
        }
        return result;
    }

    private String chars(int lenght, char c) {
        return CharBuffer.allocate(lenght).toString().replace('\0', c);
    }

    private void generateGoodsCost() {
        buyGoodsList.clear();
        for (Food food : Food.values()) {
            if (food == Food.TRAWA) continue;
            Double amount = random.nextDouble() * 100;
            Double initialPrice = GoodsCost.getGoodsCost(food);
            Double price = initialPrice + (random.nextDouble() * 2 - 1) * initialPrice;
            Goods goods = new Goods(food, amount, price);
            buyGoodsList.add(goods);
        }
    }

    private void generateNewFarms() {
        buyFarmList.clear();
        FarmGenerator farmGenerator = new FarmGenerator();
        int numberFarms = random.nextInt(numberOfPlayers) + 5;
        for (int i = 0; i < numberFarms; i++) {
            Farm farm = farmGenerator.newFarm();
            buyFarmList.add(farm);
        }
    }

    private void generateNewAnimals() {
        buyAnimalList.clear();
        AnimalFactory animalFactory = new AnimalFactory();
        int numberAnimals = random.nextInt(10) + 10;
        for (int i = 0; i < numberAnimals; i++) {
            int pick = random.nextInt(AnimalType.values().length);
            AnimalType animalType = AnimalType.values()[pick];
            Animal animal = animalFactory.create(animalType);
            buyAnimalList.add(animal);
        }
    }


    private void nextWeek() {
        generateGoodsCost();
        generateNewAnimals();
        generateNewFarms();
        weekNumber++;
    }

    private void NextPlayer() {
        actualPlayer++;
        if (actualPlayer == numberOfPlayers) {
            actualPlayer = 0;
            nextWeek();
        }
    }
}
