package eu.animecraft.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.Trait;

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
                	if (!getData().reroll()) {
                		getPlayer().closeInventory();
                		getPlayer().sendTitle("", "&cYou don't have any rerolls !", 5, 40, 5);
                		return;
                    }
                    Trait newTrait = Trait.getTraitResult();
                    tower.setTrait(newTrait);
                    getInventory().setItem(13, tower.towerItem);
                    Utils.changeItem(getInventory().getItem(31), "&fCurrent: "+tower.getTrait().getName(), "&fYou can reroll " + getData().rerolls + " times !");
                    break;
                case PAPER:
                	getPlayer().openBook(
                			Utils.book(
                			"Force \n+15% Damage",
                			"Vision \n+15% Range",
                			"Agility \n-15% Cooldown",
                			"Skilled \n+10% Damage\n-10% Cooldow\n+10% Range",
                			"Deadly \n+10% Damage\n-25% Cooldow",
                			"Quickleaner \n+45% experience received",
                			"Star \n+30% Damage\n-15% Cooldown\n+10% Range",
                			"Particular \n-15% Damage\n-65% Cooldown\nSet the max placement to 2 (if max placement is 1 for the current unit it will stay at 1)",
                			"Unique \n+400% Damage\n-25% Cooldown\n+20% Range\nSet the max placement to 1"
                			));
                	break;
                case BARRIER:
                	getData().towerMenu(getPlayer(), 0);
                    break;
			default:
				break;
            }

        }

    }

    @Override
    public void setMenuItems() {
        //Center 13
        getInventory().setItem(13, tower.towerItem);
        getInventory().setItem(31, Utils.createItem(Material.GREEN_CONCRETE, 1, "&fCurrent: "+tower.getTrait().getRarity().getColor()+tower.getTrait().name().toUpperCase(), "&fYou can reroll " + getData().rerolls + " times !"));

        for (Trait trait : Trait.values()) {
        	if (trait.name().equals("none"))continue;
        	
        }

        getInventory().setItem(23, Utils.createItem(Material.PAPER, 1, "&aTrait list:",
                "&eClick to see more details"));
        getInventory().setItem(40, Utils.createItem(Material.BARRIER, 1, "&fBack to selection", ""));

    }
}
