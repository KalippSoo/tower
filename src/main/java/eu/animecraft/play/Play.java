package eu.animecraft.play;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import eu.animecraft.data.components.Utils;

public class Play {

	public boolean isActivated = false;
	private int mode = 1;
	private int actMode = 0;
	
	private Player owner = null;
	private List<Player> players= new ArrayList<>();
	
	private int maxSeconds = 3000; 
	public int current = maxSeconds;
	public int maxPlayers = 4;
	
	final Playroom playroom;
	
	public Play(Playroom playroom) {
		this.playroom=playroom;
	}
	
	public void setToWaiting() {
		this.playroom.getStand().setCustomName("Waiting...");
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
		this.playroom.getAct().setCustomName(Utils.color(name));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getSeconds() {
		return maxSeconds;
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
	public int getActMode() {
		return actMode;
	}

	public void setActMode(int actMode) {
		this.actMode = actMode;
	}
	
}
