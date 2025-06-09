package eu.animecraft.menu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.data.components.Menu;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Playroom;

public class PlayroomMenu extends Menu{

	Playroom selection = null;
	
	@Override
	public String name() {
		return selection==null ? "Play rooms Menu":"Current Selected: "+selection.getId();
	}

	@Override
	public int size() {
		return 27;
	}

	@Override
	public void HandleMenu(InventoryClickEvent e) {
		
		switch (e.getCurrentItem().getType()) {
		case STONE:
			String id = e.getCurrentItem().getItemMeta().getDisplayName();
			Playroom playroom = instance.getPlayManager().getRoomById(id);
			if (playroom!=null) 
				selection = playroom;
			
			break;
			
		case GREEN_DYE: case RED_DYE:
			if (selection!=null) {
				boolean active = !selection.isActive();
				selection.setActive(active);
			}
			break;
		case COMPASS:
			selection=null;
			break;
		case BARRIER:
			instance.playrooms.set("playrooms."+selection.getId(), null);
			instance.getPlayManager().rooms.remove(selection);
			selection=null;
			break;

		case ARMOR_STAND:
			
			Location l=getPlayer().getLocation();
			instance.playrooms.set("playrooms."+selection.getId()+".enter", (l.getBlockX()+.5)+" "+l.getBlockY()+" "+(l.getBlockZ()+.5)+" "+l.getYaw()+" "+l.getPitch());
			selection.setEnterLocation(l);
			break;
		case FISHING_ROD:
			
			l=getPlayer().getLocation();
			instance.playrooms.set("playrooms."+selection.getId()+".wait", (l.getBlockX()+.5)+" "+l.getBlockY()+" "+(l.getBlockZ()+.5)+" "+l.getYaw()+" "+l.getPitch());
			selection.setWaitLocation(l);
			break;
			
		case ALLIUM:
			String newRoomID = instance.getPlayManager().createNewRoom();
			instance.playrooms.set("playrooms."+newRoomID+".enter", new String("0 0 0 0 0"));
			instance.playrooms.set("playrooms."+newRoomID+".wait", new String("0 0 0 0 0"));
			instance.playrooms.set("playrooms."+newRoomID+".active", false);
			instance.getPlayManager().rooms.add(new Playroom(newRoomID+"", false, new Location(Utils.overworld, 0, 0, 0), new Location(Utils.overworld, 0, 0, 0)));
			break;
		default:
			break;
		}
		set(getPlayer());
	}

	@Override
	public void setMenuItems() {
		
		if (selection==null) {
			for (Playroom rooms:instance.getPlayManager().rooms)
				super.add(Utils.createItem(Material.STONE, 1, rooms.getId()));
			super.add(26, Utils.createItem(Material.ALLIUM, 1, "Create new room"));
			
		}else {	
			super.add(Utils.createItem((selection.isActive()?Material.GREEN_DYE:Material.RED_DYE), 1, "&fActivated: "+(selection.isActive()?"&a&lON":"&c&lOFF"), "&7Click here to change the status","of the room !"));
			super.add(Utils.createItem(Material.ARMOR_STAND, 1, "&fChange the joining location !", "&7Change the location where,","&7the player needs to be to start playing!"));
			super.add(Utils.createItem(Material.FISHING_ROD, 1, "&fChange the waiting location !", "&7Change the location where,","&7the player is waiting !"));
			super.add(Utils.createItem(Material.BARRIER, 1, "&cDelete this room!"));
			super.add(Utils.createItem(Material.COMPASS, 1, "&cReturn to the rooms list!"));
		}
		
	}
	
}
