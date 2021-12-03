package carsharing.controller;

import carsharing.view.menu.MenuItems;
import carsharing.view.menu.Menu;

public abstract class Controller {
    protected Menu<MenuItems> menu;
    private boolean leaveMenu = false;
    private static boolean exitProgram = false;

    protected void doLeave() { leaveMenu = true; }
    protected void doExit() { exitProgram = true; }

    protected abstract void doActionForItem(MenuItems item);

    public void run() {
        do {
            MenuItems item = menu.choose();
            doActionForItem(item);
        } while (!leaveMenu && !exitProgram);
    }
}
