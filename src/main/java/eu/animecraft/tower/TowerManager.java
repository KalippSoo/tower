package eu.animecraft.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.data.components.TowerComponent;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.towers.TowerBorosArmored;
import eu.animecraft.tower.towers.TowerBorosFree;
import eu.animecraft.tower.towers.TowerBorosLightningBurst;
import eu.animecraft.tower.towers.TowerIchigu;
import eu.animecraft.tower.towers.TowerItochi;
import eu.animecraft.tower.towers.TowerNami;
import eu.animecraft.tower.towers.TowerNamiClimaStick;
import eu.animecraft.tower.towers.TowerSonGoku;
import eu.animecraft.tower.towers.TowerTamanegy;

public class TowerManager {
    public List<Tower> availableTower = new ArrayList<>();
    public List<Tower> currentBanner = new ArrayList<>();
    public List<Entity> currentEnemies = new ArrayList<>();
    
    public Map<Player, List<TowerComponent>> activeTower = new HashMap<>();

    public TowerManager() {
    	
//    	this.availableTower.add(new TowerItochi());
//        this.availableTower.add(new TowerSonGoku());
//        this.availableTower.add(new TowerIchigu());	
//        //NAMI
//        this.availableTower.add(new TowerNami());
//        this.availableTower.add(new TowerNamiClimaStick());
//        //BOROS
//        this.availableTower.add(new TowerBorosArmored());
//        this.availableTower.add(new TowerBorosFree());
//        this.availableTower.add(new TowerBorosLightningBurst());
//        
//        this.availableTower.add(new TowerTamanegy());
    	updateBanner();
    }
    
    public void updateBanner() {
        this.currentBanner.add(availableTower.get(8));
        for(int i = 0; i < availableTower.size(); i++) {
        	Tower tower = availableTower.get(i);
        	if (tower.evo == null) {
            	int random = ThreadLocalRandom.current().nextInt(availableTower.size());
                this.currentBanner.add(availableTower.get(random));
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
    	List<Tower> towers = new ArrayList<>();
        for (int i = 0; i<=count; i++) {
            Tower tower = currentBanner.get(ThreadLocalRandom.current().nextInt(currentBanner.size())).clone();
            if (Math.random() * 101.0 <= 1.0) {
                tower.setShiny(true);
            }
            towers.add(tower);
        }
        return towers;
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









