package eu.animecraft.event.arena;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.arena.Arena;

public class ArenaPreChargeEvent extends Event implements Cancellable{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Arena arena;
	private List<Player> player;
	private int id;
	
	boolean cancelled = false;
	
	public ArenaPreChargeEvent(Arena arena, List<Player> player, int id) {
		this.arena=arena;
		this.player=player;
		this.id=id;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		// TODO Auto-generated method stub
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancelled = arg0;
		
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public List<Player> getPlayers() {
		return player;
	}

	public int getId() {
		return id;
	}
	
}
