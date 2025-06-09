package eu.animecraft.menu;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.Rarity;

public class EvolutionMenu extends Menu{

	Tower tower;
	Tower evolveTower;
	public EvolutionMenu(Tower tower) {
		this.tower = tower;
		this.evolveTower = tower.transferStats();
	}
	
	@Override
	public String name() {
		return "Evolution";
	}

	@Override
	public int size() {
		return 27;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		
		if (e.getCurrentItem().getType() == Material.ANVIL) {
			int gold = Rarity.getGold(evolveTower.getRarity());
			if (getData().gold < gold) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, .5f, 1.5f);
				Utils.sendMessages(getPlayer(), "&cYou don't have enough gold !");
				return;
			}
			getData().gold -= gold;
			getData().getTowers().remove(tower);
			getData().getTowers().add(evolveTower);
			getPlayer().playSound(getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1.5f);
			getPlayer().closeInventory();
			Utils.sendMessages(getPlayer(), "&aYou have evolve " + tower.displayName() + " &ato " + evolveTower.displayName());
		}
		
	}

	@Override
	public void setMenuItems() {
		
		for (int i = 0; i < getInventory().getSize(); i++) {
			getInventory().setItem(i, Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, ""));
		}

		getInventory().setItem(12, tower.towerItem.clone());
		getInventory().setItem(13, Utils.createItem(Material.ANVIL, 1, "&aEvolve", "&7Making this evolution means", "&7That you'll lose your current", "&7Unit, but have the evolved one !", "", "&eClick here to forge !"));
		getInventory().setItem(14, evolveTower.towerItem.clone());
	}

	
	
}
