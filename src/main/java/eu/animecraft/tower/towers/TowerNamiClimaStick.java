package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerNamiClimaStick extends Tower{

	public TowerNamiClimaStick() {
		super(4575, Rarity.LEGENDARY, "Nomy (Weather)", null, false, 4, UUID.randomUUID(), 
				"ewogICJ0aW1lc3RhbXAiIDogMTYyODEyMTEzNjU1NCwKICAicHJvZmlsZUlkIiA6ICI3NjVkOGNhNDNhODQ0N2YxYjMyODAxZDM1N2MzM2E1NSIsCiAgIn"
				+ "Byb2ZpbGVOYW1lIiA6ICJEYXJrX1dvbGZpdG8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTi"
				+ "IgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjk5NDA4Yjc2ZjVlNjA3MmQ3NmZiMWMyMTgzM2"
				+ "EyMDk2MDk0ZmViYjNiZmM1ZmZjMTdhM2EwODhiZTA2ZDA2MyIKICAgIH0KICB9Cn0=",
				null);
        this.damage = 245;
        this.cooldown = 280;
        this.range = 24;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.WIND};
	}
	
}
