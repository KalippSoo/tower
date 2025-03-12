package eu.animecraft.controler;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import eu.animecraft.arena.Arena;

public class TowerData {
	
	private Arena region;
	private int upgradePrice;
	private Location location;
	private String name;
	private int lvl;
	private int towerBuyPrice;
	private ItemStack item;

	public TowerData(Arena region, int towerBuyPrice, int upgradePrice, Location location, String name, int lvl, ItemStack item) {
		this.region = region;
		this.upgradePrice = upgradePrice;
		this.location = location;
		this.name = name;
		this.lvl = lvl;
		this.towerBuyPrice = towerBuyPrice;
		this.item = item;
	}

	public int getUpgradePrice() {
		return upgradePrice;
	}

	public Arena getRegion() {
		return region;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public int getLvl() {
		return lvl;
	}

	public int getTowerBuyPrice() {
		return towerBuyPrice;
	}

	public ItemStack getItem() {
		return item;
	}
	
}
