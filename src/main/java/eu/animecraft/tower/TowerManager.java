package eu.animecraft.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import eu.animecraft.data.Data;
import eu.animecraft.tower.towers.TowerIchigu;
import eu.animecraft.tower.towers.TowerItochi;
import eu.animecraft.tower.towers.TowerSonGoku;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TowerManager {
    public List<Tower> availableTower = new ArrayList<>();
    public List<Tower> currentBanner = new ArrayList<>();

    public TowerManager() {
        this.availableTower.add(new TowerItochi());
        this.availableTower.add(new TowerSonGoku());
        this.availableTower.add(new TowerIchigu());

        for(int i = 0; i < 5; ++i) {
            this.currentBanner.add(this.availableTower.get(ThreadLocalRandom.current().nextInt(this.availableTower.size())));
        }

    }

    public Tower buyTower() {
        Tower tower = currentBanner.get(ThreadLocalRandom.current().nextInt(currentBanner.size())).clone();
        if (Math.random() * 101.0 < 1.0) {
            tower.setShiny(true);
        }

        tower.statsReroll.a = ThreadLocalRandom.current().nextDouble(-10, 5);
        tower.statsReroll.b = ThreadLocalRandom.current().nextDouble(-10, 5);
        tower.statsReroll.c = ThreadLocalRandom.current().nextDouble(-10, 5);
        tower.updateStats();
        ;

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
