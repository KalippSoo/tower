package eu.animecraft.listerners.menu;

import eu.animecraft.data.components.Menu;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SellingMenu extends Menu {

    @Override
    public String name() {
        return "Click to select";
    }

    @Override
    public int size() {
        return 54;
    }

    @Override
    public void HandleMenu(InventoryClickEvent e) {
        
    }

    @Override
    public void setMenuItems() {

    }
}
