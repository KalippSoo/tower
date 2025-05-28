package eu.animecraft.commands;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Utils;
import org.bukkit.entity.Player;

public class ManagerCommand extends ISCommands {

    String commandSyntax = "/aa ";

    public ManagerCommand(AnimeCraft main) {
        super(main, "aa", "aa.admin");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length >= 0 || args.length <2){
            Utils.sendMessages(sender, "&fVoici la commande &c" + commandSyntax);
            return;
        }
        if (args.length == 2){
            
        	String input = args[0];
        	
        	switch (input) {
			case "give":
				
				break;
			case "remove":
				
				break;
			case "clear":
				
				break;
			default:
				Utils.sendMessages(sender, "&cUnknow syntax, &f" + commandSyntax);
				break;
			}
        }
    }
}
