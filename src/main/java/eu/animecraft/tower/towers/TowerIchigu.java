package eu.animecraft.tower.towers;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

import java.util.UUID;

public class TowerIchigu extends Tower {
    public TowerIchigu() {
        super(2, Rarity.RARE, "Uchiga", null, false, 5, UUID.randomUUID(),
                "ewogICJ0aW1lc3RhbXAiIDogMTU4OTk0NjgwODE5NiwKICAicHJvZmlsZUlkIiA6ICJkZTU3MWExMDJjYjg0ODgwOGZlN2M5ZjQ0OT" +
                        "ZlY2RhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNSEZfTWluZXNraW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVl" +
                        "LAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3" +
                        "JhZnQubmV0L3RleHR1cmUvODIxNzYxZDNlZDAyMzQxZGUyODljMGZkMGRkMzMzZGNjZmY2MDhjNzhlNDgyZjRiOTIxNWI0" +
                        "M2VlYjI2NWRiYiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
                "ewogICJ0aW1lc3RhbXAiIDogMTY4NTc1NzAyMDYzMiwKICAicHJvZmlsZUlkIiA6ICI4N2YzOGM1MWE4Yzc0MmNmYTY2YTgxNWExZTI2NzMzYiIsC" +
                        "iAgInByb2ZpbGVOYW1lIiA6ICJCZWR3YXJzQ3V0aWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOi" +
                        "B7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UwYWVhNGJ" +
                        "hZWM1ZmI0ZWVlNzZlY2RhYTJjOTNmZjkyYjU2YWQ3Nzg4M2Y3MjYyZjM4YzRlY2Q1ZjEzZDI5OSIsCiAgICAgICJtZXRhZGF0YSIgOiB7" +
                        "CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9");
        this.damage = 25;
        this.cooldown = 100;
        this.range = 6;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.LIGHT};
        
        //this.upgrades.costs= new int[]{130,145,240,360,450,575,600};
        //this.upgrades.upgrades= new String[]{"30_10_0", "20_40_0", "4_1_0", "4_8_5", "5_9_7", "87_4_5", "98_5_1"};
    }
}
