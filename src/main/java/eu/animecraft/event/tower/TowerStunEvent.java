package eu.animecraft.event.tower;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TowerStunEvent extends Event{

	private static HandlerList HANDLERS = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		// TODO Auto-generated method stub
		return HANDLERS;
	}
}
