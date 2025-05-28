package eu.animecraft.tower;

import eu.animecraft.tower.Passive.AttackType;

public class TowerAttack {

	public static AttackType getType(int id) {
		AttackType type = AttackType.SINGLE;
		switch (id) {
		case 0: type = AttackType.FULL; break;
		}
		return type;
	}

}
