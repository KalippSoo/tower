package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.List;

import eu.animecraft.controler.EnemyCustom;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class MobList {
	
	public static List<EnemyCustom> Namek_ACT1(Arena arena, int wave) {
		List<EnemyCustom> queue = new ArrayList<EnemyCustom>();
		
		switch (wave) {
		case 1:
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 150, "&eSkeleton", arena, true, true), 2));
			break;
		case 2:
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 175, "&eSkeleton", arena, true, true), 4));
			break;
		case 3:
			queue.addAll(add(new EnemyCustom(EntityTypes.HORSE, 1.5f, 400, "&dHorsy", arena, true, true), 1));
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 200, "&eSkeleton", arena, true, true), 6));
			break;
		case 4:
			queue.addAll(add(new EnemyCustom(EntityTypes.HORSE, 1.5f, 425, "&dHorsy", arena, true, true), 1));
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 225, "&eSkeleton", arena, true, true), 1));
			break;
		case 5:
			queue.addAll(add(new EnemyCustom(EntityTypes.HORSE, 1.5f, 450, "&dHorsy", arena, true, true), 1));
			queue.addAll(add(new EnemyCustom(EntityTypes.ZOMBIE, 2f, 150, "&eSuper ZOMBIE", arena, true, true), 4));
			break;
		case 6:			
			queue.addAll(add(new EnemyCustom(EntityTypes.HORSE, 1.5f, 475, "&dHorsy", arena, true, true), 2));
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 300, "&eSkeleton", arena, true, true), 5));
			queue.addAll(add(new EnemyCustom(EntityTypes.ZOMBIE, 2f, 175, "&eSuper ZOMBIE", arena, true, true), 6));
			break;
		case 7:			
			queue.addAll(add(new EnemyCustom(EntityTypes.HORSE, 1.5f, 500, "&dHorsy", arena, true, true), 3));
			queue.addAll(add(new EnemyCustom(EntityTypes.SKELETON, 1.5f, 325, "&eSkeleton", arena, true, true), 7));
			queue.addAll(add(new EnemyCustom(EntityTypes.ZOMBIE, 2f, 200, "&eSuper ZOMBIE", arena, true, true), 8));
			break;
		case 8:			
			queue.addAll(add(new EnemyCustom(EntityTypes.ELDER_GUARDIAN, 0.2f, 5000, "&dTIRANT", arena, true, true), 1));
			queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, 1.5f, 750, "&eBrûle tout", arena, true, true), 2));
			break;
		case 9:			
			queue.addAll(add(new EnemyCustom(EntityTypes.ELDER_GUARDIAN, 1.5f, 5200, "&dTIRANT", arena, true, true), 2));
			queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, 1.5f, 750, "&eBrûle tout", arena, true, true), 4));
			break;
		case 10:
			queue.add(new EnemyCustom(EntityTypes.IRON_GOLEM, .9f, 3500, "&cGolem", arena, false, false));
			break;
		}
		return queue;
	}
	
	
	
	private static List<EnemyCustom> add(EnemyCustom custom, int count) {
		List<EnemyCustom> queue = new ArrayList<EnemyCustom>();
		for (int i = 1;i <= count; i++) {
			queue.add(custom);
		}
		return queue;
	}
	
}
