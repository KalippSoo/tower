package eu.animecraft;
import java.lang.reflect.Field;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import eu.animecraft.arena.ArenaManager;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.ConfigManager;
import eu.animecraft.data.components.Utils;
import eu.animecraft.group.GroupManager;
import eu.animecraft.listerners.ArenaListener;
import eu.animecraft.listerners.Listeners;
import eu.animecraft.listerners.TowerListener;
import eu.animecraft.play.PlayManager;
import eu.animecraft.tower.TowerManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.Item;
import net.minecraft.server.v1_16_R3.Items;

public class AnimeCraft extends JavaPlugin {
    public static AnimeCraft instance;
    private TowerManager towerManager;
    private DataManager dataManager;
    private GroupManager groupManager;
    private ArenaManager arenaManager;
    private PlayManager playManager;
    public ConfigManager groups;
    public static int mode = 0;

    static String url = "mongodb+srv://bouchardphilippe08:HhQ9zRWCEGpx2k0V@squidcraft.ujl1cfx.mongodb.net/?retryWrites=true&w=majority";
    static MongoDatabase mongoDatabase = MongoClients.create(url).getDatabase("AnimeCraft");

    //Collections
    public static MongoCollection<Document> profiles = mongoDatabase.getCollection("profiles");

    public void onEnable() {
        instance = this;
        
        setBaseOptions();
        this.saveDefaultConfig();
        
        this.groups = new ConfigManager(this, "", "", "groups.yml");
        this.towerManager = new TowerManager();
        this.groupManager = new GroupManager(this);
        this.dataManager = new DataManager();
        this.arenaManager = new ArenaManager();
        this.playManager = new PlayManager();
        
        listeners();

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
                    if (data.isPlaying()) {
                    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                TextComponent.fromLegacyText(Utils.color("&a&lMoney: &b&l"+data.po)));
                    }else {
                    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText(Utils.color("&9&lGems: &b&l"+data.gems+" &f&l| &6&lGold: &e&l"+data.gold+" &f&l| &5&lRerolls: &d"+data.rerolls)));
                    }
                }
            }
        }.runTaskTimer(this, 0, 5);

    }

    private void listeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new TowerListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ArenaListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(playManager, this);
        Bukkit.getServer().getPluginManager().registerEvents(arenaManager, this);
	}

	private void setBaseOptions() {
    	Bukkit.getWorld("world").setPVP(false);
    	Bukkit.getWorld("world").setDifficulty(Difficulty.NORMAL);
        mode = this.getConfig().getInt("mode");
		
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

	public ArenaManager getArenaManager() {
		return arenaManager;
	}

	public PlayManager getPlayManager() {
		return playManager;
	}
}
