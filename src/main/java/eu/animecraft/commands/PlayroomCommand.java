package eu.animecraft.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.menu.PlayroomMenu;

public class PlayroomCommand extends ISCommands {

	List<String> lines = new ArrayList<String>();
	public Map<Player, String> map = new HashMap<Player, String>();
	
	public PlayroomCommand(AnimeCraft main) {
		super(main, "playroom", null);
	}

	@Override
	public void execute(Player sender, String[] args) {
		
		new PlayroomMenu().set(sender);
		
	}

}
