package eu.animecraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;

public class PlayerBannerDropEvent extends Event implements Cancellable{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Player player;
	private Data data;
	
	private int bannerID;
	
	boolean cancelled = false;
	
	public PlayerBannerDropEvent(Player player, int id) {
		this.player = player;
		this.data = Utils.getData(player);
		this.bannerID = id;
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

	public Player getPlayer() {
		return player;
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

	public int getBannerID() {
		return bannerID;
	}

	public Data getData() {
		return data;
	}
	
}
