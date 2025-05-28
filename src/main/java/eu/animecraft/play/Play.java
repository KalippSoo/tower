package eu.animecraft.play;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import eu.animecraft.data.components.Utils;

public class Play {

	public boolean isActivated = false;
	private Location location, front;
	private int mode = 1;
	private int actMode = 0;
	
	private Player owner = null;
	private List<Player> players= new ArrayList<>();
	
	private int maxSeconds = 3000; 
	public int current = maxSeconds;
	public int maxPlayers = 4;
	
	private ArmorStand stand;
	private ArmorStand act;
	
	public Play(Location location, int... offsets) {
		this.location = location;
		this.front = location.clone().add(offsets[0], 0, offsets[1]);
		this.stand = location.getWorld().spawn(front, ArmorStand.class, stands -> {
			stands.setVisible(false);
			stands.setCustomNameVisible(true);
			stands.setCustomName("Waiting...");
			stands.setBasePlate(false);
		});
		for (Entity entity : stand.getNearbyEntities(0.01, 2, 0.01)) {
			if (entity instanceof ArmorStand)entity.remove();
		}
		this.act = location.getWorld().spawn(location.clone().add(offsets[0],-.5,0), ArmorStand.class, stands -> {
			stands.setVisible(false);
			stands.setCustomNameVisible(true);
			stands.setCustomName(" ");
			stands.setBasePlate(false);
		});
		for (Entity entity : act.getNearbyEntities(0.01, 2, 0.01)) {
			if (entity instanceof ArmorStand)entity.remove();
		}
	}

	public void setToWaiting() {
		this.stand.setCustomName("Waiting...");
		this.current=maxSeconds;
		this.getPlayers().clear();
		this.setOwner(null);
		this.setActMode(0);
	}
	
	public int getMode() {
		return mode;
	}	
	public void setMode(int mode, String name) {
		this.mode = mode;
		this.act.setCustomName(Utils.color(name));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getSeconds() {
		return maxSeconds;
	}

	public ArmorStand getStand() {
		return stand;
	}

	public Location getLocation() {
		return location;
	}

	public Location getFront() {
		return front;
	}

	public int getCurrent() {
		return current;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public ArmorStand getAct() {
		return act;
	}

	public int getActMode() {
		return actMode;
	}

	public void setActMode(int actMode) {
		this.actMode = actMode;
	}
	
}
