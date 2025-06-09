package eu.animecraft.menu;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class BuyBannerMenu extends Menu{
	
	List<Tower> tower;
	
	public BuyBannerMenu(List<Tower> tower) {
		this.tower = tower;
	}
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "You received...";
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 27;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		switch (e.getCurrentItem().getType()) {
		case BARRIER:
			getPlayer().closeInventory();
			break;

		default:
			break;
		}
	}
	
	int preset = 0;
	
	@Override
	public void setMenuItems() {
		
		int i[] = {0, 1, 7, 8, 9, 10, 16, 17, 18, 19, 25, 26};
		for (int in : i) {
			getInventory().setItem(in, Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, " "));
		}
		getInventory().setItem(22, Utils.createItem(Material.BARRIER, 1, "&cClose"));
		new BukkitRunnable() {
			int size = 0;
			@Override
			public void run() {
				
				if (size < tower.size()) {
					
					getInventory().addItem(tower.get(size).towerItem);
					if (tower.get(size).getRarity().ordinal()>=3) {
						getPlayer().playSound(getPlayer().getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 2f);
					}else {
						getPlayer().playSound(getPlayer().getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
					}
					size++;
				}
			
			
			}
		}.runTaskTimer(instance, 0, 5);
		
	}
	

}
