package eu.animecraft.play;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.AnimeCraft;
import eu.animecraft.arena.Arena;
import eu.animecraft.arena.ArenaManager;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.ConfigManager;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.play.PlayerJoinPlayEvent;
import eu.animecraft.event.play.PlayerLeftPlayEvent;
import eu.animecraft.event.play.PlayerTeleportPlayEvent;
import eu.animecraft.menu.PlayMenu;

public class PlayManager extends EventListener{

	public List<Playroom> rooms = new ArrayList<Playroom>();
	
	public PlayManager(ConfigManager config) {
		
		read(instance, config);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (Playroom playrooms : rooms) {
					Play play = playrooms.getPlay();
					if (!playrooms.isActive())continue;
					
					if (play.getPlayers().size() > 0) {
						//At least one player is inside, so the cool down begin
						play.current--;
						playrooms.getStand().setCustomName("Teleportation in "+play.current+"... ("+play.getPlayers().size()+"/"+play.maxPlayers+")");
						if (play.current <= 0) {
							//Teleport to arena
//							if (playRoom.getMode() == -1) {
//								playRoom.getPlayers().forEach(players->resetPlayerToSpawn(players));
//								playRoom.getPlayers().forEach(players->Utils.sendMessages(players, "&cThe leader didn't choose any modes !"));
//								playRoom.setToWaiting();
//								return;
//							}
							play.getPlayers().forEach(players->{
								callEvent(new PlayerTeleportPlayEvent(play, ArenaManager.FindPlace(play, play.getMode(), play.getActMode())));
							});
						}
					}
					
					for (Entity entities:playrooms.getStand().getNearbyEntities(.5, .5, .5)) {
						if (!(entities instanceof Player))continue;
						Player player = (Player)entities;
						callEvent(new PlayerJoinPlayEvent(playrooms, player));
					}
				}
			}
		}.runTaskTimer(AnimeCraft.instance, 0, 20);
	}
	
	
	public void read(AnimeCraft main, ConfigManager config) {
		if (config.isEmpty("playrooms"))return;
		FileConfiguration file = main.playrooms.getConfig();
		for (String ids : file.getConfigurationSection("playrooms").getKeys(false)) {
			
			String path = "playrooms."+ids;
			String[]
			enter = file.getString(path+".enter").split(" "),
			wait = file.getString(path+".wait").split(" ");
			boolean active = file.getBoolean(path+".active");
			
			Location enterLocation = new Location(Utils.overworld, 
					Double.parseDouble(enter[0]),
					Double.parseDouble(enter[1]),
					Double.parseDouble(enter[2]),
					Float.parseFloat(enter[3]),
					Float.parseFloat(enter[4]));
			Location waitLocation = new Location(Utils.overworld,
					Double.parseDouble(wait[0]),
					Double.parseDouble(wait[1]),
					Double.parseDouble(wait[2]),
					Float.parseFloat(wait[3]),
					Float.parseFloat(wait[4]));
			rooms.add(new Playroom(ids, active, enterLocation, waitLocation));
		}
		
	}
	
	@EventHandler
	public void onPlayerJoinPlayEvent(PlayerJoinPlayEvent e) {
		
		Player player = e.getPlayer();
		Data data = e.getData();
		Playroom playroom = e.getPlayroom();
		Play play = playroom.getPlay();
		
		boolean hasTurrets = false;
		for (String uuids : data.getListSelected()) {
			if (instance.getTowerManager().getTowerByUUID(data, uuids) != null) {
				hasTurrets = true;
				break;
			}
		}
		if (!hasTurrets) {
			Utils.sendMessages(player, "&cYou need to equiped at least 1 unit !");
			return;
		}
		
		//Still choosing a mode
		if (play.getPlayers().size() == 1 && play.getMode() == -1)return;
		
		if (data.play != null && data.play != play) {
			Utils.sendMessages(player, "&cSomething went wrong.. reset (SORRY)");
			super.resetPlayerToSpawn(player);
			data.play = null;
			return;
		}
		if (play.getPlayers().size() == 4) {
			Utils.sendMessages(player, "&cThis party is full !");
			return;
		}
		
		play.getPlayers().add(player);
		data.play = play;
		player.teleport(playroom.getWait());
		DataManager.changeInventory(player, 1);
		
		if (play.getOwner() == null) {
			play.setOwner(player);
			new PlayMenu(play).set(player);
		}
	}
	
	@EventHandler
	public void onPlayerLeftPlayEvent(PlayerLeftPlayEvent e) {
		if (e.getPlay() == null)return;
		if (e.getPlay().getPlayers().size() == 1) {
			e.getPlay().setToWaiting();
			super.resetPlayerToSpawn(e.getPlayer());
		}
	}
	
	public Playroom getRoomById(String id) {
		for (Playroom rooms : this.rooms) {
			if (rooms.getId().equals(id)) {
				return rooms;
			}
		}
		return null;
	}
	
	public String createNewRoom() {
		int id = ThreadLocalRandom.current().nextInt(0, 1000);
		for (Playroom rooms:this.rooms) {
			if (rooms.getId().equals(id+"")) {
				createNewRoom();
				return null;
			}
		}
		return id+"";
	}
	
	@EventHandler
	public void OnPlayerTeleportPlayEvent(PlayerTeleportPlayEvent e) {
		Play play=e.getPlay();
		Arena arena = e.getArena();
		
		List<Player>players=play.getPlayers();
		for (Player player : players) {
			Data data = Utils.getData(player);
			data.play=null;
			data.setArena(arena);
			player.teleport(arena.tp);
			DataManager.changeInventory(player, 2);
		}
		arena.newWave();
		play.setToWaiting();
	}
	
	public void OnDisable() {
		
		for (Playroom playroom:rooms) {
			playroom.resetTitles();
		}
		
	}

	
}














