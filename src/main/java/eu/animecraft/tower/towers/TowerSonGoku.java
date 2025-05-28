package eu.animecraft.tower.towers;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

import java.util.UUID;

public class TowerSonGoku extends Tower {
    public TowerSonGoku() {
        super(1, Rarity.COMMON, "Goko (Classic)", null, false, 5, UUID.randomUUID(),
                "ewogICJ0aW1lc3RhbXAiIDogMTcwMTkzMDkxNTM3MSwKICAicHJvZmlsZUlkIiA6ICI4NTZkNmYzMDgzYWY" +
                        "0MDNjODUyMTRlMGQ3NzFhY2ExNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJLb29yeEF0aWxsYSIsCi" +
                        "AgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIi" +
                        "A6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS" +
                        "85YmMyZmFhOTJlMWMzOTM4MTU5ZDUzYWI5MDhhZWJjMjY4NTg1YzBjZWE1MzAzOTlmOWE2YWQ4ODd" +
                        "lNTAyNTA4IgogICAgfQogIH0KfQ==",
                "ewogICJ0aW1lc3RhbXAiIDogMTY5NDEwNDc4NzcxOCwKICAicHJvZmlsZUlkIiA6ICI4ZGUyNDAzYTEyMjU0ZmFkOTM1OTYx" +
                        "YWFlYmQwNGUyOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJZdW5hbWkyNyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6" +
                        "IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1" +
                        "cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83ZjllZWM2Zjc5MTk4N2NlNWQzNDQxZDZlYTdiMjk1YjY5NDVkZTE2" +
                        "ZjlhNTMzOTZiN2M5ZWJmMTVmMjY2MTZjIgogICAgfQogIH0KfQ==", AttackType.SINGLE);
        this.damage = 30;
        this.cooldown = 20;
        this.range = 7;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.LIGHT};
    }
}
