package eu.animecraft.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Menu;
import eu.squidcraft.Mongo.tools.Utils;

public class NPCCommand extends ISCommands {

	public static Map<Player, NoPlayerCharacter> addLineContainer = new HashMap<Player, NoPlayerCharacter>();
	
	public NPCCommand(AnimeCraft main) {
		super(main, "npc", "npc.*");
	}

	@Override
	public void execute(Player sender, String[] args) {
		
		if (args.length < 2) {
			Utils.sendMessages(sender, "&fCommand: &e/npc <id>");
			return;
		}
		
		String id = args[1];
		NoPlayerCharacter npc = NPC.getNoPlayerCharacter(id);
		
		switch (args[0]) {
		
		/*
		 * npc create <id> [can look at player (BOOLEAN)] [line1,line2]
		 * npc modify <id>
		 * npc remove <id>
		 * npc show/list
		 */
		case "create":
			
			if (npc != null) {
				Utils.sendMessages(sender, "&cThis id is already taken !");
				return;
			}
			
			List<String> lines = new ArrayList<String>();
			lines.add("hi i'm "+id);
			
			main.npc_config.set("npc." + id + ".info.can-look", true);
			main.npc_config.set("npc." + id + ".info.distance-before-npc-lose-sight", 7.5);
			main.npc_config.set("npc." + id + ".info.name", lines);
			main.npc_config.set("npc." + id + ".info.lookAt.yaw", sender.getLocation().getYaw());
			main.npc_config.set("npc." + id + ".info.lookAt.pitch", sender.getLocation().getPitch());
			main.npc_config.set("npc." + id + ".skin.signature", "");
			main.npc_config.set("npc." + id + ".skin.texture", "");
			main.npc_config.set("npc." + id + ".location.x", sender.getLocation().getX());
			main.npc_config.set("npc." + id + ".location.y", sender.getLocation().getY());
			main.npc_config.set("npc." + id + ".location.z", sender.getLocation().getZ());
			main.npc_config.set("npc." + id + ".location.world", sender.getLocation().getWorld().getName());
			
			NPC.addNPCToCurrentServer(id, sender.getLocation(), lines);
			Utils.sendMessages(sender, "&cNo Player Character created id: " + id);
			break;
			
		case "remove":
			main.npc_config.set("npc."+id, null);
			NPC.removeSpecificNPCPacket(id);
			Utils.sendMessages(sender, "&cNo Player Character with id &e"+id+"&c deleted !");
			break;
			
		case "modify":
			
			Menu menu = new Menu() {
				@Override
				public int size() {
					return 36;
				}
				
				@Override
				public void setMenuItems() {
					
					getInventory().addItem(Utils.createItem(Material.ENDER_EYE, 1, "Can See Player: "+(npc.canSeePlayer()?"&a&lON":"&c&lOFF")));
					getInventory().addItem(Utils.createItem(Material.PAPER, 1, "&aAdd line", "&fAllow you to add a line.", "&fYou can use code color by using &e'&'", "", "&e&lClick here to add a line !"));
					getInventory().addItem(Utils.createItem(Material.WRITABLE_BOOK, 1, "&cRemove Line", "&fChance the location of the npc,", "&fAt your current location", "", "&e&lClick here to set the location !"));
					getInventory().addItem(Utils.createItem(Material.GRASS_BLOCK, 1, "&aChange Location", "&fChance the location of the npc,", "&fAt your current location", "", "&e&lClick here to set the location !"));
					
				}
				
				@Override
				public String name() {
					return "Modifying...";
				}
				
				@Override
				public void HandleMenu(InventoryClickEvent e) {
					
					Location loc = getPlayer().getLocation();
					
					switch (e.getCurrentItem().getType()) {
					case ENDER_EYE:
						npc.setCanSeePlayer(!npc.canSeePlayer());
						set(getPlayer());
						break;
					case PAPER:
						getPlayer().closeInventory();
						Utils.sendMessages(sender, "&8Write the sentence in the chat to add the line !");
						Utils.sendMessages(sender, "&8When you're done just type !stop");
						Utils.sendMessages(sender, "&8Type 'space' in the chat to create a space between lines !");
						addLineContainer.put(sender, npc);
						break;
					case GRASS_BLOCK:
						npc.getNpc().setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						npc.getHologram().resetLocation(getPlayer().getLocation().add(0,npc.height,0));
						npc.reloadHologramAndNoPlayerCharacter();
						break;
					case WRITABLE_BOOK:
						Menu menu = new Menu() {

							@Override
							public String name() {
								return "&cClick to remove !";
							}

							@Override
							public int size() {
								return 27;
							}

							@Override
							public void HandleMenu(InventoryClickEvent e) {
								int slot = e.getSlot();
								npc.getHologram().getLines().remove((slot));
								npc.reloadHologramAndNoPlayerCharacter();
								set(sender);
							}

							@Override
							public void setMenuItems() {
								for (String line:npc.getHologram().getLines()) {
									super.add(Utils.createItem(Material.PAPER, 1, line));
								}
							}
							
						};
						menu.set(sender);
						break;
					default:
						break;
					}
				}
			};
			menu.set(sender);
			break;
		case "show": case "list":
			for (String ids : NPC.getNpcs().keySet()) {
				NoPlayerCharacter npcs = NPC.getNpcs().get(ids);
				Utils.sendMessages(sender, "&e"+ids+": &8Location:",
						"&8x: "+npcs.location.getX(),
						"&8y: "+npcs.location.getY(),
						"&8z: "+npcs.location.getZ());
			}
			break;
		default:
			Utils.sendMessages(sender, "&cWrong syntax !");
			break;
		}
	}
}