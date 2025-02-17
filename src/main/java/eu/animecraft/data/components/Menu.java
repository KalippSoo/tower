package eu.animecraft.data.components;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu implements InventoryHolder {
    private Player player;
    private Inventory inventory;
    private Data data;
    public AnimeCraft instance;

    public Menu() {
    }

    public abstract String name();

    public abstract int size();

    public abstract void HandleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void set(Player player) {
        this.player = player;
        this.instance = AnimeCraft.instance;
        this.data = this.instance.getDataManager().getPlayerData().get(player.getUniqueId());
        this.inventory = Bukkit.createInventory(this, this.size(), this.name());
        this.setMenuItems();
        player.openInventory(this.inventory);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Data getData() {
        return this.data;
    }
}
