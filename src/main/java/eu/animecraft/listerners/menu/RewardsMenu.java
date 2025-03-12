package eu.animecraft.listerners.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.arena.Reward;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;

public class RewardsMenu extends Menu{

	@Override
	public String name() {
		return "Rewards...";
	}

	@Override
	public int size() {
		return 9;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		
	}

	@Override
	public void setMenuItems() {
		int[] pane = {0,1,7,8};
		
		for (int i : pane) {
			getInventory().setItem(i, Utils.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, " "));
		}
		Reward reward = new Reward();
		getInventory().addItem(Utils.createItem(Material.CRIMSON_ROOTS, 1, reward.getRerolls()+" Rerolls!"));
		getInventory().addItem(Utils.createItem(Material.EXPERIENCE_BOTTLE, 1, reward.getPlayerExp()+" Experience!"));
		getInventory().addItem(Utils.createItem(Material.DIAMOND, 1, reward.getGems() + " Gems!"));
		
		getData().rerolls+=reward.getRerolls();
		getData().experience+=reward.getPlayerExp();
		getData().gems+=reward.getGems();
		
	}

}
