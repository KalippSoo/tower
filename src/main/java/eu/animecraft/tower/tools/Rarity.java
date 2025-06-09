package eu.animecraft.tower.tools;

import java.awt.Color;

import eu.animecraft.tower.Tower;
import net.md_5.bungee.api.ChatColor;

public enum Rarity {
    COMMON("COMMON TOWER", 45, ChatColor.of(new Color(225, 225, 225))),
    RARE("RARE TOWER", 35, ChatColor.of(new Color(60, 255, 30))),
    EPIC("EPIC TOWER", 10, ChatColor.of(new Color(30, 185, 255))),
    LEGENDARY("LEGENDARY TOWER", 5, ChatColor.of(new Color(255, 215, 60))),
    MYTHIC("MYTHIC TOWER", 1, ChatColor.of(new Color(245, 120, 235))),
    SECRET("SECRET TOWER", 0, ChatColor.of(new Color(235, 65, 65))),
    LIMITED("LIMITED TOWER", 0, ChatColor.of(new Color(45, 215, 136)));

    private String name;
    private ChatColor color;
    private double dropChance;

    private Rarity(String name, double dropChance, ChatColor color) {
        this.name = name;
        this.dropChance = dropChance;
        this.color = color;
    }

    public String getName() {
        return getColor()+"&l"+this.name;
    }
    
    public String getRawName() {
        return this.name;
    }

    public ChatColor getColor() {
        return this.color;
    }
    
    public static double getMaxChance() {
    	double chance = 0;
    	for (Rarity rarities:values()) {
    		chance+=rarities.dropChance;
    	}
    	return chance;
    }
    
    public static int getGold(Tower tower) {
    	return getGold(tower.getRarity());
    }
    
    public static int getGold(Rarity rarity) {
    	switch (rarity) {
		case COMMON: return 1000;
		case RARE: return 3000;
		case EPIC: return 7500;
		case LEGENDARY: return 10000;
		case MYTHIC: return 15000;
		case SECRET: return 20000;
		case LIMITED: return 25000;
		}
		return 0;
    }

	public double getDropChance() {
		return dropChance;
	}
}
