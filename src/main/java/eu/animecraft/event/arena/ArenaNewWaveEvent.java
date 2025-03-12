package eu.animecraft.event.arena;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.arena.Arena;

public class ArenaNewWaveEvent extends Event implements Cancellable{

private static HandlerList HANDLERS = new HandlerList();
	
	private final Arena arena;
	private final int wave;
	private List<Player> players;
	
	boolean cancelled = false;

	public ArenaNewWaveEvent(Arena arena, int wave) {
		this.arena = arena;
		this.wave = wave;
		this.players = arena.players;
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
	
	public int getWave() {
		return wave;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
}
