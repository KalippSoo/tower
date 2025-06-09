package eu.animecraft.listeners;

import org.bukkit.event.EventHandler;

import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.arena.ArenaGameLostEvent;

public class ArenaListener extends EventListener{
	
	@EventHandler
	public void onLostGame(ArenaGameLostEvent e) {
		
		e.getPlayers().forEach(players->super.resetPlayerToSpawn(players));
		e.getPlayers().forEach(players->players.sendTitle(Utils.color("&cYou've lost the game"), Utils.color("&cYou may unlucky, you need to try again"), 5, 50, 5));
		e.getArena().reset();
	}
	
}
