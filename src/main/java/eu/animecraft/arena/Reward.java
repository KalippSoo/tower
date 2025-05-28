package eu.animecraft.arena;

import java.util.concurrent.ThreadLocalRandom;

public class Reward {
	
	private int rerolls = ThreadLocalRandom.current().nextInt(3, 6);
	private int playerExp = 500;
	private int gems = ThreadLocalRandom.current().nextInt(40, 80);
	
	public Arena arena;
	
	public Reward(Arena arena) {
		this.arena = arena;
	}
	
	public int getRerolls() {
		return rerolls;
	}
	public int getPlayerExp() {
		return playerExp;
	}
	public int getGems() {
		return gems;
	}
	
	
}
