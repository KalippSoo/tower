package eu.animecraft.tower;

import static eu.animecraft.data.components.Utils.format;
import static eu.animecraft.data.components.Utils.sendMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import eu.animecraft.MyArmorStand;
import eu.animecraft.arena.Arena;
import eu.animecraft.data.Lvl;
import eu.animecraft.data.StatsReroll;
import eu.animecraft.data.components.SkullCreator;
import eu.animecraft.data.components.Utils;
import eu.animecraft.event.tower.TowerPlaceEvent;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;
import eu.animecraft.tower.tools.Trait;

public class Tower{
	
    private int count;
    private int maxCount;
    private Rarity rarity;
    private String name;
    private Player owner;
    private Trait trait = Trait.none;
    private int id;
    private boolean shiny;
    /*
    Just write manually the damage, cooldown and the range with the leveling system create a new instance.
     */
    public Tower evo = null;
    public DamageType[] damageTypes;
    public int damage, cooldown, range, critChance;
    public double fd, fr;
    public int fc;
    public boolean locked;
    public StatsReroll statsReroll = new StatsReroll(0,0,0);
    public Lvl lvlSystem = new Lvl(this);
    public UUID uuid;
    public MyArmorStand stand;
    
    
    //parameters
    public int currentCooldown = 0;
    public ItemStack towerItem;
    
    //Skin
    public String value;
    public String signature;
    public String sValue;
    public String sSignature;
    
    public Tower(int id, Rarity rarity, String name, Player owner, boolean shiny, int maxCount, UUID uuid, String value, String sValue) {
        this.id = id;
        this.rarity = rarity;
        this.name = name;
        this.owner = owner;
        this.shiny = shiny;
        this.maxCount = maxCount;
        //Classic head
        this.value = value;
        //Shiny head
        this.sValue = sValue;
        updateStats();
        
    }
    
    public Tower(UUID uuid, int damage, int cooldown, int range, boolean shiny, Player owner, int id, Lvl lvlSytem, StatsReroll statsReroll, 
    		Trait trait, Rarity rarity, int maxPlacement, String value, String sValue, String name, boolean locked) {
    	this.uuid = uuid;
    	this.damage = damage;
    	this.cooldown = cooldown;
    	this.range = range;
    	this.shiny = shiny;
    	this.owner = owner;
    	this.id = id;
    	this.lvlSystem = lvlSytem;
    	this.statsReroll = statsReroll;
    	this.rarity = rarity;
    	this.value = value;
    	this.sValue = sValue;
    	this.locked = locked;
    	this.maxCount = maxPlacement;
    	this.name = name;
    	this.setTrait(trait);
    	setShiny(shiny);
    	updateStats();
    	
    }
    
    public Tower clone() {
        Tower tower = new Tower(id, rarity, name, owner, shiny, maxCount, uuid, value, sValue);
        tower.uuid = UUID.randomUUID();
        tower.damage = damage;
        tower.cooldown = cooldown;
        tower.range = range;
        tower.setShiny(shiny);
        tower.owner = owner;
        tower.id = id;
        tower.lvlSystem = lvlSystem;
        tower.statsReroll = statsReroll;
        tower.rarity = rarity;
        tower.value = value;
        tower.sValue = sValue;
        tower.locked = locked;
        tower.maxCount = maxCount;
        tower.name = name;
    	tower.updateStats();
        return tower;

    }
    
    public String displayName() {
        return Utils.color("&7[" + lvlSystem.getCurrentLevel() + "] " + this.rarity.getColor() + this.name);
    }

    public void newStats() {
        statsReroll = new StatsReroll(
        		ThreadLocalRandom.current().nextDouble(-5, 5),
        		ThreadLocalRandom.current().nextDouble(-5, 5),
        		ThreadLocalRandom.current().nextDouble(-5, 5)
        		);
        updateStats();
    }
    
    public void updateStats(){
    	
        double td = 0, tc = 0, tr = 0;

        switch (trait){
        	case none: break;
            case FORCE: td = 15;break;
            case AGILITY: tc = -15;break;
            case VISION: tr = 15;break;
            case SKILLED: td = 10; tc = -10; tr = 10;break;
            case DEADLY: td = 5; tc = -25;break;
            case QUICKLEANER: break;
            case STAR: td = 30; tc = -15; tr = 10;break;
            case PARTICULAR: td = -15; tc = -65;break;
            case UNIQUE: td = 400; tc = -25; tr = 20;break;
        }
        double bd = (lvlSystem.getDamage() + statsReroll.a), bc = (cooldown-statsReroll.b), br = (range + statsReroll.c);
        this.fd = (bd + ((bd*td)/100)) + (shiny ? ((damage*15)/100) : 0);
        this.fc = (int) ((bc + ((bc*tc)/100)) - (shiny ? ((cooldown*10)/100) : 0));
        this.fr = (br + ((br*tr)/100)) + (shiny ? ((range*5)/100) : 0);

        updateItem();
    }
    
    public void setLocked(boolean locked) {
    	this.locked = locked;
    	updateItem();
    }
    
    public void updateItem() {
    	towerItem = generateItemTower();
    }
    
    public ItemStack generateItemTower() {
        
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        SkullCreator.mutateItemMeta(meta, this.value);
        String itemName = !this.shiny ? this.getName() : this.getName() + " &e&l★";
        List<String> lines = new ArrayList<>();
        meta.setDisplayName(Utils.color("&e[" + lvlSystem.getCurrentLevel() + "] " + this.rarity.getColor() + itemName));
        lines.add(Utils.color("&7Damage: &c" + Math.round(fd) +" &f&l/"+ statsReroll.d(0)));
        lines.add(Utils.color("&7Reload: &a" + format(fc / 20.0F) + "s "+" &f&l/"+ statsReroll.d(1)));
        lines.add(Utils.color("&7Range: &e" + format(fr) +" &f&l/"+ statsReroll.d(2)));
        if (trait != null && trait != Trait.none)
        	lines.add(Utils.color("&7Trait: "+trait.getRarity().getColor()+trait.name()));
        lines.add("");
        lines.add(Utils.color("&7Locked: " + (this.locked?"&a&lON":"&f&lOFF")));
        
        lines.add("");
        lines.add(Utils.color(this.getRarity().getName()));
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public void placeStand(Player player, Arena arena, Location loc) {
    	Bukkit.getPluginManager().callEvent(new TowerPlaceEvent(player, arena, this, loc));
    }
    
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean addCount() {
        if (this.count >= this.maxCount) {
            sendMessages(this.owner, "&cYou can't place any more of this tower ("+count+"/"+maxCount+")");
            return false;
        } else {
            ++this.count;
            return true;
        }
    }
    public void clearCount() {
    	this.count = 0;
    }
    
    public void setTrait(Trait trait) {
        this.trait = trait;
        switch (trait) {
		case PARTICULAR:
			this.maxCount=this.maxCount==1?1:2;
			break;
		case UNIQUE:
			this.maxCount=1;
			break;
		default:
			break;
		}
        updateStats();
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Trait getTrait() {
        return this.trait;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isShiny() {
        return this.shiny;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
        if (shiny) {
        	if (sValue != null || sValue.equals("")) {
                this.value = this.sValue;
                this.signature = this.sSignature;
        	}
        }

    }
    
    public String getDocumentCode() {
        return id+
        ","+statsReroll.a+","+statsReroll.b+","+statsReroll.c+","+shiny+","+trait.name()
        +","+lvlSystem.getCurrentLevel()+","+lvlSystem.getCurrentExp()+","+uuid.toString()
        +","+locked;
    }
}
