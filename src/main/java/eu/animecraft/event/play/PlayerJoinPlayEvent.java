package eu.animecraft.event.play;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Playroom;

public class PlayerJoinPlayEvent extends Event implements Cancellable{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Playroom playroom;
	private Player player;
	private Data data;
	
	boolean cancelled = false;
	
	public PlayerJoinPlayEvent(Playroom playroom, Player player) {
		this.playroom=playroom;
		this.player=player;
		this.data=Utils.getData(player);
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

	public Playroom getPlayroom() {
		return playroom;
	}

	public Player getPlayer() {
		return player;
	}

	public Data getData() {
		return data;
	}
	
}
