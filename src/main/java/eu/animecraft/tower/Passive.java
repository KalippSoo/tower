package eu.animecraft.tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import eu.animecraft.arena.Arena;
import eu.animecraft.controler.EnemyCustom;

public abstract class Passive {
	
	public void passive(Tower tower, Entity entity) {
		LivingEntity living = (LivingEntity) entity;
		switch (tower.getId()) {
		case 0:
			entity.setFireTicks(60);
			break;
		case 4575:
			living.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 4));
			break;
		}
	}
	
	public List<String> passive(Tower tower) {
		List<String> description = new ArrayList<String>();
		switch (tower.getId()) {
		case 0:
			description.add("&aIgnites all enemies");
			description.add("&aIn range for 3s");
			break;
		case 4575:
			description.add("&aSlows enemies within the");
			description.add("&aSplash range for 2s");
			break;
		}
		
		return description;
	}
	
	public enum AttackType{
		
		SINGLE,
		SPLASH,
		FULL,
		;
		
		public static List<LivingEntity> getTargets(Tower tower, Arena arena, List<LivingEntity> entities){
			List<LivingEntity> finalEntities = new ArrayList<>();
			
			switch (tower.attackType) {
			case SINGLE: return getClosest(tower, arena, entities);
			case SPLASH: return getSplash(tower, arena, entities);
			case FULL: return entities;
			}
			
			return finalEntities;
		}
		
		private static List<LivingEntity> getSplash(Tower tower, Arena arena, List<LivingEntity> entities){
			List<LivingEntity> closest = getClosest(tower, arena, entities);
			
			for (LivingEntity entity : entities) {
				double distance = closest.get(0).getLocation().distance(entity.getLocation());
				if (distance <= 5) {
					closest.add(entity);
				}
			}
			return closest;
		}
		
		private static List<LivingEntity> getClosest(Tower tower, Arena arena, List<LivingEntity> entities){

			Map<Entity, EnemyCustom> enemies = arena.enemies;
			
			LivingEntity living = entities.get(0);
			for (LivingEntity livingEntity : entities) {
				if (living.isDead()) 
					living = livingEntity;
				
				if (living==livingEntity)continue;
				EnemyCustom enemyCustom = enemies.get(livingEntity);
				EnemyCustom targetEnemyCustom = enemies.get(living);
				if (enemyCustom == null)continue;
				if (targetEnemyCustom == null)continue;
				if (targetEnemyCustom.pathfinderTower.locations.size() > enemyCustom.pathfinderTower.locations.size()) {
					living = livingEntity;
					continue;
				}
				if (targetEnemyCustom.pathfinderTower.locations.size() == enemyCustom.pathfinderTower.locations.size()) {
					double currentDistance = targetEnemyCustom.pathfinderTower.locations.get(0).distance(living.getLocation()),
							nextDistance = enemyCustom.pathfinderTower.locations.get(0).distance(livingEntity.getLocation());
					if (currentDistance <= nextDistance) {
						living = livingEntity;
					}
					continue;
				}
			}
			return Arrays.asList(living);
		}
		
	}
	
	public enum TargetPriority{
		
		FIRST,
		LAST,
		STRONGEST,
		WEAKEST,
		;
		
	}
	
}
