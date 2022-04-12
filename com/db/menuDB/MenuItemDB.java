package com.db.menuDB;
import java.util.List;

import com.db.DB;
import com.models.MenuItem;

public class MenuItemDB implements DB<MenuItem> {


    @Override
    public boolean createEntry(MenuItem entry) {
        
        return false;
    }

    @Override
    public List<MenuItem> findAllEntries() {
        
        return null;
    }

    public MenuItem findSingleEntry(String itemname)
    {
        List<MenuItem> items = findAllEntries();
        if (items != null)
        for (MenuItem menuItem : items) {
            if (menuItem.getName() == itemname) return menuItem;
        }
        return null;
    }

    public boolean deleteEntry(MenuItem item)
    {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return findAllEntries().size() == 0;
    }
    
}
