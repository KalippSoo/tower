package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.arena.ArenaNewWaveEvent;
import eu.animecraft.event.arena.ArenaPreChargeEvent;
import eu.animecraft.event.arena.ArenaWaveEndEvent;
import eu.animecraft.menu.RewardsMenu;
import eu.animecraft.play.Play;

public class ArenaManager extends EventListener{

	public static List<Arena> arena=new ArrayList<Arena>();
	public static Map<Integer, List<Arena>> currentArenas=new HashMap<>();
	
	public static World world = Bukkit.getWorld("world");
	
	public ArenaManager() {
		Arena green_planet = new Arena(1, "Very Green Planet", 
				Utils.createItem(Material.BARREL, 1, "&eVery Green Planet"), 
				new Location(world, -68.5, 123, -129.5, -165, 10), 
				new Location(world, -17, 200, -97), 
				new Location(world, -80, 100, -160), 
				new ArrayList<Location>(PathManagement.VERY_GREEN_PLANET));
		
		Arena lava_land = new Arena(2, "Lava Land", 
				Utils.createItem(Material.LAVA_BUCKET, 1, "&eLava Land"), 
				new Location(world, -62.5, 123, -204.5, -165, 10), 
				new Location(world, -79, 200, -239), 
				new Location(world, -16, 100, -176), 
				new ArrayList<Location>(PathManagement.LAVA_LAND));
		
		
		arena.add(green_planet);
		arena.add(lava_land);
		System.out.println(arena.size());
		
	}
	
	public static void callNewArena(List<Player> players, Arena arena, int id) {
		Bukkit.getPluginManager().callEvent(new ArenaPreChargeEvent(arena, players, id));
	}
	
	public static Arena FindPlace(Play play, int id, int act) {
		List<Arena> arenas = currentArenas.get(id);
		Arena arena = null;
		if (arenas.isEmpty()) {
			arena = createArena(id);
			arena.setAct(act);
			arena.players=new ArrayList<>(play.getPlayers());
			arenas.add(arena);
			return arena;
		}else {
			Arena available = getArena(id);
			available.setAct(act);
			available.players=new ArrayList<>(play.getPlayers());
			arenas.add(available);
			return available;
		}
	}
	private static Arena getArena(int id) {

		System.out.println("Search an arena that already existed...");
		Arena available = null;
		List<Arena> current=currentArenas.get(id);
		for (Arena arenas : current) {
			if (arenas.isBusy && arenas.bound.getMax() == null)continue;
			available = arenas;
			break;
		}
		if (available==null)
			available=createArena(id);
		if (!available.isBusy)
			available.isBusy = true;
		System.out.println("(ARENA FOUND)");
		return available;
	}	
	private static Arena createArena(int id) {
		
		System.out.println("Creating a new arena!");
		int size = (16*5) * ((currentArenas.get(id).size()>0)?(currentArenas.get(id).size()+1):1);
		Arena target = new Arena(id);
		for (Arena arenas : arena) {
			if (target.id == arenas.id) {
				arenas.createCopy(size);
				target.health = arenas.health;
				target.tp = arenas.tp.clone().add(size, 0,0);
				target.bound.setMin(arenas.bound.getMin().clone().add(size,0,0));
				target.bound.setMax(arenas.bound.getMax().clone().add(size,0,0));
				arenas.paths.forEach(location->target.paths.add(location.clone().add(size,0,0)));
				target.isBusy = true;
				break;
			}
		}
		System.out.println("Finishing creating a new arena !");
		return target;
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
		arena.players.forEach(players-> {
		Utils.sendMessages(players, "&aYou won ! &8(Teleportation in 3s)");
		players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1.5f);
		});
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player players : arena.players) {
					Data data = Utils.getData(players);
					resetPlayerToSpawn(players);
					data.setArena(null);
					new RewardsMenu(arena).set(players);
				}
				arena.reset();
			}
		}.runTaskLater(instance, 3*20);
		
	}
	
	public static Arena getArenaByItem(ItemStack item) {
		Arena arena = null;
		for (Arena arenas : ArenaManager.arena) {
			if (arenas.itemStack.isSimilar(item)) {
				arena = arenas;
				break;
			}
		}
		return arena;
	}
	public static Arena getArenaById(int id) {
		Arena arena = null;
		for (Arena arenas : ArenaManager.arena) {
			if (id == arenas.id) {
				arena = arenas;
				break;
			}
		}
		return arena;
	}
	
	public static class PathManagement{
		
		public static final List<Location> VERY_GREEN_PLANET = 
				Arrays.asList(new Location(world, -65.5, 122, -147.5), new Location(world, -65.5, 122, -133.5),
						new Location(world, -57.5, 122, -133.5), new Location(world, -58.5, 122, -127.5),
						new Location(world, -69.5, 122, -127.5), new Location(world, -69.5, 122, -120.5),
						new Location(world, -63.5, 121, -120.5), new Location(world, -63.5, 122, -113.5),
						new Location(world, -70.5, 122, -113.5), new Location(world, -70.5, 122, -110.5),
						new Location(world, -65.5, 122, -104.5), new Location(world, -62.5, 122, -104.5));
		
		public static final List<Location> LAVA_LAND = 
				Arrays.asList(new Location(world, -65.5, 122, -227.5), new Location(world, -65.5, 122, -213.5),
						new Location(world, -57.5, 122, -213.5), new Location(world, -57.5, 122, -207.5),
						new Location(world, -69.5, 122, -207.5), new Location(world, -69.5, 122, -200.5),
						new Location(world, -63.5, 121, -200.5), new Location(world, -63.5, 122, -193.5),
						new Location(world, -69.5, 122, -193.5), new Location(world, -69.5, 122, -184.5),
						new Location(world, -44.5, 122, -184.5), new Location(world, -44.5, 121, -201.5));
		
	}
}


