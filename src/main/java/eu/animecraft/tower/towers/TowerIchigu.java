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
                "eJobTh01cjEY0U6gE3AkxefOKVNqVrZqpJiO1hf3uIftOjyb3aVm5ayeroPHDDY0rDOsZeBfwctxgUP0ERCN7TT2s9GrYP4l4adDrmPp5dO5dAu9b" +
                        "mDg5a0mrQr5FzrakVSmeg0ntevgOZtceQvL9ODDePt5C3J7IsgHDWcZwFDIKRqX3SKx2oGZrkrksZDrztKzZa0IirPax3Q8Kc1b0PxcJ6" +
                        "W30V4s6ifbxHIaqzalzeg0hl5R/9RkP//aiw/XFKZ9z5MGd+APJTbTZB0ZNgIuLFtLJAHaSKv/w8+MIUyv75GBAEEcI1ujOBGLQeJpPo6" +
                        "tLZOqNm81g8wVYv84RhFrW8JwUGWArqZLLDbdNTaaHlJA5Ev503i9rEK65oEWoky9oIyB6mZl/fLTB+IY1G1HhY7wvaAaztp9ZtzJ+LMH" +
                        "QgVZY4+/C4gviWT7jm/tv9iPmgF21aasT7w2h1/D29y1yN18fsgBMimoc9QoVvLRg3RU02+BXGBrpqLgPvvWW9TUqgfUH4W/okcvPLV+e" +
                        "i/1kX2NFgQ2r9lFSyVsRgzzerc1D5OXZZaqf2uljlDvJULk66eMkyMFolLVkavyIzcNHCFA6rIM+Sewe7hjrQjZS8R1TmlG9QLeXZAo4f" +
                        "wXzv/+HBATKO++GCeb/rUczAQe2RbnCLh2P4RGk1MQGk0=",
                "ewogICJ0aW1lc3RhbXAiIDogMTY4NTc1NzAyMDYzMiwKICAicHJvZmlsZUlkIiA6ICI4N2YzOGM1MWE4Yzc0MmNmYTY2YTgxNWExZTI2NzMzYiIsC" +
                        "iAgInByb2ZpbGVOYW1lIiA6ICJCZWR3YXJzQ3V0aWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOi" +
                        "B7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UwYWVhNGJ" +
                        "hZWM1ZmI0ZWVlNzZlY2RhYTJjOTNmZjkyYjU2YWQ3Nzg4M2Y3MjYyZjM4YzRlY2Q1ZjEzZDI5OSIsCiAgICAgICJtZXRhZGF0YSIgOiB7" +
                        "CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
                "wNwGdu9CakZA4Ou32GnzhS6tQxK7uvfYF49FKhGuu7GX1hAA0UeDh/cTu3QonTLH9uX6TRYmb/TO1+s7Xm9X7DA90rBHHO2ZDYh4zIc//yZIrkhgD" +
                        "+xkHDJmGCtb3KcnYQRrKkhLES5yq0ehlrHMSSsMfh2Z/zpK297+fmu3ajs3mXXm7bf21pvoQ2wCbtI4FJe/evS297rtY6gmTaKvkLfGbr" +
                        "Ls85lNgs4g4g3/dIOu8dEky3dxGSRUbqKQpxuxeqcyYzHccO2ZGiJLvl8VEEdpoXtO8VHbQDpmFlp6kvePIAHEgQfvUtqdW8qHacS9ImJ" +
                        "8UXlE41yE+Dtl2ZwrtONylQ1GoP36NaIvXC2kh/OcM/VzGE5qb9T/XpOzASoKPecaCgqJTEZoH6Jx/iCcq4R2daK+xsHd1frUv57GR7eG" +
                        "NURYhE58Q2I9rbTIotdwuHNcoKcwSI/jkL/Gcbj79miAW+WkqSVWAyPrukPd1vLlMw7HwNDfu7cBAFcCnapDxuv5TWYfY68hYCKIMVXkF" +
                        "31WXCrxRmo93GrUe1u9wxRwCEeybbj/WMr50/Z6p1vrPbEFsHTPkhgourZpJ7TbfKVYplRchPA/y2jemiMqQd3DopQHz7f8BI8oXMLIHW" +
                        "lRgBBbd7qt1njBnE5h8BoU1TCgTRgxWyFj+v5bXO7W6Xs=");
        this.damage = 25;
        this.cooldown = 234;
        this.range = 12;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.LIGHT};
    }
}
