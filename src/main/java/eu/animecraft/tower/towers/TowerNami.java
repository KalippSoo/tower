package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerNami extends Tower{

	public TowerNami() {
		super(4574, Rarity.EPIC, "Nomy (Stick)", null, false, 4, UUID.randomUUID(), 
				"ewogICJ0aW1lc3RhbXAiIDogMTY3MTk1MzcxODE0OCwKICAicHJvZmlsZUlkIiA6ICJhMjk1OD"
				+ "ZmYmU1ZDk0Nzk2OWZjOGQ4ZGE0NzlhNDNlZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZXZlMj"
				+ "MiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgIC"
				+ "AiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubm"
				+ "V0L3RleHR1cmUvOWVkNDdjNjEyZWE3MzZkNWFkMjUxZWNkYWNlODBiZTliMDkzNjY3NGQ2OW"
				+ "JhNWQ2ZDRjYWNlNzE5NDkyZmE5NyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm"
				+ "1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
				"ewogICJ0aW1lc3RhbXAiIDogMTcxNTQ1Njk2ODA2MSwKICAicHJvZmlsZUlkIiA6ICIzZWUxYW"
				+ "RlMzljZDI0ZjFkOWYwODliYjA2ZTkzNTY5YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSdXNvR0"
				+ "1SIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogIC"
				+ "AgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm"
				+ "5ldC90ZXh0dXJlLzQ5NjI2ZGI4YzNkYjAxOGZmNGIxNGUwNjRiODZlYmE0NDU3MWM0ZGUxMz"
				+ "Y0MzJmZTY5MGFjZmU0Njc5ZTMxZjciLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgIC"
				+ "Jtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
        this.damage = 38;
        this.cooldown = 240;
        this.range = 15;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.WIND};
        this.evo = new TowerNamiClimaStick();
	}

}
