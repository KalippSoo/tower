package eu.animecraft.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.inventory.ItemStack;

import eu.animecraft.data.Data;
import eu.animecraft.tower.towers.TowerIchigu;
import eu.animecraft.tower.towers.TowerItochi;
import eu.animecraft.tower.towers.TowerSonGoku;
import eu.animecraft.tower.towers.Nami.TowerNami;
import eu.animecraft.tower.towers.Nami.TowerNamiClimaStick;

public class TowerManager {
    public List<Tower> availableTower = new ArrayList<>();
    public List<Tower> currentBanner = new ArrayList<>();

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

    }

    public Tower buyTower() {
        Tower tower = currentBanner.get(ThreadLocalRandom.current().nextInt(currentBanner.size())).clone();
        if (Math.random() * 101.0 <= 1.0) {
            tower.setShiny(true);
        }
        return tower;
    }

    public Tower getTower(int id) {
        Iterator<?> var3 = this.availableTower.iterator();

        while(var3.hasNext()) {
            Tower towers = (Tower)var3.next();
            if (towers.getId() == id) {
                return towers.clone();
            }
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
