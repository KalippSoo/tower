package eu.animecraft.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.banner.PlayerBannerDropEvent;

public class BannerMenu extends Menu {
    int[] towerLocation = new int[]{12, 13, 14, 21, 22, 23};

    public BannerMenu() {
    }
    public String name() {
        return "Current Units";
    }

    public int size() {
        return 54;
    }

    public void HandleMenu(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        switch (item.getType()) {
        	case BARRIER:
        		player.closeInventory();
        		break;
        	case GLOWSTONE_DUST:
                player.closeInventory();
                break;
            case LIME_DYE:
            	
            	PlayerBannerDropEvent event = new PlayerBannerDropEvent(player, (item.getAmount()-1), 0);
            	if (!event.isCancelled()) {
            		Bukkit.getPluginManager().callEvent(event);
            	}
                
		default:
			break;
        }

    }

    public void setMenuItems() {
        int[] pane = new int[]{0, 1, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        int[] pane1 = new int[]{2, 3, 4, 5, 6, 11, 15, 20, 24, 29, 30, 31, 32, 33};

        int i;
        for(i = 0; i < pane.length; ++i) {
            this.getInventory().setItem(pane[i], Utils.createItem(Material.YELLOW_STAINED_GLASS_PANE, 1, " "));
        }

        for(i = 0; i < pane1.length; ++i) {
            this.getInventory().setItem(pane1[i], Utils.createItem(Material.ORANGE_STAINED_GLASS_PANE, 1, " ", new String[0]));
        }

        for(i = 0; i < this.towerLocation.length; ++i) {
            this.getInventory().setItem(this.towerLocation[i], instance.getTowerManager().currentBanner.get(i).towerItem);
        }

        this.getInventory().setItem(16, Utils.createItem(Material.LAPIS_LAZULI, 1, "&9Gems: &d" + this.instance.getDataManager().getPlayerData().get(this.getPlayer().getUniqueId()).gems));
        this.getInventory().setItem(49, Utils.createItem(Material.BARRIER, 1, "&cLeave"));
        this.getInventory().setItem(39, Utils.createItem(Material.LIME_DYE, 1, "&aBuy 1"));
        this.getInventory().setItem(41, Utils.createItem(Material.LIME_DYE, 10, "&aBuy 10"));
    }
}
