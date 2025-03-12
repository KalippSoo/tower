package eu.animecraft.event.tower;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.MyArmorStand;
import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.Tower;

public class TowerHittingTargetEvent extends Event implements Cancellable {

	private static HandlerList HANDLERS = new HandlerList();
	boolean cancelled = false;

	private final LivingEntity entityLiving;
	private final Player player;
	private final Tower tower;
	private final Data data;
	private final MyArmorStand armorStand;

	public TowerHittingTargetEvent(Tower tower, LivingEntity living, MyArmorStand armorStand){
		this.tower = tower;
		this.player = tower.getOwner();
		this.entityLiving = living;
		this.data = Utils.getData(player);
		this.armorStand = armorStand;
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

	public LivingEntity getEntity() {
		return entityLiving;
	}

	public Player getPlayer() {
		return player;
	}

	public Tower getTower() {
		return tower;
	}

	public Data getData() {
		return data;
	}

	public MyArmorStand getArmorStand() {
		return armorStand;
	}
}
