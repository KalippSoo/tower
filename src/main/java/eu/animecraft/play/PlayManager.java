package eu.animecraft.play;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.AnimeCraft;
import eu.animecraft.arena.Arena;
import eu.animecraft.arena.ArenaManager;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.play.PlayerJoinPlayEvent;
import eu.animecraft.event.play.PlayerLeftPlayEvent;
import eu.animecraft.event.play.PlayerTeleportPlayEvent;
import eu.animecraft.listerners.menu.PlayMenu;

public class PlayManager extends EventListener{

	public List<Play> currentRoom=new ArrayList<Play>();
	public List<Playroom> rooms = new ArrayList<Playroom>();
	
	public PlayManager() {
		currentRoom.add(new Play(new Location(Utils.world, -47.5, 104, -4.5), 0, 2));
		currentRoom.add(new Play(new Location(Utils.world, -51.5, 104, -3.5), 0, 2));
		currentRoom.add(new Play(new Location(Utils.world, -55.5, 104, -3.5), 0, 2));
		
		currentRoom.add(new Play(new Location(Utils.world, -47.5, 104, 5.5), 0, -3));
		currentRoom.add(new Play(new Location(Utils.world, -51.5, 104, 5.5), 0, -3));
		currentRoom.add(new Play(new Location(Utils.world, -55.5, 104, 5.5), 0, -3));
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Play playRoom : currentRoom) {
					if (playRoom.getPlayers().size() > 0) {
						//At least one player is inside, so the cool down begin
						playRoom.current--;
						playRoom.getStand().setCustomName("Teleportation in "+playRoom.current+"... ("+playRoom.getPlayers().size()+"/"+playRoom.maxPlayers+")");
						if (playRoom.current <= 0) {
							//Teleport to arena
//							if (playRoom.getMode() == -1) {
//								playRoom.getPlayers().forEach(players->resetPlayerToSpawn(players));
//								playRoom.getPlayers().forEach(players->Utils.sendMessages(players, "&cThe leader didn't choose any modes !"));
//								playRoom.setToWaiting();
//								return;
//							}
							playRoom.getPlayers().forEach(players->{
								callEvent(new PlayerTeleportPlayEvent(playRoom, ArenaManager.FindPlace(playRoom, playRoom.getMode(), playRoom.getActMode())));
							});
						}
					}
					
					for (Entity entities:playRoom.getStand().getNearbyEntities(.5, .5, .5)) {
						if (!(entities instanceof Player))continue;
						Player player = (Player)entities;
						callEvent(new PlayerJoinPlayEvent(playRoom, player));
					}
				}
			}
		}.runTaskTimer(AnimeCraft.instance, 0, 20);
	}
	
	@EventHandler
	public void onPlayerJoinPlayEvent(PlayerJoinPlayEvent e) {
		
		Player player = e.getPlayer();
		Data data = e.getData();
		Play play = e.getPlay();
		
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
		player.teleport(play.getLocation());
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
	
	
}














