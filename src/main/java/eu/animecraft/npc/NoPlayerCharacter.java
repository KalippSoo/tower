package eu.animecraft.npc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.scoreboard.CraftScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.NMSClass;
import eu.animecraft.npc.hologram.Hologram;
import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_16_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import net.minecraft.server.v1_16_R3.ScoreboardTeam;
import net.minecraft.server.v1_16_R3.ScoreboardTeamBase.EnumNameTagVisibility;

public class NoPlayerCharacter {
	
	private List<Player> loaded = new ArrayList<>();
	private EntityPlayer npc;
	private World world;
	
	private boolean canSeePlayer = true;
	public Location location;
	private Hologram hologram;
	public double height=1.85;
	
	GameProfile p;
	AnimeCraft plugin = AnimeCraft.instance;
	
	public NoPlayerCharacter(World world, String name, boolean canSee, List<String> lines, double x, double y, double z,
			float yaw, float pitch, String signature, String value) {
		
		MinecraftServer s = ((CraftServer)Bukkit.getServer()).getHandle().getServer();
		
		this.world = world;
		p = new GameProfile(UUID.randomUUID(), name);
		
		p.getProperties().put("texture", new Property("textures", value, signature));
		npc = new EntityPlayer(s, ((CraftWorld)world).getHandle(), p, new PlayerInteractManager(((CraftWorld)world).getHandle()));
		
		npc.setLocation(x, y, z, yaw, pitch);
		location = new Location(world, x, y, z);
		this.canSeePlayer = canSee;
		if (lines == null)return;
		
		/*
		 * Reverse order of the list !
		 */
		// Collections.reverse(lines);
		hologram = new Hologram(new Location(npc.getBukkitEntity().getWorld(), x, y, z).add(0, height, 0), false, lines, NPC.distanceViewNPC);
		hologram.create();
	}
	
	public void add(Player player) {
		
		if (player.getWorld() != world)return;
		
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		
		ScoreboardTeam team = new ScoreboardTeam(((CraftScoreboard)Bukkit.getScoreboardManager().getMainScoreboard()).getHandle(), npc.getName());
		team.setNameTagVisibility(EnumNameTagVisibility.NEVER);
		
		if (!NPC.npcName.contains(npc.getName())) {
			NPC.npcName.add(npc.getName());
		}
		
		connection.sendPacket(new PacketPlayOutScoreboardTeam(team, 0));
		connection.sendPacket(new PacketPlayOutScoreboardTeam(team, NPC.npcName, 3));
		connection.sendPacket(new PacketPlayOutScoreboardTeam(team, 2));
		
		byte b = (byte) (0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
		npc.getDataWatcher().set(DataWatcherRegistry.a.a(16), b);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, npc));
				
			}
		}.runTaskLaterAsynchronously(plugin, 20);
		
		NMSClass.sendPacket(player, new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));
	}
	
	public void remove(Player player) {
		if (player.getWorld() != world)return;
		if (hologram != null) {
			hologram.removeHologram();
		}
		NMSClass.sendPacket(player, new PacketPlayOutEntityDestroy(npc.getId()));
	}
	
	public EntityPlayer getNpc() {
		return npc;
	}

	public Hologram getHologram() {
		return this.hologram;
	}
	
	public List<Player> getLoaded() {
		return loaded;
	}

	public boolean canSeePlayer() {
		return canSeePlayer;
	}

	public void setCanSeePlayer(boolean canSeePlayer) {
		this.canSeePlayer = canSeePlayer;
	}

	public void reloadHologramAndNoPlayerCharacter() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			
			if (loaded.contains(players)) {
				loaded.remove(players);
			}
			if (hologram.getIsActive().containsKey(players)) {
				hologram.setPlayer(players);
				hologram.removeHologram();
				hologram.getIsActive().put(players, false);
				hologram.setPlayer(null);
			}
			
		}
		
	}
	
}