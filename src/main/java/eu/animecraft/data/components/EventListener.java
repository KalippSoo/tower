package eu.animecraft.data.components;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;

public abstract class EventListener implements Listener{
	
	public AnimeCraft instance = AnimeCraft.instance;
	public Data dataFrom(Player player) {
		return Utils.getData(player);
	}
	public void resetPlayerToSpawn(Player player) {
		player.teleport(new Location(Bukkit.getWorld("world"), 0+getRandomWithNeg(3), 102, 0+getRandomWithNeg(3), -180, 0));
		DataManager.changeInventory(player, 0);
	}
    public double getRandomWithNeg(double radius) {
    	
    	double offset = (Math.random()*(radius+1));
    	if (Math.random() >= .5) offset*=-1;
    	return offset;
    }
    public void callEvent(Event event) {
    	Bukkit.getPluginManager().callEvent(event);
    }
}
