package eu.animecraft.event.arena;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.arena.Arena;

public class ArenaWaveEndEvent extends Event implements Cancellable{

private static HandlerList HANDLERS = new HandlerList();
	
	private final Arena arena;
	
	boolean cancelled = false;

	public ArenaWaveEndEvent(Arena arena) {
		this.arena = arena;
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
	
}
