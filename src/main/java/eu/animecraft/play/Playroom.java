package eu.animecraft.play;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class Playroom {
	
	private final String id;
	private Location enter;
	private Location wait;
	
	private boolean active = false;
	private final Play play;
	
	public Playroom(String id, boolean active, Location enter, Location wait) {
		this.id = id;
		this.active = active;
		this.enter = enter;
		this.wait = wait;
		this.play = new Play(this);

		setPositions();
	}

	public Location getEnter() {
		return enter;
	}
	
	private ArmorStand stand;
	private ArmorStand act;
	
	public void setPositions() {
		
		if (enter != null) {
			
			if (enter.getX() == 0 && enter.getY() == 0 && enter.getZ() == 0) {
				return;
			}
			
			if (stand != null) {
				stand.remove();
				act.remove();
			}
			
			this.stand = enter.getWorld().spawn(enter, ArmorStand.class, stands -> {
				stands.setVisible(false);
				stands.setCustomNameVisible(true);
				stands.setCustomName("Waiting...");
				stands.setBasePlate(false);
			});
			this.act = enter.getWorld().spawn(enter.clone().add(0,-.5,0), ArmorStand.class, stands -> {
				stands.setVisible(false);
				stands.setCustomNameVisible(true);
				stands.setCustomName(" ");
				stands.setBasePlate(false);
			});
		}
	}
	
	public void setEnterLocation(Location location) {
		this.enter = location;
		setPositions();
	}

	public void resetTitles() {
		if (stand != null) {
			stand.remove();
			stand = null;
		}
		if (act != null) {
			act.remove();
			act = null;
		}
	}
	
	public Location getWait() {
		return wait;
	}
	
	public void setWaitLocation(Location location) {
		this.wait = location;
	}

	public String getId() {
		return id;
	}

	public Play getPlay() {
		return play;
	}

	public ArmorStand getAct() {
		return act;
	}	
	
	public ArmorStand getStand() {
		return stand;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}
