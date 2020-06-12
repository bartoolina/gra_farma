package com.company.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<String> msg;
    Scanner scanner = new Scanner(System.in);

    static final List<String> global_header = new ArrayList<>() {{
        add("+----------------+");
        add("|  Witam w grze  |");
        add("+----------------+");
    }};

    static final List<String> global_menu = new ArrayList<>() {{
        add("1. Zakup nowej farmy.");
        add("2. Zakup/Sprzedaż ziemi uprawnej.");
        add("3. Budowa/Zniszczenie budynku.");
        add("4. Zakup zwierząt lub roślin.");
        add("5. Posadzenie roślin.");
        add("6. Zbiory roślin.");
        add("7. Sprzedaż roślin lub zwierząt.");
        add("8. Sprawdzenie stanu zapasów.");
        add("9. Informacja o posiadanych zwierzętach.");
        add("10. Informacja o posiadanych sadzonkach i zasadzonych roślinach.");
    }};

    public void show() {
        printHeader();
        printMenu();
    }

    private void printMenu() {
        msg = global_menu;
        msg.forEach(System.out::println);
    }

    private void printHeader() {
        msg = global_header;
        msg.forEach(System.out::println);
    }

    public Integer getInput() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.println("wcisnales: " + choice);
        } catch (NumberFormatException e) {
            System.out.println("niehodlfsifd");
        }

        return choice;
    }

    public void getLine(Integer line) {
        System.out.println(msg.get(line));
    }
}
