package eu.animecraft.tower;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static eu.animecraft.data.components.Utils.*;
import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import eu.animecraft.data.Lvl;
import eu.animecraft.data.StatsReroll;
import eu.animecraft.data.components.SkullCreator;
import eu.animecraft.data.components.Utils;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;
import eu.animecraft.tower.tools.Trait;
import net.minecraft.server.v1_16_R3.Item;
import net.minecraft.server.v1_16_R3.Items;
import org.bson.Document;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Tower {

    //private CraftTower craftTower;
    private DamageType[] damageTypes;
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
    public int damage, cooldown, range;
    public double fd, fc, fr;
    public StatsReroll statsReroll = new StatsReroll(0,0,0);
    public Lvl lvlSystem = new Lvl(this);
    public UUID uuid;

    //Skin
    public String value;
    public String signature;
    public String sValue;
    public String sSignature;

    public String toString(){
        return "dmg:" + damage + " reload:" + cooldown + " range:" + range + " rarity:" + rarity.name() + " name:" + name + " id:" + id
                + " shiny:"+shiny+" uuid:"+uuid.toString()+" value:"+value;
    }

    public Tower(int id, Rarity rarity, String name, Player owner, boolean shiny, int maxCount, UUID uuid, String value, String signature, String sValue, String sSignature) {
        this.id = id;
        this.rarity = rarity;
        this.name = name;
        this.owner = owner;
        this.shiny = shiny;
        this.maxCount = maxCount;
        //Classic head
        this.value = value;
        this.signature = signature;
        //Shiny head
        this.sValue = sValue;
        this.sSignature = sSignature;
        updateStats();
    }

    public String displayName() {
        return Utils.color("&7[" + lvlSystem.getCurrentLevel() + "] " + this.rarity.getColor() + this.name);
    }

    public void updateStats(){

        double td = 0, tc = 0, tr = 0;

        switch (trait){
            case FORCE: td = 15;break;
            case AGILITY: tc = -15;break;
            case VISION: tr = 15;break;
            case SKILLED: td = 10; tc = -10; tr = 10;break;
            case DEADLY: td = 5; tc = -25;break;
            case STAR: td = 30; tc = -15; tr = 10;break;
            case PARTICULAR: td = -15; tc = -45;break;
            case UNIQUE: td = 400; tc = -25; tr = 20;break;
        }

        double bd = (damage + statsReroll.a), bc = (cooldown + statsReroll.b), br = (range + statsReroll.c);
        this.fd = (bd + ((bd*td)/100)) + (shiny ? ((damage*15)/100) : 0);
        this.fc = (bc + ((bc*tc)/100)) - (shiny ? ((cooldown*10)/100) : 0);
        this.fr = (br + ((br*tr)/100)) + (shiny ? ((range*5)/100) : 0);
    }

    public Tower clone() {
        Tower tower = new Tower(id, rarity, name, owner, shiny, maxCount, uuid, value, signature, sValue, sSignature);
        tower.uuid = UUID.randomUUID();
        tower.damage = damage;
        tower.cooldown = cooldown;
        tower.range = range;
        tower.statsReroll = statsReroll;
        return tower;

    }

    /*
    Banner mode: 1
    Reroll mode: 2
     */

    public ItemStack getItemVersion(int mode) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        if (!(item.getItemMeta() instanceof SkullMeta)) {
            return null;
        } else {
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            SkullCreator.mutateItemMeta(meta, this.value);
            String name = !this.shiny ? this.getName() : this.getName() + " &e&l★";
            List<String> lines = new ArrayList<>();
            if (mode == 1){
                meta.setDisplayName(Utils.color(this.rarity.getColor() + name));

            }else{
                updateStats();
                meta.setDisplayName(Utils.color("&e[" + lvlSystem.getCurrentLevel() + "] " + this.rarity.getColor() + name));
                lines.add(Utils.color("&7Damage: &c" + format.format(fd) + statsReroll.d(0)));
                lines.add(Utils.color("&7Reload: &a" + format.format(fc / 20.0F) + "s"+ statsReroll.d(1)));
                lines.add(Utils.color("&7Range: &e" + format.format(fr) + statsReroll.d(2)));
                if (trait != null && trait != Trait.none)
                    lines.add(Utils.color("&7Trait: " +trait.name()));
            }

            lines.add("");
            lines.add(Utils.color(this.getRarity().getName()));
            meta.setLore(lines);
            item.setItemMeta(meta);
            return item;
        }
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

    public void setTrait(Trait trait) {
        this.trait = trait;
        updateStats();
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
            this.value = this.sValue;
            this.signature = this.sSignature;
        }

    }

    public String getDocumentCode() {
        return id+","+damage+","+cooldown+","+range+","+statsReroll.a+","+statsReroll.b+","+statsReroll.c+","+shiny+","+trait.name()+","+lvlSystem.getCurrentLevel()+","+lvlSystem.getCurrentExp()+","+uuid.toString();
    }
}
