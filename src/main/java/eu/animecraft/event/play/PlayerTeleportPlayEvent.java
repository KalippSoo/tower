package eu.animecraft.event.play;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.arena.Arena;
import eu.animecraft.play.Play;

public class PlayerTeleportPlayEvent extends Event implements Cancellable{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Play play;
	private Arena arena;
	
	boolean cancelled = false;
	
	public PlayerTeleportPlayEvent(Play play, Arena arena) {
		this.play=play;
		this.arena=arena;
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

	public Play getPlay() {
		return play;
	}

	public Arena getArena() {
		return arena;
	}
	
}
