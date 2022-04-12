package com.views;

import com.controller.MenuItemController;
import com.db.menuDB.MenuItemDB;
import com.models.MenuItem;

public class MenuViews implements Views {

    @Override
    public void process() {
        int choice;
        MenuItemController controller = new MenuItemController();

        while (true) {
            choice = UserInputViews.getUserChoice(new String[] {
                    "Create new menu item",
                    "View/Update/Delete a menu item",
                    "View all menu items",
                    "Return to room service main menu"
            });

            switch (choice) {
                case 1:
                    if (controller.manageCreateEntry() == null)
                        System.out.println("Something went wrong trying to save the menu item");
                    else
                        System.out.println("Menu item has been successfully added to the menu");
                    break;

                case 2:
                    if (controller.manageMutateEntry() == null)
                        System.out.println("Something went wrong trying to save the menu item");
                    else
                        System.out.println("Menu item has been successfully added to the menu");
                    break;
                case 3:
                    for (MenuItem item : new MenuItemDB().findAllEntries()) {
                        System.out.println(item + "\n");
                    }
                default:
                    return;
            }
        }

    }

}
