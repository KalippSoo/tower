package eu.animecraft.commands;

import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Utils;

public class DiscordCommand extends ISCommands{

	public DiscordCommand(AnimeCraft main) {
		super(main, "discord", null);
	}

	@Override
	public void execute(Player sender, String[] args) {
		Utils.sendMessages(sender, "&f&lhttps://discord.gg/T9Ar79GW2A");
		
	}

}
