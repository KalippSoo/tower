package eu.animecraft.data.components;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.Packet;

public class NMSClass {

	public static Class<?> getNMSClass(String name){
		try {
			return Class.forName("net.minecraft.server."+
					Bukkit.getServer().getClass().getPackageName().split("\\.")[3]+"."+name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendPacket(Player player, Packet<?> packet) {
		
		try {
			Object handle = player.getClass().getMethod("getHandle");
			Object connection = handle.getClass().getField("playerConnection").get(handle);
			connection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(connection, packet);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
