package eu.animecraft.controler;

import org.bukkit.Location;
import org.bukkit.Particle;
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
	public PathfinderGoalTower pathfinderTower = null;
	public boolean boss = false;
	
	public EnemyCustom(EntityTypes<? extends EntityCreature> entitytypes, double speed, double health, String name, Arena region, boolean canBeStun, boolean canBeSlow) {
		super(entitytypes, ((CraftWorld)region.paths.get(0).getWorld()).getHandle());
		this.name = Utils.color(name);
		this.health = health;
		this.speed = speed;
		this.entityTypes = entitytypes;
		this.canBeStun = canBeStun;
		this.canBeSlow = canBeSlow;
		this.arena=region;
		this.ai = true;
		
		if (entitytypes == EntityTypes.IRON_GOLEM) {
			boss = true;
		}
		
		Location l = region.paths.get(0);
		
		this.setLocation(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
		this.persistent = true;
		this.maxNoDamageTicks = 0;

		this.collides = false;
		
		drops.clear();
		int completeHealth = (int) (health+((health*arena.getBonusHealthAct()[arena.getAct()])/100));
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(completeHealth);
		this.getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).setValue(100);
		
		this.setCustomNameVisible(true);
		this.setHealth((float) completeHealth);
		
		//pathfinder
		initPathfinder();
		pathfinderTower = new PathfinderGoalTower(this, region, speed);
		this.goalSelector.a(0, pathfinderTower);
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
		if (boss) {
			this.getBukkitEntity().getWorld().spawnParticle(Particle.LAVA, this.getBukkitEntity().getLocation().add(0,1.5,0), 1, 0, 0, 0, .1f);
		}
	}
	
	private void preSpawn(Arena region) {
		
		EnemyCustom custom = new EnemyCustom(entityTypes, speed, health, name, region, canBeStun, canBeSlow);
		WorldServer world = ((CraftWorld)region.paths.get(0).getWorld()).getHandle();
		world.addEntity(custom, SpawnReason.CUSTOM);
		this.arena.enemies.put(custom.getBukkitEntity(), this);
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
