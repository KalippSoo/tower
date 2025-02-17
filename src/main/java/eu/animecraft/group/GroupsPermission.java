package eu.animecraft.group;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;

public class GroupsPermission {
    private final Map<UUID, PermissionAttachment> attachments = new HashMap<>();
    private AnimeCraft main;

    public GroupsPermission(AnimeCraft main) {
        this.main = main;
    }

    public void setupPermissions(final Player player) {
        PermissionAttachment attachment = player.addAttachment(this.main);
        attachment.getPermissions().clear();
        this.attachments.put(player.getUniqueId(), attachment);
        (new BukkitRunnable() {
            public void run() {
                GroupsPermission.this.permissionsSetter(player);
                this.cancel();
            }
        }).runTaskTimer(this.main, 0L, 20L);
    }

    private void permissionsSetter(Player player) {
        PermissionAttachment attachment = (PermissionAttachment)this.attachments.get(player.getUniqueId());
        Data data = this.main.getDataManager().getPlayerData().get(player.getUniqueId());

        String perms;
        Iterator<?> var5;
        try {
            var5 = data.getUniquePermissions().iterator();

            while(var5.hasNext()) {
                perms = (String)var5.next();
                String correctPattern = perms.startsWith("-") ? perms.substring(1).trim() : perms.trim();
                attachment.setPermission(correctPattern, true);
            }
        } catch (Exception var7) {
        }

        var5 = data.getGroup().getPerms().iterator();

        while(var5.hasNext()) {
            perms = (String)var5.next();
            attachment.setPermission(perms, true);
        }

    }

    public Map<UUID, PermissionAttachment> getGroupsPermissions() {
        return this.attachments;
    }
}
