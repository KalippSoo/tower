package eu.animecraft.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.towers.TowerIchigu;
import eu.animecraft.tower.towers.TowerItochi;
import eu.animecraft.tower.towers.TowerSonGoku;
import eu.animecraft.tower.towers.Nami.TowerNami;
import eu.animecraft.tower.towers.Nami.TowerNamiClimaStick;

public class TowerManager {
    public List<Tower> availableTower = new ArrayList<>();
    public List<Tower> currentBanner = new ArrayList<>();
    public List<Entity> currentEnemies = new ArrayList<>();
    
    public Map<Player, List<Tower>> activeTowers = new HashMap<>();

    public TowerManager() {
    	this.availableTower.add(new TowerItochi());
        this.availableTower.add(new TowerSonGoku());
        this.availableTower.add(new TowerIchigu());
        this.availableTower.add(new TowerNami());
        this.availableTower.add(new TowerNamiClimaStick());
        
        for(int i = 0; i < 5; i++) {
        	int random = ThreadLocalRandom.current().nextInt(availableTower.size());
            this.currentBanner.add(availableTower.get(random));
        }
        
        new BukkitRunnable() {
			int current;
			@Override
			public void run() {
				for (Player players : activeTowers.keySet()) {
					List<Tower> towers = activeTowers.get(players);
					List<Entity> removal = new ArrayList<>();
					for (Tower tower : towers) {
						
//						if (current >= 10) {
//							current = 0;
//							circle(players, tower.stand.getLocation(), (int) tower.fr);
//						}else {
//							current++;
//						}
						
						if (tower.currentCooldown > 0) {
							tower.currentCooldown--;
						}else {
							tower.currentCooldown = (int) (tower.fc);
							
							Iterator<Entity> entities = currentEnemies.iterator();
							while (entities.hasNext()) {
								Entity entity = entities.next();
								LivingEntity target = ((LivingEntity)entity);
								double
								x1 = tower.stand.getLocation().getX(),
								x2 = entity.getLocation().getX(),
								z1 = tower.stand.getLocation().getZ(),
								z2 = entity.getLocation().getZ();
								
								double distance = calculateDistanceBetweenPoints(x1, z1, x2, z2);
								if (distance > tower.fr)continue;
								
								
								target.damage(tower.fd);
								if (target.isDead()) {
									removal.add(target);
								}
							}
							currentEnemies.removeAll(removal);
							tower.stand.setVelocity(new Vector(0, 2, 0).multiply(0.2f));
						}
						
					}
				}
			}
		}.runTaskTimer(AnimeCraft.instance, 0, 0);
        
    }
    
    public void circle(Player player, Location loc, int radius) {
        for(int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; ++x) {
            for(int y = loc.getBlockY() - radius; y < loc.getBlockY() + radius; ++y) {
                for(int z = loc.getBlockZ() - radius; z < loc.getBlockZ() + radius; ++z) {
                    if ((loc.getBlockX() - x) * (loc.getBlockX() - x) + (loc.getBlockY() - y) * (loc.getBlockY() - y) + (loc.getBlockZ() - z) * (loc.getBlockZ() - z) <= radius * radius && loc.getBlock().getType() == Material.AIR) {
                        player.spawnParticle(Particle.VILLAGER_HAPPY, loc, 1, 0, 0, 0, 0.001);
                    }
                }
            }
        }
    }
    
    public double calculateDistanceBetweenPoints(
    		  double x1, 
    		  double y1, 
    		  double x2, 
    		  double y2) {       
    		    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    		}

    public Tower buyTower() {
        Tower tower = currentBanner.get(ThreadLocalRandom.current().nextInt(currentBanner.size())).clone();
        if (Math.random() * 101.0 <= 1.0) {
            tower.setShiny(true);
        }
        return tower;
    }

    public Tower getTower(int id) {
    	
        for (Tower towers : this.availableTower) {
        	if (towers.getId() == id)
        		return towers.clone();
        }

        return null;
    }

    public Tower getTowerByUUID(Data data, String uuid) {
        if (data == null){
            System.out.println("Data is null ! FUCK");
            return null;
        }

        for (Tower towers : data.getTowers()){
            if (towers.uuid.toString().equals(uuid)) {
                return towers;
            }
        }
        return null;
    }

    public Tower getTowerByItemVersion(Player player, ItemStack item) {
    	Data data = Utils.getData(player);
        Iterator<?> var4 = data.getTowers().iterator();

        while(var4.hasNext()) {
            Tower towers = (Tower)var4.next();
            if (towers.getItemVersion(0).isSimilar(item)) {
                return towers;
            }
        }

        return null;
    }
    
    public Tower getTowerByItemVersion(Data data, ItemStack item) {
        Iterator<?> var4 = data.getTowers().iterator();

        while(var4.hasNext()) {
            Tower towers = (Tower)var4.next();
            if (towers.getItemVersion(0).isSimilar(item)) {
                return towers;
            }
        }

        return null;
    }
}
