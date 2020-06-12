package com.company;

import com.company.menu.Menu;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Menu menu = new Menu();
        menu.show();
        menu.getLine(menu.getInput());
    }
}
