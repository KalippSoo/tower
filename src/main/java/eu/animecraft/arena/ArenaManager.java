package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.arena.ArenaNewWaveEvent;
import eu.animecraft.event.arena.ArenaPreChargeEvent;
import eu.animecraft.event.arena.ArenaWaveEndEvent;
import eu.animecraft.listerners.menu.RewardsMenu;
import eu.animecraft.play.Play;

public class ArenaManager extends EventListener{

	public static List<Arena> arena=new ArrayList<Arena>();
	public static List<Block> blocks = new ArrayList<Block>();
	public static Map<Integer, List<Arena>> currentArenas=new HashMap<>();
	
	public Arena test = null;
	
	public World world = Bukkit.getWorld("world");
	
	public ArenaManager() {
		test = new Arena();
		test.name = "Beer Land";
		test.tp = new Location(world, -68.5, 123, -129.5, -165, 10);
		test.health = 100;
		test.id = 1;
		
		test.bound.setMin(new Location(world, -17, 200, -97));
		test.bound.setMax(new Location(world, -80, 100, -160));
		test.bound.assignCorrectBound();
		
		test.paths.add(new Location(world, -65.5, 122, -147.5));
		test.paths.add(new Location(world, -65.5, 122, -133.5));
		test.paths.add(new Location(world, -57.5, 122, -133.5));
		test.paths.add(new Location(world, -57.5, 122, -127.5));
		test.paths.add(new Location(world, -69.5, 122, -127.5));
		test.paths.add(new Location(world, -69.5, 122, -120.5));
		test.paths.add(new Location(world, -63.5, 122, -120.5));
		test.paths.add(new Location(world, -63.5, 122, -113.5));
		test.paths.add(new Location(world, -70.5, 122, -113.5));
		test.paths.add(new Location(world, -70.5, 122, -108.5));
		test.paths.add(new Location(world, -65.5, 122, -108.5));
		test.paths.add(new Location(world, -65.5, 122, -106.5));
		
		test.itemStack = Utils.createItem(Material.BARREL, 1, "&eBeer Land");
		arena.add(test);
		
		currentArenas.put(1, new ArrayList<>());
	}
	
	public static void callNewArena(List<Player> players, Arena arena, int id) {
		Bukkit.getPluginManager().callEvent(new ArenaPreChargeEvent(arena, players, id));
	}

	public static Arena searchPlaceToPlay(Play play, int mode) {
		
		List<Arena> a = currentArenas.get(mode);
		if (currentArenas.containsKey(mode)) {
			if (a.isEmpty()) {
				Arena target = createArena(mode);
				target.players=new ArrayList<>(play.getPlayers());
				a.add(target);
				return target;
			}else {
				Arena available = search(mode);
				available.players=new ArrayList<>(play.getPlayers());
				a.add(available);
				return available;
			}
		}
		
		return null;
	}
	
	public static Arena createArena(int mode) {
		System.out.println("Creating a new arena!");
		int size = (16*5) * ((currentArenas.get(mode).size()>0)?(currentArenas.get(mode).size()+1):1);
		Arena target = new Arena(mode);
		for (Arena arenas : arena) {
			if (target.id == arenas.id) {
				arenas.createCopy(size);
				target.health = arenas.health;
				target.tp = arenas.tp.clone().add(size, 0,0);
				arenas.paths.forEach(location->target.paths.add(location.clone().add(size,0,0)));
				target.isBusy = true;
				break;
			}
		}
		return target;
	}
	public static Arena search(int mode) {
		Arena available = null;
		List<Arena> current=currentArenas.get(mode);
		for (Arena arenas : current) {
			if (arenas.isBusy && arenas.bound.getMax() == null)continue;
			available = arenas;
			break;
		}
		if (available==null)
			available=createArena(mode);
		if (!available.isBusy)
			available.isBusy = true;
		return available;
	}
	
	@EventHandler
	public void onNewWaveEvent(ArenaNewWaveEvent e) {
		Arena arena = e.getArena();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				arena.a=true;
				arena.spawnCurrentWave();
				cancel();
			}
		}.runTaskLater(instance, 3*20);
	}
	
	@EventHandler
	public void onWaveEndEvent(ArenaWaveEndEvent e) {
		Arena arena = e.getArena();
		for (Player players : arena.players) {
			Data data = Utils.getData(players);
			resetPlayerToSpawn(players);
			Utils.sendMessages(players, "&aYou won !");
			data.setArena(null);
			new RewardsMenu().set(players);
		}
		System.out.println(true);
		arena.reset();
		
	}
}


