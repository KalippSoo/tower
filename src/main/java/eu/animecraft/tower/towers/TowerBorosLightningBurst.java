package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerBorosLightningBurst extends Tower{

	public TowerBorosLightningBurst() {
		super(147, Rarity.SECRET, "Boron (Lightning Burst)", null, false, 1, UUID.randomUUID(), 
				"ewogICJ0aW1lc3RhbXAiIDogMTY0MTgwMzg4NDkwNCwKICAicHJvZmlsZUlkIiA6ICJjNzQ1Mzc4MDY5MzY0OD"
				+ "g2ODkwNzRkOTQ3ZjBlOTlmNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJjdWN1bWkwNyIsCiAgInNpZ25hdHVyZV"
				+ "JlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOi"
				+ "AiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xM2ViNDVkYmJkYjYyMzYxYTQyNTdlZT"
				+ "cwMjY5NWRmNTEwNWJhOTAxYjM2YWRhNmJjMTM1NzNjY2VlZWY0NTA3IgogICAgfQogIH0KfQ==", null
				, AttackType.FULL);
		this.damage = 1200;
        this.cooldown = 140;
        this.range = 40;
        this.damageTypes = new DamageType[]{DamageType.MAGICAL, DamageType.FIRE};
	}

}
