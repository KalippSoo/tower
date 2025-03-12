package eu.animecraft.listerners;

import org.bukkit.event.EventHandler;

import eu.animecraft.arena.Arena;
import eu.animecraft.controler.EnemyCustom;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.arena.ArenaGameLostEvent;
import eu.animecraft.event.arena.ArenaPreChargeEvent;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class ArenaListener extends EventListener{

	@EventHandler
	public void onNewArena(ArenaPreChargeEvent e) {
		
		Arena arena = e.getArena();
		if (arena == null)return;
		
		//e.getPlayers().forEach(players -> players.teleport(arena.tp));
		EnemyCustom custom = new EnemyCustom(EntityTypes.PIG, 1f, 125, "Big mama", arena, true, true);
		custom.spawn();
	}
	
	@EventHandler
	public void onLostGame(ArenaGameLostEvent e) {
		
		e.getPlayers().forEach(players->players.sendTitle(Utils.color("&cYou've lost the game"), Utils.color("&cYou may unlucky, you need to try again"), 5, 50, 5));
		e.getArena().reset();
	}
	
}
