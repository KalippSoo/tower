package eu.animecraft.data.components;

import eu.animecraft.AnimeCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ISCommands implements CommandExecutor {
    protected AnimeCraft main;
    protected String permission;

    public abstract void execute(Player sender, String[] args);

    public ISCommands(AnimeCraft main, String name, String permission) {
        this.main = main;
        main.getCommand(name).setExecutor(this);
        this.permission = permission;
    }

    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (this.permission != null) {
                if (player.hasPermission(this.permission)) {
                    this.execute(player, args);
                }
            } else {
                this.execute(player, args);
            }
        }

        return false;
    }
}
