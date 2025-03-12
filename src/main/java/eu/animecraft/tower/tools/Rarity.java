package eu.animecraft.tower.tools;

import java.awt.Color;

import net.md_5.bungee.api.ChatColor;

public enum Rarity {
    COMMON("COMMON TOWER", ChatColor.of(new Color(225, 225, 225))),
    RARE("RARE TOWER", ChatColor.of(new Color(60, 255, 30))),
    EPIC("EPIC TOWER", ChatColor.of(new Color(30, 185, 255))),
    LEGENDARY("LEGENDARY TOWER", ChatColor.of(new Color(255, 215, 60))),
    MYTHIC("MYTHIC TOWER", ChatColor.of(new Color(245, 120, 235))),
    SECRET("SECRET TOWER", ChatColor.of(new Color(235, 65, 65))),
    LIMITED("LIMITED TOWER", ChatColor.of(new Color(45, 215, 136)));

    private String name;
    private ChatColor color;

    private Rarity(String name, ChatColor color) {
        this.name = name;
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
}
