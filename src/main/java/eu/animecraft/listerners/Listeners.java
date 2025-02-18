package eu.animecraft.listerners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import eu.animecraft.data.DocumentRelated;
import eu.animecraft.data.StatsReroll;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.listerners.menu.BannerMenu;
import eu.animecraft.listerners.menu.TowerMenu;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.Trait;

public class Listeners implements Listener {

    public void circle(Location loc, int radius) {
        for(int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; ++x) {
            for(int y = loc.getBlockY() - radius; y < loc.getBlockY() + radius; ++y) {
                for(int z = loc.getBlockZ() - radius; z < loc.getBlockZ() + radius; ++z) {
                    if ((loc.getBlockX() - x) * (loc.getBlockX() - x) + (loc.getBlockY() - y) * (loc.getBlockY() - y) + (loc.getBlockZ() - z) * (loc.getBlockZ() - z) <= radius * radius && loc.getBlock().getType() == Material.AIR) {
                        loc.getBlock().setType(Material.CYAN_WOOL);
                    }
                }
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().clear();
        if (!AnimeCraft.instance.getDataManager().getPlayerData().containsKey(e.getPlayer().getUniqueId())) {
            AnimeCraft.instance.getDataManager().getPlayerData().put(e.getPlayer().getUniqueId(), new Data());
        }

        Data data = AnimeCraft.instance.getDataManager().getPlayerData().get(player.getUniqueId());
        DocumentRelated.createGetDocument(player, data);

        if (data.getListSelected().isEmpty()){
            List<String> list = new ArrayList<>();
            for(int i = 1; i<= 6; i++){
                list.add("");
            }
            data.setListSelected(list);
        }

        Document stats = DocumentRelated.getSpecificDocument(player, "stats");
        Document collection = DocumentRelated.getSpecificDocument(player, "collection");
        Document inventory = DocumentRelated.getSpecificDocument(player, "inventory");

        Iterator<String> towers = inventory.getList("contents", String.class).iterator();

        while(towers.hasNext()){
            String[] info = ((String)towers.next()).split(",");
            int id = Integer.parseInt(info[0]);
            double damage = Double.parseDouble(info[1]);
            double reload = Double.parseDouble(info[2]);
            int range = Integer.parseInt(info[3]);
            double
                    a = Double.parseDouble(info[4]),
                    b = Double.parseDouble(info[5]),
                    c = Double.parseDouble(info[6]);
            boolean shiny = Boolean.parseBoolean(info[7]);
            Trait trait = Trait.valueOf(info[8]);
            int level = Integer.parseInt(info[9]);
            double experience = Double.parseDouble(info[10]);
            UUID uuid = UUID.fromString(info[11]);

            Tower tower = AnimeCraft.instance.getTowerManager().getTower(id);
            if (tower == null)continue;
            tower.uuid = uuid;
            tower.damage = (int) damage;
            tower.cooldown = (int) reload;
            tower.range = range;
            tower.setShiny(shiny);
            tower.setOwner(e.getPlayer());
            tower.setId(id);
            tower.lvlSystem.setLevel(level);
            tower.lvlSystem.addExp(experience);
            tower.statsReroll = new StatsReroll(a,b,c);
            tower.setTrait(trait == null ? Trait.none : trait);
            data.getTowers().add(tower);
        }

        String group = stats.getString("rank");

        data.setGroup(AnimeCraft.instance.getGroupManager().getGroup(group));
        player.setScoreboard(AnimeCraft.instance.getGroupManager().getScoreboard());
        AnimeCraft.instance.getGroupManager().getTeam(player).addEntry(player.getName());

        int input = 1;
        for (String uuid : DocumentRelated.getSpecificDocument(player, "menu").getList("selection", String.class)){
            ItemStack item = null;
            if (uuid == "none" || uuid == "")continue;
            for (Tower tower : data.getTowers()){
                if (uuid.equals(tower.uuid.toString())) {
                    item = tower.getItemVersion(0);
                    break;
                }
            }
            if (item == null) {
                player.getInventory().setItem(input, Utils.createItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, "&fPlacement: " + (input), new String[]{"&eClick to open the tower menu"}));
            } else {
                player.getInventory().setItem(input, item);
            }
            input++;
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        DocumentRelated.saveDocument(e.getPlayer());
        AnimeCraft.instance.getDataManager().getPlayerData().remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Data data = Utils.getData(e.getPlayer());
        e.setFormat(Utils.color(data.getGroup().getPrefix() + " &f" + e.getPlayer().getName() + "&f : " + (data.getGroup().getGroup().toLowerCase().equals("joueur") ? "&8" : "&f") + message));
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Zombie) {
            BannerMenu menu = new BannerMenu();
            menu.set(e.getPlayer());
        }
        if (e.getRightClicked() instanceof Cat) {
            TowerMenu menu = new TowerMenu(true);
            menu.set(e.getPlayer());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
            if (e.getHand() == EquipmentSlot.HAND) {
                if (e.getItem().getType() == Material.PLAYER_HEAD || e.getItem().getType() == Material.MAGENTA_STAINED_GLASS_PANE) {
                    e.setCancelled(true);
                    TowerMenu menu = new TowerMenu(false);
                    menu.set(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onClickInventoryMenu(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu) {
            Menu menu = (Menu)holder;
            if (e.getSlot() < 0) {
                return;
            }

            if (e.getCurrentItem() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            e.setCancelled(true);
            menu.HandleMenu(e);
        }

    }
}
