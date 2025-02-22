package eu.animecraft.data.components;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;

public abstract class EventListener implements Listener{
	
	public AnimeCraft instance = AnimeCraft.instance;
	public Data dataFrom(Player player) {
		return Utils.getData(player);
	}
	
}
