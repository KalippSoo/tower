package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.AnimeCraft;
import eu.animecraft.controler.EnemyCustom;
import eu.animecraft.data.Data;
import eu.animecraft.data.components.TowerComponent;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.arena.ArenaGameLostEvent;
import eu.animecraft.event.arena.ArenaNewWaveEvent;
import eu.animecraft.event.arena.ArenaWaveEndEvent;
import eu.animecraft.event.player.PlayerWinEvent;

public class Arena {

	public String rawName, name;
	public Bound bound = new Bound();
	public Location tp;
	public int health = 20;
	public List<Player> players = new ArrayList<>();
	public Map<Entity, EnemyCustom> enemies = new HashMap<>();
	public List<EnemyCustom> queue = new ArrayList<>();
	public List<Location> paths = new ArrayList<>();
	
	private int act=0;
	private int[] bonusHealthAct = {0, 50, 100, 150, 200, 250};
	
	private Arena arena;
	
	//GAMEPLAY RELATED
	boolean a = false;
	int wave = 0;
	
	int valueTimeOut = 10;
	public int timeout = valueTimeOut;
	
	public ItemStack itemStack;
	
	public int id = 0;
	public boolean isBusy = false;
	
	public Arena() {}
	public Arena(int id) {
		this.id=id;
		this.arena = this;
	}
	
	public Arena(String name, int health, Bound bound) {
		this.name = name;
		this.rawName = Utils.stripColor(name).toLowerCase();
		this.health = health;
		this.bound = bound;
		this.arena = this;
	}
	
	public Arena(int id, String name, ItemStack display, Location teleport, Location min, Location max, List<Location> paths) {
		this.arena = this;
		
		this.id = id;
		this.name = name;
		this.rawName=Utils.stripColor(name);
		
		this.itemStack = display;
		
		this.bound.setMin(min);
		this.bound.setMax(max);
		this.bound.assignCorrectBound();
		this.tp = teleport;
		
		this.paths = paths;

		ArenaManager.currentArenas.put(id, new ArrayList<>());
	}
	
	public void reset() {
		
		for (Player players : this.players) {
			List<TowerComponent> towers = AnimeCraft.instance.getTowerManager().activeTower.get(players);
			for (TowerComponent tower : towers) {
				for (ArmorStand stand : tower.towers.keySet()) {
					stand.remove();
				}
				tower.tower.clearCount();
			}
			Data data = Utils.getData(players);
			data.setArena(null);
			data.po = 0;
		}
		
		this.enemies.keySet().forEach(entities->entities.remove());
		
		this.wave = 0;
		this.enemies.clear();
		this.players.clear();
		this.isBusy=false;
	}
	
	public void spawnCurrentWave() {
		
		new BukkitRunnable() {
			int autoSkip = 10;
			@Override
			public void run() {
				if (!a)return;
				if (wave < 10 && queue.isEmpty() && (autoSkip == 0 || enemies.isEmpty())) {
					newWave();
					cancel();
					return;
				}
				if (wave < 10 && queue.isEmpty()) {
					autoSkip--;
				}
				
				if (wave == 10) {
					if (queue.isEmpty()&&enemies.isEmpty()) {
						players.forEach(player->Bukkit.getPluginManager().callEvent(new PlayerWinEvent(player, id)));
						Bukkit.getPluginManager().callEvent(new ArenaWaveEndEvent(arena));
						cancel();
						return;
					
					}
				}
				
				if (!queue.isEmpty()) {
					EnemyCustom enemyCustom = queue.get(0);
					enemyCustom.spawn();
					queue.remove(0);
				}
				
			}
		}.runTaskTimer(AnimeCraft.instance, 20, 20);

	}
	
	public void newWave() {
		//Wait 5 seconds before the new wave start !
		a=false;
		wave++;
		queue = new ArrayList<EnemyCustom>(MobList.getMobsLinkToAct(this, wave));
		Bukkit.getPluginManager().callEvent(new ArenaNewWaveEvent(this, wave));
	}
	
	public void hurt(int hurt) {
		if (hurt >= health) {
			health = 0;
			Utils.callEvent(new ArenaGameLostEvent(this));
			return;
		}
		health -= hurt;
	}
	
	public void createCopy(int size) {
		List<Block> blocks=bound.getCopy();
		for (Block block : blocks) {
			
			Block bloc=block.getWorld().getBlockAt(block.getLocation().clone().add(size, 0, 0));
			bloc.setType(block.getType());
			bloc.setBlockData(block.getBlockData());
		}
		
	}
	public int getAct() {
		return act;
	}
	public void setAct(int act) {
		this.act = act;
	}
	public int[] getBonusHealthAct() {
		return bonusHealthAct;
	}
	
}
