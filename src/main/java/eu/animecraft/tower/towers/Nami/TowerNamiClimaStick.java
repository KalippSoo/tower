package eu.animecraft.tower.towers.Nami;

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
				"gccxPJgYRRGb4AreuBoCAWICAwARtkVBCGrrKKFwOI5lDXKQar4WNREDmD3KEbzl9Ry0JyKXIPZOl0kkYfa+Z4TvJWt34mc9Cil8HqpMzeROsnGJqiHn+B"
				+ "pTrsm1ROy/J176jz9L8uHjJBcrP2VJCdU9cvWAKCz6hLVZ9WSMhqPXS8iATpYynGkms5FE0NSzbsQwVNr7ySWxpXdyX7XTABdarVIGKnJ4M4gvoXMBC6"
				+ "MUzDEdiWBcPmgLsLrIO/DyhQ9uXthC/xbs7FU/3ZIh2UnyjolmDiuFPCe6F7c/jfsvfdsToKk4tsQLB9U49eWDc2SGh1HOqzuR5ROLSn4gyIXH/6TXES"
				+ "iFiaoCYQOoD8bpGlQdHnPnPCQSxT8qfneC0MbsD3s2Tztw6Rc+GkIomnBze5VJp93H3BdxkMaMtAQKt5W2kq9ZoyL5sX06LJu/giGMQbOVqjhx4iGWxc"
				+ "gwOPNsbOjahFv6m8fMsW+LG/qKoWxlYOe5vFAOB1dO1xfHYdGCc/N2FY4ya3dZy7UyInFGn1Yv213/p15+UqUbfYw2ZGGReG7w4fYKFJD3jKcc2LA/WF"
				+ "MdXoRCiu15b4Yx08KAAcIs8rPqx5RAtQl7kTxAwk4I9w1bqwH+bDStF1h7HT7p6St/ljueT0Pf9+JTONVnEigargXGfVp6EdXqhGk=", 
				null,
				null);
        this.damage = 245;
        this.cooldown = 280;
        this.range = 24;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.WIND};
	}
	
}
