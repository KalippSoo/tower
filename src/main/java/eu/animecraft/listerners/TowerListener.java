package eu.animecraft.listerners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.TowerComponent;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.PlayerBannerDropEvent;
import eu.animecraft.event.TowerPlaceEvent;
import eu.animecraft.listerners.menu.BuyBannerMenu;
import eu.animecraft.tower.Tower;

public class TowerListener extends EventListener{
	
	@EventHandler
	public void onPlayerPlaceTower(TowerPlaceEvent e) {
		Player player = e.getPlayer();
		Location targetlocation = e.getLocation();
		Tower tower = e.getTower();
		
		TowerComponent.check(player, tower);
		
		for (TowerComponent tc : instance.getTowerManager().activeTower.get(player)) {
			if (tc.tower == tower) {
				if (!tower.addCount())return;
				ArmorStand stand = player.getWorld().spawn(targetlocation, ArmorStand.class);
				stand.setSmall(true);
				stand.setCustomName(Utils.color(tower.displayName()));
				stand.setCustomNameVisible(true);
				stand.setBasePlate(false);
				stand.setArms(true);
				((LivingEntity)stand).getEquipment().setHelmet(tower.getItemVersion(0));
				((LivingEntity)stand).getEquipment().setChestplate(Utils.createItem(Material.LEATHER_CHESTPLATE, 1, null));
				((LivingEntity)stand).getEquipment().setLeggings(Utils.createItem(Material.LEATHER_LEGGINGS, 1, null));
				((LivingEntity)stand).getEquipment().setBoots(Utils.createItem(Material.LEATHER_BOOTS, 1, null));
				tc.towers.put(stand, 0);
			}
		}
	}
	
	@EventHandler
	public void onPlayerBannerDrop(PlayerBannerDropEvent e) {
		
		Player player = e.getPlayer();
		Data data = e.getData();
		
        if (data.getMaxStorageSize() < data.getTowers().size() + 1) {
            Utils.sendMessages(player, new String[]{"&cNot enough place in the storage"});
            return;
        }

        int price = 50;
        if (data.gems < price) {
            Utils.sendMessages(player, new String[]{"&cYou don't have enough gems !"});
            return;
        }
        
        List<Tower> towers = instance.getTowerManager().buyTower(e.getCount());
        for (Tower tower : towers) {
            tower.newStats();
            tower.setOwner(player);
            data.getTowers().add(tower);
        }
        data.rerolls += 15;
        BuyBannerMenu menu = new BuyBannerMenu(towers);
        menu.set(player);
	}

}
