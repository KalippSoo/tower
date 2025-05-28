package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerTamanegy extends Tower{

	public TowerTamanegy() {
		super(666, Rarity.LIMITED, "Tamanegy", null, false, 1, UUID.randomUUID(), 
				"ewogICJ0aW1lc3RhbXAiIDogMTY0NDY4MDg4MzMwOCwKICAicHJvZmlsZUlkIiA"
				+ "6ICJjZGM5MzQ0NDAzODM0ZDdkYmRmOWUyMmVjZmM5MzBiZiIsCiAgInByb2ZpbG"
				+ "VOYW1lIiA6ICJSYXdMb2JzdGVycyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IH"
				+ "RydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybC"
				+ "IgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81MzQzYT"
				+ "BiMTY5OTg3Yjc3YjUxNTMwNDQzMzEzYTMzMWY4YzY3MGZlYWI2NDMxYjA4ZjFjODc"
				+ "5ODIyOTRmNzYwIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWw"
				+ "iIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
				null, AttackType.SPLASH);
        this.damage = 300;
        this.cooldown = 80;
        this.range = 24;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.DARK};
	}
}