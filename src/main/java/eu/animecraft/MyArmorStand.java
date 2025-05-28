package eu.animecraft;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;

import eu.animecraft.arena.Arena;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.event.tower.TowerHittingTargetEvent;
import eu.animecraft.tower.Tower;
import net.minecraft.server.v1_16_R3.EntityArmorStand;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.MobEffect;
import net.minecraft.server.v1_16_R3.MobEffects;
import net.minecraft.server.v1_16_R3.WorldServer;

public class MyArmorStand extends EntityArmorStand{

	Tower tower;
	Location location;
	int currentReload;
	Entity stands;
	//ArmorStand stats;
	public Arena arena;
	
	public MyArmorStand(Location location, Tower tower, Arena arena) {
		super(EntityTypes.ARMOR_STAND, ((CraftWorld)location.getWorld()).getHandle());
		setPosition(location.getX(), location.getY(), location.getZ());
		this.tower = tower;
		this.location = location;
		this.currentReload = 0;
		this.arena = arena;
		this.persist = true;
		this.stands = this.getBukkitEntity();
		
		this.setNoGravity(false);
		updateEquipment();
	}

	
	public static MyArmorStand create(Location location, Tower tower, Arena arena) {
		
		MyArmorStand armorStand = new MyArmorStand(location, tower.clone(), arena);
		WorldServer world = ((CraftWorld)location.getWorld()).getHandle();
		world.addEntity(armorStand, SpawnReason.CUSTOM);
		return armorStand;
	}
	
	List<Entity> enemies = AnimeCraft.instance.getTowerManager().currentEnemies;
	List<LivingEntity> targets = new ArrayList<LivingEntity>();
	
	public void tick() {
		super.tick();
		name();
		
		if (currentReload>0) {
			currentReload--;
		}else {
			addEffect(new MobEffect(MobEffects.GLOWING, 5), Cause.UNKNOWN);
			boolean find = false;
			for (Entity entity : enemies) {
				
				if (entity.isDead())continue;
				if (entity instanceof Item)continue;
				LivingEntity target = ((LivingEntity)entity);
				
				double
				x1 = stands.getLocation().getX(),
				x2 = entity.getLocation().getX(),
				z1 = stands.getLocation().getZ(),
				z2 = entity.getLocation().getZ(),
				y1 = stands.getLocation().getY(),
				y2 = entity.getLocation().getY();
				
				double distance = calculateDistanceBetweenPoints(x1, z1, y1, x2, z2, y2);
				if (distance > tower.fr)continue;

				find = true;
				targets.add(target);
			}
			if (find) {
				currentReload = tower.fc;
				Bukkit.getPluginManager().callEvent(new TowerHittingTargetEvent(tower, arena, targets, this));
			}
		}
	}
	
    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {
        
        List<Location> circleBlocks = new ArrayList<Location>();

        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();
     
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                 
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                 
                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {
                     
                        Location l = new Location(centerBlock.getWorld(), x, y, z);
                        circleBlocks.add(l);
                        
                    }
                }
            }
        }
     
        return circleBlocks;
    }
 
    
    int current = 0, MaxTick = 20;
    public void circleInput(Player player) {
    	
    	if (current >= MaxTick) {
    		List<Location> blocksLocation = generateSphere(stands.getLocation(), (int) tower.fr, true);
    		for (Location locs : blocksLocation) {
    			NMSClass.sendParticle(player, Particle.VILLAGER_HAPPY, true, locs, 0, 0, 0, 0.1f, 1);
    		}
    		
    		current = 0;
    	}
    	current++;
    	
    }
	
	public double calculateDistanceBetweenPoints(
	    		  double x1, 
	    		  double y1,
	    		  double z1,
	    		  double x2, 
	    		  double y2,
	    		  double z2) {       
	    		    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1) + ((z2-z1) * (z2-z1)));
	    		}
	
	void name() {
		this.ticksFarFromPlayer = 0;	
		this.activatedTick = MinecraftServer.currentTick;
	}
}








