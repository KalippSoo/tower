package eu.animecraft.listerners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import eu.animecraft.AnimeCraft;
import eu.animecraft.arena.Arena;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.DocumentRelated;
import eu.animecraft.data.Lvl;
import eu.animecraft.data.StatsReroll;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.play.PlayerLeftPlayEvent;
import eu.animecraft.listerners.menu.BannerMenu;
import eu.animecraft.listerners.menu.TeleportationMenu;
import eu.animecraft.listerners.menu.TowerMenu;
import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.Trait;

public class Listeners extends EventListener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().clear();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 999999, 255, false, false));
        if (!AnimeCraft.instance.getDataManager().getPlayerData().containsKey(e.getPlayer().getUniqueId())) {
            AnimeCraft.instance.getDataManager().getPlayerData().put(e.getPlayer().getUniqueId(), new Data());
        }
		
		Data data = Utils.getData(player);
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

        Iterator<String> collections = collection.getList("contents", String.class).iterator();
        Iterator<String> towers = inventory.getList("contents", String.class).iterator();

        while(towers.hasNext()){
            String[] info = ((String)towers.next()).split(",");
            int id = Integer.parseInt(info[0]);
            Tower targetTower = AnimeCraft.instance.getTowerManager().getTower(id);
            double
                    a = Double.parseDouble(info[1]),
                    b = Double.parseDouble(info[2]),
                    c = Double.parseDouble(info[3]);
            boolean shiny = Boolean.parseBoolean(info[4]);
            Trait trait = Trait.valueOf(info[5]);
            int level = Integer.parseInt(info[6]);
            double experience = Double.parseDouble(info[7]);
            UUID uuid = UUID.fromString(info[8]);
            boolean locked = info[9] == null?false:Boolean.parseBoolean(info[9]);
            
            Lvl lvlSystem = new Lvl(targetTower);
            lvlSystem.setLevel(level);
            lvlSystem.addExp(experience);
            
            Tower tower = new Tower(uuid, targetTower.damage, targetTower.cooldown, targetTower.range,
            		shiny, player, id, lvlSystem, new StatsReroll(a,b,c), trait, targetTower.getRarity(),
            		targetTower.getMaxCount(), targetTower.value, targetTower.sValue, targetTower.getName(),
            		locked, Utils.getEvolved(id), targetTower.attackType);
            if (tower.range == 0) continue;
            data.getTowers().add(tower);
        }

        String group = stats.getString("rank");

        data.setGroup(AnimeCraft.instance.getGroupManager().getGroup(group));
        player.setScoreboard(AnimeCraft.instance.getGroupManager().getScoreboard());
        AnimeCraft.instance.getGroupManager().getTeam(player).addEntry(player.getName());
        
        data.setListSelected((DocumentRelated.getSpecificDocument(player, "menu").getList("selection", String.class)));
        DataManager.changeInventory(player, 0);
	            
		player.sendTitle(Utils.color("&fWelcome &e"+player.getName()), Utils.color("&cThis mode is still in beta !"), 5, 80, 5);
		Utils.sendMessages(player, "&cPlease contact us, if there's something wrong !!");
		super.resetPlayerToSpawn(player);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    	DocumentRelated.saveDocument(e.getPlayer());
        AnimeCraft.instance.getDataManager().getPlayerData().remove(e.getPlayer().getUniqueId(), Utils.getData(e.getPlayer()));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Data data = Utils.getData(e.getPlayer());
        e.setFormat(Utils.color(data.getGroup().getPrefix() + " &f" + e.getPlayer().getName() + "&f : " + (data.getGroup().getGroup().toLowerCase().equals("joueur") ? "&8" : "&f") + message));
    }
    
    @EventHandler
    public void onDrop(PlayerSwapHandItemsEvent e) {
    	if (e.getPlayer().getGameMode() == GameMode.CREATIVE)return;
    	e.setCancelled(false);
    }
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
    	if (e.getPlayer().getGameMode() == GameMode.CREATIVE)return;
    	e.setCancelled(true);
    }
    
    @EventHandler
    public void onFall(EntityDamageEvent e) {
    	if (e.getEntity() instanceof Player) {
    		Player player = (Player) e.getEntity();
    		if (e.getCause() == DamageCause.VOID) {
    			e.setCancelled(true);
    			Data data = super.dataFrom(player);
    			Arena arena = data.getArena();
    			if (arena == null) {
    				super.resetPlayerToSpawn(player);
    			}else {
    				player.teleport(arena.tp);
    			}
    		}
    	}
    }
    
    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
    	if (e.getEntity() instanceof Player)return;
    	if (e.getEntity() instanceof ArmorStand)return;
    	if (!(e.getEntity() instanceof LivingEntity))return;
    	instance.getTowerManager().currentEnemies.add(e.getEntity());
    }
    
    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
    	Player player = e.getPlayer();
        if (e.getRightClicked() instanceof Zombie) {
            BannerMenu menu = new BannerMenu();
            menu.set(e.getPlayer());
        }
        if (e.getRightClicked() instanceof Cat) {
        	dataFrom(player).towerMenu(player, 0);
        }
        if (e.getRightClicked() instanceof ArmorStand) {
        	if (e.getRightClicked() == instance.getEvolutionManager().getArmorStand()) {
        		new TowerMenu(1).set(player);
        	}
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
    	Player player = e.getPlayer();
        if (e.getItem() == null || e.getItem().getType() == Material.AIR)return;
        if (e.getHand() == EquipmentSlot.OFF_HAND)return;
        if (!e.getAction().name().contains("RIGHT_CLICK"))return;

        if (dataFrom(player).getArena() != null) {
        	e.setCancelled(true);
        }
        switch (e.getItem().getType()) {
		case PLAYER_HEAD:
    		Tower tower = instance.getTowerManager().getTowerByItemVersion(player, e.getItem());
    		if (dataFrom(player).getArena() == null)return;
    		if (tower == null)return;
    		tower.placeStand(player, dataFrom(player).getArena(), e.getClickedBlock() == null ? player.getLocation() : e.getClickedBlock().getLocation().add(0.5, 2, 0.5));
    		return;
    		
		case CHEST:
            dataFrom(player).towerMenu(player, -1);
            e.setCancelled(true);
			return;
		case RED_TERRACOTTA:
			super.callEvent(new PlayerLeftPlayEvent(player));
			Data data = dataFrom(player);
			if (data.play != null) {
				data.play.getPlayers().remove(player);
				super.resetPlayerToSpawn(player);
			}
			return;
		case COMPASS:
			new TeleportationMenu().set(player);
			return;
		default:
			break;
		}
    }

    @EventHandler
    public void onDeathEntity(EntityDeathEvent e) {
    	if (instance.getTowerManager().currentEnemies.contains(e.getEntity())) {
    		e.setDroppedExp(0);
    		e.getDrops().clear();
    	}
    }
    
    @EventHandler
    public void onClickInventoryMenu(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu) {
            Menu menu = (Menu)holder;
            if (e.getSlot() < 0) return;
            if (e.getCurrentItem() == null)  return;
            if (e.getCurrentItem().getType() == Material.AIR) return;

            e.setCancelled(true);
            menu.HandleMenu(e);
            return;
        }
    	if (e.getWhoClicked().getGameMode() == GameMode.CREATIVE)return;
    	e.setCancelled(true);
    }
}
