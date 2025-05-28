package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerBorosArmored extends Tower{

	public TowerBorosArmored() {
		super(1574, Rarity.LEGENDARY, "Boron (Armored)", null, false, 3, UUID.randomUUID()
				, "ewogICJ0aW1lc3RhbXAiIDogMTYzMDU0MDUyMzA3OSwKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0N"
						+ "TQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogIC"
						+ "JzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB"
						+ "7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTVk"
						+ "OGRlMTk0MDViZjVjYTI5MWQxNzk5NGNjZDk2NGI4ZDc1MmE1ZWEzNDQ4NWE3ZjUyODBiNGYxNjRhM"
						+ "ThiMyIKICAgIH0KICB9Cn0=", null, AttackType.SINGLE);
        this.damage = 150;
        this.cooldown = 19;
        this.range = 15;
        this.damageTypes = new DamageType[]{DamageType.MAGICAL, DamageType.LIGHT};
	}

}
