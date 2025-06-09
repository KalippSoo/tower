package eu.animecraft.menu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;

public class TeleportationMenu extends Menu{

	public static Location play = new Location(Utils.overworld, -40.5, 104, -0.5, 90f, 0f);
	public static Location summon = new Location(Utils.overworld, 0.5, 104, -36.5, 180f, -15f);
	
	@Override
	public String name() {
		return "Choose where to go";
	}

	@Override
	public int size() {
		return 9;
	}
	
	@Override
	public void HandleMenu(InventoryClickEvent e) {
		Location location = null;
		switch (e.getCurrentItem().getType()) {
		case DIAMOND_SWORD:
			location = play;
			break;
		case ENDER_EYE:
			location = summon;
			break;
		default:
			break;
		}
		if (location != null) {
			getPlayer().teleport(location);
			getPlayer().playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1.5f);
			getPlayer().closeInventory();
		}
	}

	@Override
	public void setMenuItems() {
		super.getInventory().setItem(2, Utils.createItem(Material.DIAMOND_SWORD, 1, "&aPlay", "&e&lClick to teleport"));
		super.getInventory().setItem(3, Utils.createItem(Material.ENDER_EYE, 1, "&aSummon", "&e&lClick to teleport"));
		super.getInventory().setItem(4, Utils.createItem(Material.PILLAGER_SPAWN_EGG, 1, "&cRaids", "&e&lCOMING SOON"));
	}

}
