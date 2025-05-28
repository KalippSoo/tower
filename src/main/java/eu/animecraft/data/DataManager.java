package eu.animecraft.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class DataManager {
    private Map<UUID, Data> playerData = new HashMap<>();

    public DataManager() {
    }

    public Map<UUID, Data> getPlayerData() {
        return this.playerData;
    }
    
    public static void changeInventory(Player player, int mode) {
    	
    	Data data = Utils.getData(player);
    	AnimeCraft instance = AnimeCraft.instance;
    	int i;
    	player.getInventory().clear();
    	switch (mode) {
		case 0:
	        player.getInventory().setItem(1, Utils.createItem(Material.CHEST, 1, "Open your menu"));
	        player.getInventory().setItem(3, Utils.createItem(Material.COMPASS, 1, "Teleport"));
	        player.getInventory().setItem(7, Utils.createItem(Material.EMERALD, 1, "Shop"));
	        i = 27;
            for (String uuids : data.getListSelected()){
            	Tower tower = instance.getTowerManager().getTowerByUUID(data, uuids);
                if (tower != null)
                	player.getInventory().setItem(i, tower.towerItem.clone());
                else
                	player.getInventory().setItem(i, Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, "&fEmpty"));
                i++;
            }
			break;
		case 1:
	        i = 3;
            for (String uuids : data.getListSelected()){
            	Tower tower = instance.getTowerManager().getTowerByUUID(data, uuids);
                if (tower != null)
                	player.getInventory().setItem(i, tower.towerItem.clone());
                i++;
            }
			player.getInventory().setItem(0, Utils.createItem(Material.RED_TERRACOTTA, 1, "&c&lCLICK TO LEAVE"));
			break;
		case 2:
	        i = 2;
            for (String uuids : data.getListSelected()){
            	Tower tower = instance.getTowerManager().getTowerByUUID(data, uuids);
                if (tower != null)
                	player.getInventory().setItem(i, tower.towerItem.clone());
                else
                	player.getInventory().setItem(i, Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, "&fEmpty"));
                i++;
            }
			break;
			
		}
    	
    }
}
