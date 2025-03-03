package eu.animecraft.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class TowerPlaceEvent extends Event{

	private static HandlerList HANDLERS = new HandlerList();
	
	private Player player;
	private Data data;
	
	private Location location;
	private Tower tower;
	
	public TowerPlaceEvent(Player player, Tower tower, Location loc) {
		this.player = player;
		this.data = Utils.getData(player);
		this.tower = tower;
		this.location = loc;
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

	public Tower getTower() {
		return tower;
	}

	public Location getLocation() {
		return location;
	}
}
