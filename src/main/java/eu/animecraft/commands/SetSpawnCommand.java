package eu.animecraft.commands;

import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Utils;

public class SetSpawnCommand extends ISCommands{

	public SetSpawnCommand(AnimeCraft main) {
		super(main, "setspawn", "setspawn.use");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player sender, String[] args) {
		Utils.sendMessages(sender, "New spawn location updated !");
		main.getConfig().set("spawn", sender.getLocation());
		main.saveConfig();
	}

}
