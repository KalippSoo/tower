package eu.animecraft.tower;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.data.components.TowerComponent;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.tools.Rarity;

public class TowerManager {
    public List<Tower> availableTower = new ArrayList<>();
    public List<Tower> currentBanner = new ArrayList<>();
    public List<Entity> currentEnemies = new ArrayList<>();
    
    public Map<String, List<Tower>> towerByRarity = new HashMap<String, List<Tower>>();
    public Map<Player, List<TowerComponent>> activeTower = new HashMap<>();

    public TowerManager() {
    	
    	String path = getClass().getPackage().getName() + ".towers";
    	
    	for (Class<?> clazz : new Reflections(path).getSubTypesOf(Tower.class)) {
    		
    		try {
    			List<Tower> towerList;
				Tower tower = (Tower) clazz.getDeclaredConstructor().newInstance();
				
				String rarity = tower.getRarity().name();
				if (towerByRarity.containsKey(rarity)) {
					towerList = towerByRarity.get(rarity);
				}else {
					towerList = new ArrayList<Tower>();
				}
				towerList.add(tower);
				towerByRarity.put(rarity, towerList);
				this.availableTower.add(tower);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	new BukkitRunnable() {
			int currentHours = 0;
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				if (currentHours != cal.getTime().getHours()) {
					currentHours = cal.getTime().getHours();
					Bukkit.getOnlinePlayers().forEach(players -> Utils.sendMessages(players, "&f&lThe banner has updated new units are now there!"));
					updateBanner();
				}

			}
		}.runTaskTimer(AnimeCraft.instance, 0, 600);
    	
    	updateBanner();
    }
    
    public void updateBanner() {
    	this.currentBanner.clear();
    	this.currentBanner.add(towerByRarity.get("MYTHIC").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("MYTHIC").size())));
    	this.currentBanner.add(towerByRarity.get("LEGENDARY").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("LEGENDARY").size())));
    	this.currentBanner.add(towerByRarity.get("EPIC").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("EPIC").size())));
    	this.currentBanner.add(towerByRarity.get("EPIC").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("EPIC").size())));
    	this.currentBanner.add(towerByRarity.get("RARE").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("RARE").size())));
    	this.currentBanner.add(towerByRarity.get("COMMON").get(ThreadLocalRandom.current().nextInt(towerByRarity.get("COMMON").size())));
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
    public void circleInput(Player player, int radius, Set<ArmorStand> set) {
    	
    	if (current >= MaxTick) {
    		for (ArmorStand stands : set) {
        		List<Location> blocksLocation = generateSphere(stands.getLocation(), radius, true);
        		for (Location locs : blocksLocation) {
        			NMSClass.sendParticle(player, Particle.VILLAGER_HAPPY, true, locs, 0, 0, 0, 0.1f, 1);
        		}
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

    public List<Tower> buyTower(int count) {
    	List<Tower> towerList = new ArrayList<>();
        for (int i = 0; i<=count; i++) {
           //Tower tower = currentBanner.get(ThreadLocalRandom.current().nextInt(currentBanner.size())).clone();
        	Tower tower = null;
            double random = Math.random()*Rarity.getMaxChance(), previous = 0;
            for (Tower towers:currentBanner) {
                previous+=towers.getRarity().getDropChance();
                if (random<=previous) {
                	tower=towers.clone();
                	break;
                }
            }
            
            if (Math.random() * 101.0 <= 1.0) {
                tower.setShiny(true);
            }
            towerList.add(tower);
        }
        return towerList;
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
    
    public int getPlacementInSelectedArray(Data data, String uuid) {
    	int count = 0;
    	for (String string : data.getListSelected()) {
    		if (string.equals(uuid)) {
    			break;
    		}
    		count++;
    	}
    	return count;
    }
    
    public Tower getTowerByItemVersion(Player player, ItemStack item) {
    	Data data = Utils.getData(player);
        Iterator<?> var4 = data.getTowers().iterator();

        while(var4.hasNext()) {
            Tower towers = (Tower)var4.next();
            if (isSimilar(towers.towerItem, item)) {
                return towers;
            }
        }

        return null;
    }
    
    private boolean isSimilar(ItemStack original, ItemStack item) {
    	return 
    			original.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName()) && 
    			original.getItemMeta().getLore().get(0).equals(item.getItemMeta().getLore().get(0)) && 
    				original.getItemMeta().getLore().get(1).equals(item.getItemMeta().getLore().get(1)) &&
    				original.getItemMeta().getLore().get(2).equals(item.getItemMeta().getLore().get(2));
    }
}









