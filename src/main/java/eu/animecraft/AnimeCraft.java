package eu.animecraft;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ConnectionPoolSettings;

import eu.animecraft.arena.ArenaManager;
import eu.animecraft.automaticmessages.AutomaticMessages;
import eu.animecraft.commands.DiscordCommand;
import eu.animecraft.data.Data;
import eu.animecraft.data.DataManager;
import eu.animecraft.data.components.ConfigManager;
import eu.animecraft.data.components.Utils;
import eu.animecraft.evolution.EvolutionManager;
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
    private EvolutionManager evolutionManager;
    private PlayManager playManager;
    public ConfigManager groups;
    public ConfigManager playrooms;
    public static int mode = 0;

    static MongoDatabase mongoDatabase;

    //Collections
    public static MongoCollection<Document> profiles;
    
    public void onEnable() {
        instance = this;
        
        this.mongo();
        setBaseOptions();
        this.saveDefaultConfig();
        
        //YML CONFIGURATION
        this.groups = new ConfigManager(this, "", "", "groups.yml");
        this.playrooms = new ConfigManager(this, "", "", "playrooms.yml");
        
        //EVENT
        this.towerManager = new TowerManager();
        this.groupManager = new GroupManager(this);
        this.dataManager = new DataManager();
        this.arenaManager = new ArenaManager();
        this.playManager = new PlayManager();
        this.evolutionManager = new EvolutionManager();
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
        
        commands();
	}

	private void commands() {
		new DiscordCommand(this);
	}

	private void setBaseOptions() {
		
		try {
			Class.forName("com.mongodb.client.model.UpdateOptions");
			Class.forName("com.mongodb.client.model.UpdateOneModel");
			Class.forName("com.mongodb.client.model.InsertOneOptions");
			Class.forName("com.mongodb.client.model.BulkWriteOptions");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Bukkit.getOnlinePlayers().forEach(players->players.kickPlayer(Utils.color("&cThe server needs a restart!\nSorry for the bother!")));
			Bukkit.shutdown();
		}
		
    	Bukkit.getWorld("world").setPVP(false);
    	Bukkit.getWorld("world").setDifficulty(Difficulty.NORMAL);
        mode = this.getConfig().getInt("mode");
        
        // ICI
		AutomaticMessages.auto();
	}
	
	public void mongo() {
		 String connectionString = "mongodb+srv://bouchardphilippe08:HhQ9zRWCEGpx2k0V@squidcraft.ujl1cfx.mongodb.net/?retryWrites=true&w=majority&appName=SquidCraft";
	        ServerApi serverApi = ServerApi.builder()
	                .version(ServerApiVersion.V1)
	                .build();
	        MongoClientSettings settings = MongoClientSettings.builder()
	                .applyConnectionString(new ConnectionString(connectionString))
	                .serverApi(serverApi)
	                .applyToSslSettings(builder->
	                	builder.enabled(true))
	                .retryWrites(true)
	                .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) ->{
	                	builder.maxSize(100)
	                	.minSize(5)
	                	.maxConnectionLifeTime(30, TimeUnit.MINUTES)
	                	.maxConnectionIdleTime(1000, TimeUnit.MILLISECONDS);
	                })
	                .applyToSocketSettings(builder ->{
	                	builder.connectTimeout(2000, TimeUnit.MILLISECONDS);
	                })
	                .build();
	        // Create a new client and connect to the server
	        mongoDatabase = MongoClients.create(settings).getDatabase("AnimeCraft");
	        profiles = mongoDatabase.getCollection("profiles");
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

	public EvolutionManager getEvolutionManager() {
		return evolutionManager;
	}
	
}
