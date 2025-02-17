package eu.animecraft.commands;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.components.ISCommands;
import eu.animecraft.data.components.Utils;
import org.bukkit.entity.Player;

public class ManagerCommand extends ISCommands {

    String commandSyntax = "/aa <give/remove/clear> <name>";

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
            
        }
    }
}
