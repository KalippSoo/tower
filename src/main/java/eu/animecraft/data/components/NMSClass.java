package eu.animecraft.data.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftParticle;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.PacketPlayOutUpdateAttributes;
import net.minecraft.server.v1_16_R3.ParticleParam;

public class NMSClass {

	public static Class<?> getNMSClass(String name){
		try {
			return Class.forName("net.minecraft.server."+
					Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]+"."+name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendPacket(Player player, Object packet) {
		
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object connection = handle.getClass().getField("playerConnection").get(handle);
			connection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(connection, packet);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void sendTabListPacket(Player player, String h, String f) {
//		
//		try {
//
//			PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
//			
//			IChatBaseComponent JSONheader = .a("{\"text\": \"" + h + "\"}");
//	        IChatBaseComponent JSONfooter = ChatComponentUtils.a("{\"text\": \"" + f + "\"}");
//	        
//	        
//	        
//	        sendPacket(player, packet);
//		} catch (IllegalArgumentException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void sendParticle(Player player, org.bukkit.Particle particle, boolean hard, Location l, float offsetX, float offsetY, float offsetZ, float speed, int count) {
		
		try {
			Constructor<?> packetConstructor = getNMSClass("PacketPlayOutWorldParticles").getConstructor(ParticleParam.class, boolean.class, double.class, double.class,
					double.class, float.class, float.class, float.class, float.class, int.class);
			
			Object packet = packetConstructor.newInstance(CraftParticle.toNMS(particle), hard, l.getX(), l.getY(), l.getZ(), offsetX, offsetY, offsetZ, speed, count);
			sendPacket(player, packet);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
		
	}
	
}







