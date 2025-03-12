package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerBorosFree extends Tower{

	public TowerBorosFree() {
		super(2455, Rarity.MYTHIC, "Boron (Unarmored)", null, false, 2, UUID.randomUUID(), 
				"ewogICJ0aW1lc3RhbXAiIDogMTcyODgxMjU5OTEzMCwKICAicHJvZmlsZUlkIiA6ICJjNWVmOGQ"
				+ "1NDIwOWY0OTdlYWYzYzA1NjA3MjZhYTMwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXNoX0Rh"
				+ "eCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgI"
				+ "CJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZX"
				+ "QvdGV4dHVyZS80NjgxMWU2MTgyOTUzNTQyMjVmMTZlZDBiNDQ4YzUxMjk1YWNhZDBkNTk1NTI"
				+ "1NjFkMDkxOTI4NDU3YzhiYjBiIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9k"
				+ "ZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", null);
        this.damage = 340;
        this.cooldown = 48;
        this.range = 20;
        this.damageTypes = new DamageType[]{DamageType.MAGICAL, DamageType.FIRE};
	}

}
