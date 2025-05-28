package eu.animecraft.listerners.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.arena.Arena;
import eu.animecraft.arena.ArenaManager;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Play;

public class PlayMenu extends Menu{

	public Play play;
	Arena arena;
	public PlayMenu(Play play) {
		this.play = play;
		arena = ArenaManager.getArenaById(play.getMode());
	}
	
	@Override
	public String name() {
		return (arena == null)?"Please Select":arena.rawName+" Act - " + (this.play.getActMode()+1);
	}

	@Override
	public int size() {
		return 54;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		String name = e.getCurrentItem().getItemMeta().getDisplayName();
		Arena arena = ArenaManager.getArenaByItem(e.getCurrentItem());
		
		if (arena != null) {
			play.setMode(arena.id, name);
			this.arena = arena;
			System.out.println("Map Selection is now " + arena.id);
		}
		switch (e.getCurrentItem().getType()) {
		case BEACON:
			this.play.setActMode(Integer.parseInt(name.replace("Act ", ""))-1);
			System.out.println(this.play.getMode());
			System.out.println(this.play.getActMode());
			break;
		case GREEN_TERRACOTTA:
			if (this.play.getMode()==-1)return;
			getPlayer().closeInventory();
			this.play.current=0;
			break;
		case BARRIER:
			getPlayer().closeInventory();
			DataManager.changeInventory(getPlayer(), 0);
			play.getPlayers().forEach(players->players.teleport(TeleportationMenu.play));
			play.setToWaiting();
			break;
		default:
			break;
		}
		if (e.getCurrentItem().getType()!=Material.GREEN_TERRACOTTA&&e.getCurrentItem().getType()!=Material.BARRIER)
			set(getPlayer());
	}

	@Override
	public void setMenuItems() {
		int[] slots = {0,1,2,9,11,18,20,27,29,36,38,45,47};
		int[] slotsPane = {3,4,5,6,7,8,12,17,21,26,30,35,39,44,48,49,50,51,52,53};
		
		int[] actPos= {10, 19, 28, 37, 46};
		int[] subActPos= {13,14,15,16,22,23,24,25};
		
		for (int i = 0;i<6;i++) {
			int act=subActPos[i];
			getInventory().setItem(act, Utils.createItem(Material.BEACON, 1, "Act " + (i+1)));

		}
		
		int[] line= {31,32,33,34};
		for (int i : line) {
			getInventory().setItem(i, Utils.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, " "));
		}
		
		for (int i : slots) {
			getInventory().setItem(i, Utils.createItem(Material.LIME_STAINED_GLASS_PANE, 1, " "));
		}
		for (int i : slotsPane) {
			getInventory().setItem(i, Utils.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, " "));
		}
		int i1 = 0;
		for (Arena arena : ArenaManager.arena) {
			getInventory().setItem(actPos[i1], arena.itemStack.clone());
			i1++;
		}

		getInventory().setItem(40, Utils.createItem(Material.CRIMSON_NYLIUM, 1, "&cRaid - Act " + play.getMode()));
		getInventory().setItem(41, Utils.createItem(Material.GOLDEN_AXE, 1, "&aChallenge"));
		getInventory().setItem(42, Utils.createItem(Material.DARK_OAK_SAPLING, 1, "&aInfinite"));
		getInventory().setItem(43, Utils.createItem(Material.GOLD_NUGGET, 1, "&e&lGOLD RUSH", "&7Get a maximum of gold", "In this mode, everybody wins", "&7The same amount of gold !"));
		
		getInventory().setItem(46, Utils.createItem(Material.BARRIER, 1, "&c&lLeave"));
		getInventory().setItem(47, Utils.createItem(Material.GREEN_TERRACOTTA, 1, "&a&lConfirm"));
	}

	
	
}
