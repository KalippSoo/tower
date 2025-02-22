package eu.animecraft.listerners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.PlayerBannerDropEvent;
import eu.animecraft.event.TowerPlaceEvent;
import eu.animecraft.tower.Tower;

public class TowerListener extends EventListener{
	
	@EventHandler
	public void onPlayerPlaceTower(TowerPlaceEvent e) {
		Player player = e.getPlayer();
		Location targetlocation = e.getLocation();
		Tower tower = e.getTower();
		
		if (!tower.addCount())return;
		
		ArmorStand stand = player.getWorld().spawn(targetlocation, ArmorStand.class);
		stand.setSmall(true);
		stand.setCustomName(Utils.color(tower.displayName()));
		stand.setCustomNameVisible(true);
		stand.setBasePlate(false);
		stand.setArms(true);
		((LivingEntity)stand).getEquipment().setHelmet(tower.getItemVersion(0));
		List<Tower> activeTowers = instance.getTowerManager().activeTowers.get(player) != null ? instance.getTowerManager().activeTowers.get(player) : new ArrayList<>();
		
		tower.stand = stand;
		activeTowers.add(tower);
		instance.getTowerManager().activeTowers.put(player, activeTowers);
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
        
        Tower tower = instance.getTowerManager().buyTower();
        tower.newStats();
        data.getTowers().add(tower);
        Utils.sendMessages(player, "&fYou just droped " + tower.displayName());
        data.rerolls += 15;
	}

}
