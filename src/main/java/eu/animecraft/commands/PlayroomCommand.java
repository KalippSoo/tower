package eu.animecraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Utils;
import eu.animecraft.play.Playroom;

public class PlayroomCommand extends ISCommands {

	List<String> lines = new ArrayList<String>();
	
	public PlayroomCommand(AnimeCraft main) {
		super(main, "playroom", null);
		lines.add("/playroom create <id>");
		lines.add("/playroom remove <id>");	
		lines.add("/playroom enter <id>");
		lines.add("/playroom waiting <id>");
		
		read(main);
	}

	@Override
	public void execute(Player sender, String[] args) {
		
		if (args.length == 0||args.length < 2) {
			String input = args[0];
			
		}
		
	}
	
	public void read(AnimeCraft main) {
		FileConfiguration config = main.playrooms.getConfig();
		if (main.playrooms.isEmpty("playrooms"))return;
		for (String ids : config.getConfigurationSection("playrooms").getKeys(false)) {
			String[]
			enter = config.getString(ids+".enter").split(" "),
			wait = config.getString(ids+".wait").split(" ");
			
			Location enterLocation = new Location(Utils.world, 
					Double.parseDouble(enter[0]),
					Double.parseDouble(enter[1]),
					Double.parseDouble(enter[2]),
					Float.parseFloat(enter[3]),
					Float.parseFloat(enter[4]));
			Location waitLocation = new Location(Utils.world, 
					Double.parseDouble(wait[0]),
					Double.parseDouble(wait[1]),
					Double.parseDouble(wait[2]),
					Float.parseFloat(wait[3]),
					Float.parseFloat(wait[4]));
			main.getPlayManager().rooms.add(new Playroom(ids, enterLocation, waitLocation));
		}
		
	}

}
