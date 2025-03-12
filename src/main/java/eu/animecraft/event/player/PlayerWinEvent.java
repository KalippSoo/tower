package eu.animecraft.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;

public class PlayerWinEvent extends Event{

private static HandlerList HANDLERS = new HandlerList();
	
	private Player player;
	private Data data;
	
	public PlayerWinEvent(Player player, int id) {
		this.player = player;
		this.data = Utils.getData(player);
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

	public Data getData() {
		return data;
	}
}
