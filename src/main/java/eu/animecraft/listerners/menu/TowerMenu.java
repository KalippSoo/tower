package eu.animecraft.listerners.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class TowerMenu extends Menu {
    public TowerMenu(boolean rerollMenu) {
        this.rerollMenu = rerollMenu;
    }

    public String name() {
        return getPlayer().getName()+"'s tower";
    }

    public int size() {
        return 54;
    }

    public boolean rerollMenu;
    int preset = 1;
    int searchMode = -1;

    public void HandleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()){
            case PLAYER_HEAD:
                if (e.getClickedInventory() == e.getView().getTopInventory()) {

                    int currentTowerInBar = e.getWhoClicked().getInventory().getHeldItemSlot() - preset;
                    Tower targetTower = AnimeCraft.instance.getTowerManager().getTowerByItemVersion(getData(), e.getCurrentItem());
                    if (targetTower == null)return;

                    if (rerollMenu){
                        RerollMenu menu = new RerollMenu(targetTower);
                        menu.set(getPlayer());
                        return;
                    }

                    e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getDisplayName());
                    getData().getListSelected().set(currentTowerInBar, getData().getListSelected().contains(targetTower.uuid.toString()) ? "none" : targetTower.uuid.toString());
                    e.getWhoClicked().getInventory().setItemInMainHand(e.getCurrentItem().getType() == Material.BARRIER ? Utils.createItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, "&fPlacement: " + (currentTowerInBar + preset), new String[]{"&eClick to open the tower menu"}) : e.getCurrentItem());
                }
                break;
            case RED_DYE:
                if (e.getClickedInventory() == e.getView().getTopInventory()) {
                    for (int i = 0; i<6;i++){
                        getData().getListSelected().set(i, "none");
                        e.getWhoClicked().getInventory().setItem(i+preset, Utils.createItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, "&fPlacement: " + (i + preset), new String[]{"&eClick to open the tower menu"}));
                    }
                }
                break;
            case GOLD_INGOT:
                //Open selling menu
                SellingMenu s = new SellingMenu();
                s.set(getPlayer());
                return;
            case PAPER:
                break;
		default:
			break;
        }
        this.set(this.getPlayer());
    }

    public void setMenuItems() {
        // 17 26 35 44
        int[] pane = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

        for(int i = 0; i < pane.length; ++i)
            this.getInventory().setItem(pane[i], Utils.createItem(Material.BLUE_STAINED_GLASS_PANE, 1, " "));

        if (!rerollMenu){
            this.getInventory().setItem(17, Utils.createItem(Material.RED_DYE, 1, "&4&lUnequip all"));
            this.getInventory().setItem(26, Utils.createItem(Material.GOLD_INGOT, 1, "&c&lSell"));
            this.getInventory().setItem(35, Utils.createItem(Material.PAPER, 1, "&f&lSearch"));
            this.getInventory().setItem(44, Utils.createItem(Material.POLISHED_ANDESITE_STAIRS, 1, "&f&l???"));
        }
        List<String> uuids = new ArrayList<>();

        //Get the uuid of the towers to track it
        for (String towersUUID : getData().getListSelected()){
            for (Tower towers : getData().getTowers()){
                if (!towers.uuid.toString().equals(towersUUID))continue;
                uuids.add(towersUUID);
                ItemStack stack = towers.getItemVersion(0);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(Utils.color(meta.getDisplayName()));
                List<String> lines = new ArrayList<>(meta.getLore());
                lines.add(Utils.color(""));
                lines.add(Utils.color("&cLeft click to unselected !"));
                meta.setLore(lines);
                stack.setItemMeta(meta);
                this.getInventory().addItem(new ItemStack[]{stack});

            }
        }
        for (Tower towers : getData().getTowers()){
            if (uuids.contains(towers.uuid.toString()))continue;
            this.getInventory().addItem(new ItemStack[]{towers.getItemVersion(0)});
        }
    }
}
