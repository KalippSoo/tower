package eu.animecraft.event;

import eu.animecraft.tower.CraftTower;
import net.minecraft.server.v1_16_R3.EntityLiving;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TowerHittingTargetEvent extends Event implements Cancellable {

	private static HandlerList HANDLERS = new HandlerList();
	boolean cancelled = false;

	public CraftTower craftTower;
	public EntityLiving entityLiving;

	public TowerHittingTargetEvent(CraftTower craftTower, EntityLiving living){
		this.craftTower = craftTower;
		this.entityLiving = living;
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
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}
}
