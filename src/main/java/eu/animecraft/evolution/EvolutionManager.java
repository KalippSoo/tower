package eu.animecraft.evolution;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.data.components.Utils;

public class EvolutionManager {

	private final ArmorStand armorStand;
	private Location loc = new Location(Utils.overworld, 42.5, 105, 0.5);
	
	public EvolutionManager() {
		
		armorStand = Utils.overworld.spawn(loc, ArmorStand.class,
				stand -> {
					stand.setGravity(false);
					stand.setInvisible(true);
					stand.setCustomNameVisible(true);
					stand.setCustomName(Utils.color("&5&lEvolution"));
				});
		
		for (Entity entity : armorStand.getNearbyEntities(0.01, 2, 0.01)) {
			if (entity instanceof ArmorStand)entity.remove();
		}
		new BukkitRunnable() {

			Location location = loc.clone();
			double x, y = 2.5, z;
			double pi = 0;
			double radius = 1.75;
			
			@Override
			public void run() {
				pi+=Math.PI/10;
				
				x = Math.cos(pi) *radius;
				z = Math.sin(pi) *radius;
				
				Bukkit.getOnlinePlayers().forEach(players->NMSClass.sendParticle(players, Particle.PORTAL, false, location.clone().add(x,y,z), .2f, .2f, .2f, (float) .2, 5));
				
				if (Math.random()>.4) y-=0.1;
				if (y <= -1) {
					y = 2.5;
				}
			}
		}.runTaskTimer(AnimeCraft.instance, 0, 0);
	}

	public ArmorStand getArmorStand() {
		return armorStand;
	}
	
}
