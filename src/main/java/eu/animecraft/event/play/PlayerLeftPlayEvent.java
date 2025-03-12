package eu.animecraft.event.play;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Play;

public class PlayerLeftPlayEvent extends Event implements Cancellable{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Play play;
	private Player player;
	private Data data;
	
	boolean cancelled = false;
	
	public PlayerLeftPlayEvent(Player player) {
		this.player=player;
		this.data=Utils.getData(player);
		this.play=data.play;
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

	public Data getData() {
		return data;
	}

	public Player getPlayer() {
		return player;
	}
	
}
