package eu.animecraft.arena;

import org.bukkit.Sound;

public class SoundTower {

	public static Sound getSound(int id) {
		
		Sound sound = null;
		switch (id) {
		case 0: sound = Sound.ITEM_FLINTANDSTEEL_USE; break;
		case 1: sound = Sound.ENTITY_GENERIC_EXPLODE; break;
		case 2: sound = Sound.ENTITY_PLAYER_ATTACK_SWEEP; break;
		case 4575: sound = Sound.AMBIENT_UNDERWATER_LOOP; break;
		}
		if (sound == null)
			sound = Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR;
		return sound;
	}
	
}
