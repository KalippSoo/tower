package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.List;

import eu.animecraft.controler.EnemyCustom;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class MobList {
	
	public static List<EnemyCustom> getMobsLinkToAct(Arena arena, int wave) {
		List<EnemyCustom> queue = new ArrayList<EnemyCustom>();
		
		switch (arena.id) {
		case 1:
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
				queue.addAll(add(new EnemyCustom(EntityTypes.GUARDIAN, 0.4f, 4000, "&dTIRANT", arena, true, true), 1));
				queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, .9f, 2500, "&eBrûle tout", arena, true, true), 2));
				break;
			case 9:			
				queue.addAll(add(new EnemyCustom(EntityTypes.GUARDIAN, .4f, 5200, "&dTIRANT", arena, true, true), 2));
				queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, .9f, 3700, "&eBrûle tout", arena, true, true), 4));
				break;
			case 10:
				queue.add(new EnemyCustom(EntityTypes.IRON_GOLEM, .6f, 35000, "&cGolem", arena, false, false));
				break;
			}
			break;
		case 2:
			switch (wave) {
			case 1:
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 400, "&eWither Skeleton", arena, true, true), 2));
				break;
			case 2:
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 425, "&eWither Skeleton", arena, true, true), 4));
				break;
			case 3:
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN_BRUTE, 1.5f, 800, "&cBrute", arena, true, true), 1));
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 450, "&eWither Skeleton", arena, true, true), 6));
				break;
			case 4:
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN_BRUTE, 1.5f, 825, "&cBrute", arena, true, true), 1));
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 475, "&eWither Skeleton", arena, true, true), 1));
				break;
			case 5:
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN_BRUTE, 1.5f, 850, "&cBrute", arena, true, true), 1));
				queue.addAll(add(new EnemyCustom(EntityTypes.ZOMBIFIED_PIGLIN, 2f, 450, "&eSuper ZOMBIE", arena, true, true), 4));
				break;
			case 6:			
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN_BRUTE, 1.5f, 875, "&cBrute", arena, true, true), 2));
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 500, "&eSkeleton", arena, true, true), 5));
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN, 2f, 175, "&eSuper ZOMBIE", arena, true, true), 6));
				break;
			case 7:			
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN_BRUTE, 1.5f, 900, "&cBrute", arena, true, true), 3));
				queue.addAll(add(new EnemyCustom(EntityTypes.WITHER_SKELETON, 1.5f, 525, "&eSkeleton", arena, true, true), 7));
				queue.addAll(add(new EnemyCustom(EntityTypes.PIGLIN, 2f, 200, "&eSuper ZOMBIE", arena, true, true), 8));
				break;
			case 8:			
				queue.addAll(add(new EnemyCustom(EntityTypes.RAVAGER, 0.4f, 8000, "&dTIRANT", arena, true, true), 1));
				queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, .9f, 5600, "&eBrûle tout", arena, true, true), 2));
				break;
			case 9:			
				queue.addAll(add(new EnemyCustom(EntityTypes.RAVAGER, .4f, 10000, "&dTIRANT", arena, true, true), 2));
				queue.addAll(add(new EnemyCustom(EntityTypes.BLAZE, .9f, 7600, "&eBrûle tout", arena, true, true), 4));
				break;
			case 10:
				queue.add(new EnemyCustom(EntityTypes.WITHER, .1f, 80000, "&cGA-GASHT", arena, false, false));
				break;
			}
			
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
