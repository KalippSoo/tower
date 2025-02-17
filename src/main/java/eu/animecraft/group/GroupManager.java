package eu.animecraft.group;

import eu.animecraft.AnimeCraft;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import eu.animecraft.data.components.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class GroupManager {
    public List<Group> availableGroups = new ArrayList<>();
    public List<Team> teams = new ArrayList<>();
    public Scoreboard scoreboardManager;
    public char[] alpha = new char[]{'1', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    AnimeCraft main;
    private Scoreboard scoreboard;

    public GroupManager(AnimeCraft main) {
        this.main = main;
        this.loadGroups();
    }

    public List<String> getPermissions(Group group) {
        return group.getPerms();
    }

    public Group getGroup(UUID uuid) {
        return this.main.getDataManager().getPlayerData().get(uuid).getGroup();
    }

    public Group getGroup(String string) {
        Group group = null;
        Iterator<?> var4 = this.availableGroups.iterator();

        while(var4.hasNext()) {
            Group groups = (Group)var4.next();
            if (groups.getGroup().toLowerCase().equals(string.toLowerCase())) {
                group = groups;
                break;
            }
        }

        return group;
    }

    public Team getTeam(Player player) {
        Team team = null;
        Iterator<?> var4 = this.teams.iterator();

        while(var4.hasNext()) {
            Team teams = (Team)var4.next();
            if (teams.getName().toLowerCase().substring(2).equals(this.getGroup(player.getUniqueId()).getGroup().toLowerCase())) {
                team = teams;
                break;
            }
        }

        return team;
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void loadGroups() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        if (this.main.groups.getConfig().getConfigurationSection("groups").getKeys(false).size() != 0) {
            int count = 0;

            for(Iterator<?> var3 = this.main.groups.getConfig().getConfigurationSection("groups").getKeys(false).iterator(); var3.hasNext(); ++count) {
                String groupName = (String)var3.next();
                String prefix = this.main.groups.getConfig().getString("groups." + groupName + ".prefix");
                String suffix = this.main.groups.getConfig().getString("groups." + groupName + ".suffix");
                List<String> perms = this.main.groups.getConfig().getStringList("groups." + groupName + ".permissions");
                boolean staff = this.main.groups.getConfig().getBoolean("groups." + groupName + ".staff");
                Group group = new Group(groupName, prefix, suffix, staff, perms);
                this.availableGroups.add(group);
                Team team = this.scoreboard.registerNewTeam(this.alpha[count] + "_" + group.getGroup().trim());
                team.setPrefix(Utils.color(prefix) + " ");
                team.setSuffix(" " + Utils.color(suffix));
                team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
                team.setOption(Option.COLLISION_RULE, OptionStatus.NEVER);
                this.teams.add(team);
            }
        }

    }

    public List<Group> getAvailableGroups() {
        return this.availableGroups;
    }
}
