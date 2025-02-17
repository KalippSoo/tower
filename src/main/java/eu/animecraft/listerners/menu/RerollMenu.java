package eu.animecraft.listerners.menu;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.TowerManager;
import eu.animecraft.tower.tools.Trait;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class RerollMenu extends Menu {

    Tower tower;
    public RerollMenu(Tower tower){
        this.tower = tower;
        if (tower == null){
            getPlayer().closeInventory();
            Utils.sendMessages(getPlayer(), "&cAn error has closed the inventory, try again !");
        }
    }

    @Override
    public String name() {
        return "Time to Reroll";
    }

    @Override
    public int size() {
        return 45;
    }

    @Override
    public void HandleMenu(InventoryClickEvent e) {

        if (e.getClickedInventory() == this.getInventory()){

            switch(e.getCurrentItem().getType()){
                case GREEN_CONCRETE:
                    Trait newTrait = Trait.getTraitResult();
                    tower.setTrait(newTrait);
                    Utils.sendMessages(getPlayer(), "&c" + newTrait.name());
                    this.set(getPlayer());
                    break;
                case BARRIER:
                    TowerMenu menu = new TowerMenu(true);
                    menu.set(getPlayer());
                    break;
            }

        }

    }

    @Override
    public void setMenuItems() {
        //Center 13
        getInventory().setItem(13, tower.getItemVersion(2));
        getInventory().setItem(31, Utils.createItem(Material.GREEN_CONCRETE, 1, "&aCurrent: &e"+tower.getTrait().name().toUpperCase(), "&fYou can reroll 24 times !"));

        StringBuilder sb = new StringBuilder();
        Arrays.stream(Trait.values()).filter(trait -> {
            if (!trait.name().equals("none")){
                sb.append(trait.name().toUpperCase());
            }
            return false;
        }),

        getInventory().setItem(23, Utils.createItem(Material.PAPER, 1, "&aTrait list:",
                sb.toString(),
                "&eClick to see more details"));
        getInventory().setItem(40, Utils.createItem(Material.BARRIER, 1, "&fBack to selection", ""));

    }
}
