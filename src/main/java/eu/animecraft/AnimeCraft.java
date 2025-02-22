package eu.animecraft;
import java.lang.reflect.Field;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.ConfigManager;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.Utils;
import eu.animecraft.group.GroupManager;
import eu.animecraft.listerners.Listeners;
import eu.animecraft.listerners.TowerListener;
import eu.animecraft.tower.TowerManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.Item;
import net.minecraft.server.v1_16_R3.Items;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bukkit.scheduler.BukkitRunnable;

public class AnimeCraft extends JavaPlugin {
    public static AnimeCraft instance;
    private TowerManager towerManager;
    private DataManager dataManager;
    private GroupManager groupManager;
    public ConfigManager groups;

    static String url = "mongodb+srv://bouchardphilippe08:HhQ9zRWCEGpx2k0V@squidcraft.ujl1cfx.mongodb.net/?retryWrites=true&w=majority";
    static MongoDatabase mongoDatabase = MongoClients.create(url).getDatabase("AnimeCraft");

    //Collections
    public static MongoCollection<Document> profiles = mongoDatabase.getCollection("profiles");

    public void onEnable() {
        instance = this;
        this.groups = new ConfigManager(this, "", "", "groups.yml");
        this.towerManager = new TowerManager();
        this.groupManager = new GroupManager(this);
        this.dataManager = new DataManager();
        
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new TowerListener(), this);

        try {
            Field maxStackSizeField = Item.class.getDeclaredField("maxStackSize");
            maxStackSizeField.setAccessible(true);
            maxStackSizeField.set(Items.PLAYER_HEAD, 1);
            maxStackSizeField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()){
                    Data data = Utils.getData(player);
                    if (data == null)continue;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText(Utils.color("&9&lGems: &b&l"+data.gems+" &f&l| &6&lGold: &e&l"+data.gold)));
                }
            }
        }.runTaskTimer(this, 0, 5);

    }

    public TowerManager getTowerManager() {
        return this.towerManager;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }

    public GroupManager getGroupManager() {
        return this.groupManager;
    }
}
