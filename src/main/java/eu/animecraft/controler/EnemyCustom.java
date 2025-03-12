package eu.animecraft.controler;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import eu.animecraft.PathfinderGoalTower;
import eu.animecraft.arena.Arena;
import eu.animecraft.data.components.Utils;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.DamageSource;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.WorldServer;

public class EnemyCustom extends EntityCreature{

	private String name;
	public double speed, health;
	private EntityTypes<? extends EntityCreature> entityTypes;
	private boolean canBeStun, canBeSlow;
	private Arena arena;
	
	public EnemyCustom(EntityTypes<? extends EntityCreature> entitytypes, double speed, double health, String name, Arena region, boolean canBeStun, boolean canBeSlow) {
		super(entitytypes, ((CraftWorld)region.paths.get(0).getWorld()).getHandle());
		this.name = Utils.color(name);
		this.health = health;
		this.speed = speed;
		this.entityTypes = entitytypes;
		this.canBeStun = canBeStun;
		this.canBeSlow = canBeSlow;
		this.arena=region;
		
		Location l = region.paths.get(0);
		
		this.setLocation(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
		this.persistent = true;
		this.maxNoDamageTicks = 0;

		this.collides = false;
		
		drops.clear();
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(health);
		this.getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).setValue(100);
		
		this.setCustomNameVisible(true);
		this.setHealth((float) health);
		
		//pathfinder
		initPathfinder();
		this.goalSelector.a(0, new PathfinderGoalTower(this, region, speed));
	}
	
	@Override
	protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
	}
	@Override
	protected void dropExperience() {
	}
	
	@Override
	public void initPathfinder() {
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		name();
	}
	
	private void preSpawn(Arena region) {
		
		EnemyCustom custom = new EnemyCustom(entityTypes, speed, health, name, region, canBeStun, canBeSlow);
		WorldServer world = ((CraftWorld)region.paths.get(0).getWorld()).getHandle();
		world.addEntity(custom, SpawnReason.CUSTOM);
		this.arena.enemies.add(custom.getBukkitEntity());
	}
	
	public void spawn() {
		preSpawn(arena);
	}
//	public void spawnSpecific(Region region) {
//		preSpawn(region);
//	}
	
	void name() {
		this.setCustomName(new ChatComponentText(Utils.color(name) +Utils.setHealth((int) this.getHealth())));
		this.ticksFarFromPlayer = 0;	
		this.activatedTick = MinecraftServer.currentTick;
	}

	public Arena getArena() {
		return arena;
	}
	
}
