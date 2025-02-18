package eu.animecraft.listerners.menu;

import java.util.concurrent.ThreadLocalRandom;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.Rarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
            case GLOWSTONE_DUST:
                player.closeInventory();
                break;
            case LIME_DYE:

                if (getData().getMaxStorageSize() < this.getData().getTowers().size() + item.getAmount()) {
                    Utils.sendMessages(player, new String[]{"&cNot enough place in the storage"});
                    return;
                }

                int price = 50 * item.getAmount();
                if (this.getData().gems < price) {
                    Utils.sendMessages(player, new String[]{"&cYou don't have enough gems !"});
                    return;
                }

                StringBuilder builder = new StringBuilder();

                for(int i = 0; i < item.getAmount(); ++i) {
                    Tower tower = this.instance.getTowerManager().buyTower();
                    tower.newStats();
                    builder.append(tower.displayName() + " : &e(DPS " + (Utils.format.format((tower.fc/ (tower.fc/20))) + ")\n"));
                    getData().getTowers().add(tower);
                    if (tower.getRarity() != Rarity.MYTHIC && tower.getRarity() != Rarity.LIMITED)continue;
                }

                Utils.sendMessages(player, new String[]{builder.toString()});
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
            this.getInventory().setItem(this.towerLocation[i], AnimeCraft.instance.getTowerManager().availableTower.get(ThreadLocalRandom.current().nextInt(AnimeCraft.instance.getTowerManager().availableTower.size())).getItemVersion(1));
        }

        this.getInventory().setItem(16, Utils.createItem(Material.LAPIS_LAZULI, 1, "&9Gems: &d" + this.instance.getDataManager().getPlayerData().get(this.getPlayer().getUniqueId()).gems));
        this.getInventory().setItem(49, Utils.createItem(Material.BARRIER, 1, "&cLeave"));
        this.getInventory().setItem(39, Utils.createItem(Material.LIME_DYE, 1, "&aBuy 1"));
        this.getInventory().setItem(41, Utils.createItem(Material.LIME_DYE, 5, "&aBuy 5"));
    }
}
