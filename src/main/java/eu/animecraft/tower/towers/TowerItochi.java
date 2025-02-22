package eu.animecraft.tower.towers;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

import java.util.UUID;

public class TowerItochi extends Tower {
    public TowerItochi() {
        super(0, Rarity.COMMON, "Itochi", null, false, 4, UUID.randomUUID(),
                "ewogICJ0aW1lc3RhbXAiIDogMTY5MDAwMzQ4NjkyOSwKICAicHJvZmlsZUlkIiA6ICJiNzQ4YWExODk3ZGU0Y2RiYjFhNTI1YjVjMjM3ZDc2" +
                        "MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaXhpOCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6" +
                        "IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YmJm" +
                        "OTBhZGNjNDlhNGYzYWU0YTEyOWMxNDUzYTE3YTMwMGJjNzQwM2YyNjZlMDE4NmZhODY2ZjBkNDg4MDZjIgogICAgfQogIH0KfQ==",
                "Z36SLh258+jHPP6w2+4twB55Xek8bUq5zf1WPUO+lnb9rUhpqj2Q6Z5zQtdNS0j0YJxFV+8nylhkY1QxlHCPnmaTrxshedFvaTDbY+7lH4Sd" +
                        "kepGnNrdls+Snab8Nrd84n/wDEXptbh1wPgMOBn0RPILW1y+AFUKyw1C9gAj7RsK5l5xqIKlKIrtaa//A8GrZUqmyCR9GezhY+DG" +
                        "N0MgPl3QU8KqZ/9ldBzu1Tbr07HJua0HNAFsSy364iooXLfhYb6DmUXQiOozxUgwJ90hsNtah7tsxDdFXrDEgTip73HtEqvnQnyY" +
                        "4gxmSqjg0fl7SyIRZ0XHAf5lVCFW9qKlhLbC1/bRYyqaIXjUJNZgwHeS4gNmqHNGb8xv1kwtr36bOlsUzUQwD2BUkRiYfQlfXvMC" +
                        "IBDSZhaVaKn9tJIV8OpR/y0ftauSXCdGbbgwxbvNhLeTd2yauy6AtR8sGbY0xJN6un1qkVezIugK1giPubhKM7JpjrJSn5FU2DoL" +
                        "bzGlSgPvKrj1xxWOnHPbx9aIH1oMRaPrY3lOJQ0vI1m7+KVVBfS7CuNgo6kZJqoNayqP+wxgEFzXFibNGofZgstZed4XMFINpIju" +
                        "8PtbMvWU57i57OdcvryiKgwjgcLJ2c/JVnkONUX5+a0QNW1gcoPDkIlwabXU67OrRSz38jtjvI0=",
                        "ewogICJ0aW1lc3RhbXAiIDogMTczMjM5NjA4NjM2NiwKICAicHJvZmlsZUlkIiA6ICI3OThlZWM3OGYzNGU0MGJhYjlmNjk0ODIw"
                        + "YzM3ZTcxYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJFbENoYW5vbk1DIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKIC"
                        + "AidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5l"
                        + "dC90ZXh0dXJlL2RlM2YzZjhmMTYxMzAxMjIzODg1NGY0NWUzMGNhMGM4NGE3MWM0ZGMwNmI4OTRhNTE4NWFlZGI2NTE4MmFkNW"
                        + "YiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
                        "DniMUwoEWYzuzeZ+Is7rboLgELIdgFDdcMyDjx80RxHVmYkIxuz6WRaMxceBR7vIgj0EZ/WIWAaLgzNIDYCojsliY1l8LDnF2NRW"
                        + "j6k0RvpWWxJaQF3DjDuRWLkMcthKN630znQiffsbSFTcNQYKIGoVsuaUZucxYYgBnseeQ7BISRgovo7oU8C0pPnhuQQvEwVwWA"
                        + "oVfgNRh9G3z+JRuo0VYW/02NmR640Dre2ZvO0vtZMJr+uSxSIvdVx1RZ0KuQFUm7i9K/gv0boPy/w02hvVROG/pqCLLx+YztB1"
                        + "jn6hGTdKMqYpJqRG/jKcZx36OtjNHPTTefGIe1FqPLIXqgJUFBMAYq9PHdsXtXgdeNBcjC3apmhM+N12GZT3zzd22ElLS3Md8S"
                        + "DZpOb/6ZRO7DSYBOzPtR8iQENhH5UeBQdAqq6pl0g81A+mYn/QBNeID6ALce+Ver6vLIew4C/QZTyoRPrLAN7TQWvl7bGcsdjK"
                        + "dYndVnL4aOV/9jtUfukbOSIpWnS5vtidvmMveyYveQvC/24E1eyPpu7QJWHSEI6ta4y7nc+vZoCzCHQBD+uYa56dwqI2s0XoDI"
                        + "8u+Xed9lsqJgY3MtRH7+oA+HoDcFC8Nz32yfRcpXBHAxRBuL46cHl0Ug0RpH6Gf5tVhx74wUkDp64SLwS/wgmHBz4FPa0=");
        this.damage = 24;
        this.cooldown = 250;
        this.range = 10;
        this.damageTypes = new DamageType[]{DamageType.MAGICAL, DamageType.FIRE};
    }
}
