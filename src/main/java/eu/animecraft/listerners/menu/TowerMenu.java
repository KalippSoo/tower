package eu.animecraft.listerners.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ScrollMenu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class TowerMenu extends ScrollMenu {
    public TowerMenu(boolean rerollMenu) {
        this.rerollMenu = rerollMenu;
        this.maxItemInPage = 24;
    }

    public String name() {
        return !isSelling ? (getPlayer().getName()+"'s tower") : ("Click to remove");
    }

    public int size() { return 54; }

    public boolean rerollMenu;
    int preset = 27;
    
    public boolean isSelling = false;
    List<Tower> sellingTowers=new ArrayList<Tower>();
    
    public void HandleMenu(InventoryClickEvent e) {
    	switch (e.getCurrentItem().getType()){
        case PLAYER_HEAD:
            if (e.getClickedInventory() != e.getView().getTopInventory())return;
            
            /*
             * Get the tower via the information of the item, cancel event if tower equals null
             */
            Tower targetTower = AnimeCraft.instance.getTowerManager().getTowerByItemVersion(getPlayer(), e.getCurrentItem());
            if (targetTower == null)return;
            
            /*
             * z
             */
            if (rerollMenu){
                RerollMenu menu = new RerollMenu(targetTower);
                menu.set(getPlayer());
                return;
            }
            
            /*
             * Classic locked system
             */
            if (e.getClick()==ClickType.SHIFT_LEFT) {
            	targetTower.setLocked(!targetTower.locked);
            	set(getPlayer());
            	return;
            }
            
            /*
             * Selling system plus 
             */
            if (e.getClick()==ClickType.LEFT) {
            	if (!isSelling) {
            		if (getData().getListSelected().contains(targetTower.uuid.toString())) {
            			//Unequip
            			getData().removeTowerToListedSelection(targetTower);
            		}else {
            			if (getData().isSelectedFull()) {
            				Utils.sendMessages(getPlayer(), "We need to unequip a unit!");
            				return;
            			}
            			getData().addTowerToListedSelection(targetTower);
            		}
            		int i = 27;
                    for (String uuids : getData().getListSelected()){
                    	Tower tower = instance.getTowerManager().getTowerByUUID(getData(), uuids);
                        if (tower != null)
                        	e.getWhoClicked().getInventory().setItem(i, tower.towerItem.clone());
                        else
                        	e.getWhoClicked().getInventory().setItem(i, Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, "&fEmpty"));
                        i++;
                    }
        			set(getPlayer());
        			return;
            	}
            	if(isSelling) {
            		if (sellingTowers.contains(targetTower)) {
                		sellingTowers.remove(targetTower);
                		e.setCurrentItem(targetTower.towerItem.clone());
            		}else {
                		sellingTowers.add(targetTower);
                		Utils.changeItemWithCurrentLore(e.getCurrentItem(), null, "", "&c&lAbout to get deleted !");
            		}
            		return;
            	}
            }
            
            
            
            break;
        case RED_DYE:
            break;
        case GOLD_INGOT:
        	if (isSelling) {
        		isSelling = false;
        		if (sellingTowers.size() > 0) {
        			if (e.getClick()==ClickType.RIGHT) {
        				Utils.sendMessages(getPlayer(), "&aYou cancelled all your sellings !");
        			}else if (e.getClick()==ClickType.LEFT) {
                		getData().getTowers().removeAll(sellingTowers);
                		Utils.sendMessages(getPlayer(), "&aYou sold " + sellingTowers.size() + " units !");
        			}
            		sellingTowers.clear();
        		}
        	}else {
            	isSelling = true;
            	page = 0;
        	}
        	set(getPlayer());
            return;
        case PAPER:
            break;
        case ARROW:
        	if (Utils.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("En bas")) {
        		if (!((index + 1) >= getData().getTowers().size())){
                    page = page + 1;
                    super.set(getPlayer());
                }
        	}else if (Utils.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("En haut")){
        		
        		if (page!=0) {
                    page = page - 1;
                    super.set(getPlayer());
        		}
        	}
        	break;
	default:
		break;
    }
        
    }

    public void setMenuItems() {
        // 17 26 35 44
        int[] pane = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 18, 25, 26, 27, 34, 35, 36, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        if (rerollMenu && isSelling)isSelling=!isSelling;
        
        /*
         * Change pane color if selling mode is ON
         */
        if (isSelling) for(int i = 0; i < pane.length; ++i) this.getInventory().setItem(pane[i], Utils.createItem(Material.RED_STAINED_GLASS_PANE, 1, " "));
        else for(int i = 0; i < pane.length; ++i) this.getInventory().setItem(pane[i], Utils.createItem(Material.BLUE_STAINED_GLASS_PANE, 1, " "));
        
        if (page != 0)
            getInventory().setItem(8, Utils.createItem(Material.ARROW, 1, "&7En haut"));
        getInventory().setItem(53, Utils.createItem(Material.ARROW, 1, "&7En bas"));
	
        if (!rerollMenu){
            this.getInventory().setItem(17, Utils.createItem(Material.RED_DYE, 1, "&4&lUnequip all"));
            this.getInventory().setItem(26, Utils.createItem(Material.GOLD_INGOT, 1, "&f&lSell: "+((isSelling) ? "&a&lON" : "&c&lOFF")
            		, "", "&e&lRIGHT CLICK TO CANCEL", "&e&lLEFT CLICK TO CONFIRM"));
            this.getInventory().setItem(35, Utils.createItem(Material.PAPER, 1, "&f&lSearch", "&c&lWORKING IN PROGRESS!"));
            this.getInventory().setItem(44, Utils.createItem(Material.POLISHED_ANDESITE_STAIRS, 1, "&f&l???"));
        }
        List<String> uuids = new ArrayList<>();
        
        //Get the uuid of the towers to track it
        for (String towersUUID : getData().getListSelected()){
            for (Tower towers : getData().getTowers()){
                if (!towers.uuid.toString().equals(towersUUID))continue;
                uuids.add(towersUUID);
                if (rerollMenu)continue;
                ItemStack stack = towers.towerItem.clone();
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(Utils.color(meta.getDisplayName()));
                List<String> lines = new ArrayList<>(meta.getLore());
                if (!(lines.size() > 8)) {
                    lines.add(Utils.color(""));
                    lines.add(Utils.color("&a&lEQUIPED"));
                }
                meta.setLore(lines);
                stack.setItemMeta(meta);
                this.getInventory().addItem(new ItemStack[]{stack});

            }
        }
        
        for (int i = 0; i <= super.maxItemInPage; i++){
        	index = super.maxItemInPage * super.page + i;
        	if (index >= getData().getTowers().size())break;
        	Tower tower = getData().getTowers().get(index);
        	ItemStack item = tower.towerItem.clone();
        	if (tower.locked && isSelling) 
        		Utils.changeType(item, Material.BARRIER);
            if (uuids.contains(tower.uuid.toString()))continue;
            this.getInventory().addItem(new ItemStack[]{!sellingTowers.contains(tower)?item:Utils.changeItemWithCurrentLore(item, null, "", "&c&lAbout to get deleted !")});
        }
    }
}
