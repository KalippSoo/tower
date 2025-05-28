package eu.animecraft.play;

import org.bukkit.Location;

public class Playroom {
	
	private final String id;
	private final Location enter;
	private final Location wait;
	
	public Playroom(String id, Location enter, Location wait) {
		this.id = id;
		this.enter = enter;
		this.wait = wait;
	}

	public Location getEnter() {
		return enter;
	}

	public Location getWait() {
		return wait;
	}

	public String getId() {
		return id;
	}

	
}
