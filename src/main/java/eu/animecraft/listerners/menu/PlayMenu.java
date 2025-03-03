package eu.animecraft.listerners.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;

public class PlayMenu extends Menu{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Select...";
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 54;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		switch (e.getCurrentItem().getType()) {
		case GRASS_BLOCK:
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public void setMenuItems() {
		getInventory().setItem(0, Utils.createItem(Material.GRASS_BLOCK, 1, "&7Level: 1 &c(TEST)"));
		
	}

	
	
}
