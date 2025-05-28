package eu.animecraft.tower.towers;

import java.util.UUID;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

public class TowerItochi extends Tower {
    public TowerItochi() {
        super(0, Rarity.COMMON, "Itochi", null, false, 4, UUID.randomUUID(),
                "ewogICJ0aW1lc3RhbXAiIDogMTY5MDAwMzQ4NjkyOSwKICAicHJvZmlsZUlkIiA6ICJiNzQ4YWExODk3ZGU0Y2RiYjFhNTI1YjVjMjM3ZDc2" +
                        "MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaXhpOCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6" +
                        "IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YmJm" +
                        "OTBhZGNjNDlhNGYzYWU0YTEyOWMxNDUzYTE3YTMwMGJjNzQwM2YyNjZlMDE4NmZhODY2ZjBkNDg4MDZjIgogICAgfQogIH0KfQ==",
                        "ewogICJ0aW1lc3RhbXAiIDogMTczMjM5NjA4NjM2NiwKICAicHJvZmlsZUlkIiA6ICI3OThlZWM3OGYzNGU0MGJhYjlmNjk0ODIw"
                        + "YzM3ZTcxYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJFbENoYW5vbk1DIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKIC"
                        + "AidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5l"
                        + "dC90ZXh0dXJlL2RlM2YzZjhmMTYxMzAxMjIzODg1NGY0NWUzMGNhMGM4NGE3MWM0ZGMwNmI4OTRhNTE4NWFlZGI2NTE4MmFkNW"
                        + "YiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="
                        , AttackType.FULL);
        this.damage = 95;
        this.cooldown = 80;
        this.range = 9;
        this.damageTypes = new DamageType[]{DamageType.MAGICAL, DamageType.FIRE};
    }
    
    
}
