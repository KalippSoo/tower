package eu.animecraft.automaticmessages;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.Utils;

public class AutomaticMessages {
	
	public static void auto(){
		new BukkitRunnable() {
			String[] string = new String[] {
					"&e&lYou can evolve your units with gold ! &7(Not all units can be evolve)",
					"&eYou can gain gold by selling units or playing the mode &6&lMarée d'or",
					"&e&lEvery hours the banner's reset !",
					"&e&lYou can add stats to your unit with rerolls !",
			};
			
			@Override
			public void run() {
				String message = string[ThreadLocalRandom.current().nextInt(string.length)];
				Bukkit.getOnlinePlayers().forEach(players -> Utils.sendMessages(players, message));
				
			}
		}.runTaskTimer(AnimeCraft.instance, 0, (300*20));
	}
}