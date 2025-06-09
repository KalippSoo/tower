package eu.animecraft.listeners;

import java.util.Collections;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.data.components.EventListener;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.data.components.Utils;
import eu.animecraft.menu.BannerMenu;
import eu.animecraft.npc.NPC;
import eu.animecraft.npc.NPCCommand;
import eu.animecraft.npc.NoPlayerCharacter;
import eu.animecraft.npc.event.PlayerAddLineToNPCEvent;
import eu.animecraft.npc.event.PlayerInteractAtNPCEvent;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntity.PacketPlayOutEntityLook;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.PlayerConnection;

public class NPCListener extends EventListener{

	public NPCListener() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				NPC.distanceNPC();
				instance.getHologramManager().check();
				
			}
		}.runTaskTimer(instance, 0, 20);
	}
	
	FileConfiguration npc_config = instance.npc_config.getConfig();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		if (NPC.getNpcs().isEmpty() || NPC.getNpcs().size() == 0)return;
		
		for (String ids : NPC.getNpcs().keySet()) {
			
			double distance = npc_config.getInt("npc."+ids+".info.distance-before-npc-lose-sight");
			
			NoPlayerCharacter npcharacter = NPC.getNpcs().get(ids);
			EntityPlayer npc = npcharacter.getNpc();
			
			Player players = e.getPlayer();
			if (!players.getWorld().equals(npc.getBukkitEntity().getWorld()))return;
			
			PlayerConnection pc = ((CraftPlayer)players).getHandle().playerConnection;
			
			Location location = npc.getBukkitEntity().getLocation();
			
			// Look at their direction
			if (npc.getBukkitEntity().getLocation().distance(players.getLocation()) > distance) {
				
				if (players.getWorld().equals(npc.getBukkitEntity().getWorld())) {
					
					try {

						float yaw = (float) npc_config.getInt("npc." + ids + ".info.lookAt.yaw"),
						pitch = (float) npc_config.getInt("npc." + ids + ".info.lookAt.pitch");
						
						pc.sendPacket(new PacketPlayOutEntityLook(npc.getId(), (byte) ((yaw%360)*256/360), (byte) ((pitch%360)*256/360), false));
						pc.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((yaw%360)*256/360)));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				continue;
			}
			
			if(!npcharacter.canSeePlayer())return;
			
			location.setDirection(players.getLocation().subtract(location).toVector());
			
			float yaw = location.getYaw();
			float pitch = location.getPitch();
			
			if (players.getWorld().equals(npc.getBukkitEntity().getWorld())) {
				
				NMSClass.sendPacket(players, new PacketPlayOutEntityLook(npc.getId(), (byte) ((yaw%360)*256/360), (byte) ((pitch%360)*256/360), false));
				NMSClass.sendPacket(players, new PacketPlayOutEntityHeadRotation(npc, (byte) ((yaw%360)*256/360)));
			}
		
		}
	}
	
	@EventHandler
	public void onPlayerInteractWithNPC(PlayerInteractAtNPCEvent e) {
		Player player = e.getPlayer();
		if (player.isOp()) {
			Utils.sendMessages(player, "&e" + e.getID());
		}
		
		switch (e.getID()) {
		case "0":
			BannerMenu menu = new BannerMenu();
			menu.set(player);
			break;

		default:
			break;
		}
	}
	
	@EventHandler
	public void onPlayerAddLineToNPC(PlayerAddLineToNPCEvent e) {
		Player player = e.getPlayer();
		String line = e.getLine();
		NoPlayerCharacter npc = e.getNpc();
		
		if (npc.getHologram().getLines().size() >= 15) {
			Utils.sendMessages(player, "&cYou can't have more than 15 lines per hologram !");
    		NPCCommand.addLineContainer.remove(player);
    		Utils.sendMessages(player, "&7Done ! you can send back normal messages in the chat !");
			return;
		}
		
		Utils.sendMessages(player, "&f&l'"+line+"&f&l' &8(Added)");
		Collections.reverse(npc.getHologram().getLines());
		if (line.equalsIgnoreCase("space")) {
			npc.getHologram().getLines().add("");
		}else {
			npc.getHologram().getLines().add(line);
		}
		Collections.reverse(npc.getHologram().getLines());
		npc.reloadHologramAndNoPlayerCharacter();
		instance.npc_config.set("npc."+NPC.getIDFromNoPlayerCharacter(npc)+".info.name", npc.getHologram().getLines());
	}
}