package eu.animecraft.data.components;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private JavaPlugin main;
    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private String name;
    public String type;

    public ConfigManager(JavaPlugin main, String path, String name, String type) {
        this.main = main;
        this.name = name;
        this.type = type;
        this.saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.main.getDataFolder(), this.name + this.type);
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.main.getResource(this.name + this.type);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }

    }

    public boolean isEmpty(String string) {
    	if (this.dataConfig.getConfigurationSection(string) != null && this.dataConfig.getConfigurationSection(string).getKeys(false).size() > 0) return false;
    	return true;
    }
    
    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            this.reloadConfig();
        }

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig != null && this.configFile != null) {
            try {
                this.getConfig().save(this.configFile);
            } catch (IOException var2) {
                this.main.getLogger().log(Level.SEVERE, "La sauvegarde du ficher de config \" " + this.configFile + "\" a échoué !" + var2);
            }

        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.main.getDataFolder(), this.name + this.type);
        }

        if (!this.configFile.exists()) {
            this.main.saveResource(this.name + this.type, false);
        }

    }

    public String getName() {
        return this.name + this.type;
    }

    public File getFile() {
        return this.configFile;
    }

    public static void createFolder(JavaPlugin main, String name) {
        File folder = new File(main.getDataFolder(), name);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }
}
