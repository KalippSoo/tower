package eu.animecraft.listerners.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.arena.Arena;
import eu.animecraft.arena.ArenaManager;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Play;

public class PlayMenu extends Menu{

	public Play play;
	public PlayMenu(Play play) {
		this.play = play;
	}
	
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
		case BARREL:
			this.play.setMode(1, e.getCurrentItem().getItemMeta().getDisplayName());
			break;

		default:
			break;
		}
		
	}

	@Override
	public void setMenuItems() {
		int[] slots = {0,1,2,9,11,18,20,27,29,36,38,45,47};
		int[] slotsPane = {3,4,5,6,7,8,12,17,21,26,30,35,39,44,48,49,50,51,52,53};
		
		int[] actPos= {10, 19, 28, 37, 46};
		
		for (int i : slots) {
			getInventory().setItem(i, Utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, 1, " "));
		}		
		for (int i : slotsPane) {
			getInventory().setItem(i, Utils.createItem(Material.ORANGE_STAINED_GLASS_PANE, 1, " "));
		}
		int i1 = 0;
		for (Arena arena : ArenaManager.arena) {
			getInventory().setItem(actPos[i1], arena.itemStack);
		}

		getInventory().setItem(46, Utils.createItem(Material.BARRIER, 1, "&aLEAVE"));
	}

	
	
}
