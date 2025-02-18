//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.animecraft.data.components;

import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import eu.animecraft.AnimeCraft;
import eu.animecraft.data.Data;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Utils {
    public static String moneySymbol = "❇";
    public static String creditSymbol = "Ⓒ";
    
    public static DecimalFormat format = new DecimalFormat("###,###.##");

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static Data getData(Player player){
        return AnimeCraft.instance.getDataManager().getPlayerData().get(player.getUniqueId());

    }

    public static List<String> color(List<String> texts) {
        List<String> lines = new ArrayList<>();
        Iterator<?> var3 = texts.iterator();

        while(var3.hasNext()) {
            String text = (String)var3.next();
            lines.add(color(text));
        }

        return lines;
    }

    public static ItemStack changeAmount(ItemStack item, int amount) {
        ItemStack newItem = item.clone();
        newItem.setAmount(amount);
        return newItem;
    }

    public static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static ItemStack l(ItemStack item, Color color) {
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)helm.getItemMeta();
        meta.setColor(color);
        helm.setItemMeta(meta);
        return helm;
    }

    public static String stripColor(String text) {
        return ChatColor.stripColor(text);
    }

    public static ItemStack book(ItemStack itemStack, String... lines) {
        BookMeta meta = (BookMeta)itemStack.getItemMeta();
        meta.setTitle("Fishing Update");
        meta.addPage(lines);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public static int distance2D(double x, double y, double x1, double y2) {
        return (int)calculateDistanceBetweenPoints(x, y, x1, y2);
    }

    public static void sendGlobalMessages(String... texts) {
        Iterator<?> var2 = Bukkit.getOnlinePlayers().iterator();

        while(var2.hasNext()) {
            Player players = (Player)var2.next();
            String[] var6 = texts;
            int var5 = texts.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String line = var6[var4];
                players.sendMessage(color(line));
            }
        }

    }

    public static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for(int i = 0; i < n; ++i) {
            int index = (int)((double)AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public static void sendMessages(Player player, String... texts) {
        String[] var5 = texts;
        int var4 = texts.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String line = var5[var3];
            player.sendMessage(color(line));
        }

    }

    public static void sendMessages(CommandSender sender, String... texts) {
        String[] var5 = texts;
        int var4 = texts.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String line = var5[var3];
            sender.sendMessage(color(line));
        }

    }

    public static void playSoundWorld(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    public static void playSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getEyeLocation(), sound, volume, pitch);
    }

    public static ItemStack createItem(Material mat, int size, String name, String... lores) {
        ItemStack item = new ItemStack(mat, size);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(color(name));
        }

        ArrayList<String> lines = new ArrayList<>();
        String[] var10 = lores;
        int var9 = lores.length;

        for(int var8 = 0; var8 < var9; ++var8) {
            String line = var10[var8];
            lines.add(color(line));
        }

        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material mat, int size, String name, List<String> lores) {
        ItemStack item = new ItemStack(mat, size);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(color(name));
        }

        if (lores != null) {
            ArrayList<String> lines = new ArrayList<>();
            Iterator<?> var8 = lores.iterator();

            while(var8.hasNext()) {
                String line = (String)var8.next();
                lines.add(color(line));
            }

            meta.setLore(lines);
        }

        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material mat, int size, boolean unbreakable, String name, String... lores) {
        ItemStack item = new ItemStack(mat, size);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(color(name));
        }

        ArrayList<String> lines = new ArrayList<>();
        String[] var11 = lores;
        int var10 = lores.length;

        for(int var9 = 0; var9 < var10; ++var9) {
            String line = var11[var9];
            lines.add(color(line));
        }

        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack copy(ItemStack itemStack, String... lores) {
        ItemStack item = itemStack.clone();
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lines = new ArrayList<>();
        String[] var8 = lores;
        int lvl = lores.length;

        for(int var6 = 0; var6 < lvl; ++var6) {
            String line = var8[var6];
            lines.add(color(line));
        }

        Iterator<?> var10 = meta.getEnchants().keySet().iterator();

        while(var10.hasNext()) {
            Enchantment enchantment = (Enchantment)var10.next();
            lvl = (Integer)meta.getEnchants().get(enchantment);
            meta.addEnchant(enchantment, lvl, true);
        }

        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack Enchant(Enchantment enchantment, int lvl, ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(enchantment, lvl, true);
        List<String> list = new ArrayList<>();
        if (meta.hasLore()) {
            Iterator<?> var7 = meta.getLore().iterator();

            while(var7.hasNext()) {
                String lines = (String)var7.next();
                list.add(color(lines));
            }

            if (list != null) {
                meta.setLore(list);
            }
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack Enchant(Enchantment enchantment, int lvl, ItemStack itemStack, boolean b) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(enchantment, lvl, true);
        List<String> list = new ArrayList<>();
        if (b) {
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        if (meta.hasLore()) {
            Iterator<?> var8 = meta.getLore().iterator();

            while(var8.hasNext()) {
                String lines = (String)var8.next();
                list.add(color(lines));
            }

            if (list != null) {
                meta.setLore(list);
            }
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static String getCorrectValue(double value) {
        DecimalFormat format = new DecimalFormat("#.##");
        double realMoney = 0.0;
        String correct = "";
        if (value <= 999.0) {
            return String.valueOf(value);
        } else {
            if (value > 999.0) {
                realMoney = 1000.0;
                correct = "k";
            }

            if (value > 999999.0) {
                realMoney = 1000000.0;
                correct = "m";
            }

            if (value > 9.9999999E7) {
                realMoney = 1.0E9;
                correct = "B";
            }

            return format.format(value / realMoney) + correct;
        }
    }

    public static String DelayFormat(long l) {
        System.out.println(l);
        int seconds = (int)((l - System.currentTimeMillis()) / 1000L);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int weeks = days / 7;
        int months = weeks / 4;
        int years = months / 12;
        seconds -= minutes * 60;
        minutes -= hours * 60;
        hours -= days * 24;
        days -= weeks * 7;
        weeks -= months * 4;
        months -= years * 12;
        StringBuilder sb = new StringBuilder();
        if (years > 0) {
            sb.append(color("&e" + years + " année(s) "));
        }

        if (months > 0) {
            sb.append(color("&e" + months + " mois "));
        }

        if (weeks > 0) {
            sb.append(color("&e" + weeks + " semaine(s) "));
        }

        if (days > 0) {
            sb.append(color("&e" + days + " jour(s) "));
        }

        if (hours > 0) {
            sb.append(color("&e" + hours + " heure(s) "));
        }

        if (minutes > 0) {
            sb.append(color("&e" + minutes + " minute(s) "));
        }

        if (seconds > 0) {
            sb.append(color("&e" + seconds + " seconde(s) "));
        }

        return sb.toString();
    }

    public static String DelayFormat(int l) {
        int seconds = (int)(((long)l - System.currentTimeMillis()) / 1000L);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int weeks = days / 7;
        int months = weeks / 4;
        int years = months / 12;
        seconds -= minutes * 60;
        minutes -= hours * 60;
        hours -= days * 24;
        days -= weeks * 7;
        weeks -= months * 4;
        months -= years * 12;
        StringBuilder sb = new StringBuilder();
        if (years > 0) {
            sb.append(color("&c" + years + ":"));
        }

        if (months > 0) {
            sb.append(color("&c" + months + ":"));
        }

        if (weeks > 0) {
            sb.append(color("&c" + weeks + ":"));
        }

        if (days > 0) {
            sb.append(color("&c" + days + ":"));
        }

        if (hours > 0) {
            sb.append(color("&c" + hours + ":"));
        }

        if (minutes > 0) {
            sb.append(color("&c" + minutes + ":"));
        }

        if (seconds > 0) {
            sb.append(color("&c" + seconds + " seconde(s) "));
        }

        return sb.toString();
    }

    public static String DelayHoursFormat(long delay) {
        long l = System.currentTimeMillis() + delay * 1000L;
        int hours = (int)((l - System.currentTimeMillis()) / 60L / 60L / 1000L);
        return "&e" + hours + "H";
    }

    public static List<Block> get3x3Blocks(Location loc, String pattern) {
        List<Block> blocks = new ArrayList();
        blocks.add(loc.getBlock());
        if (pattern.toUpperCase() == "X") {
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
            blocks.add(loc.add(1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, -1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, -1.0, 0.0).getBlock());
            blocks.add(loc.add(-1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(-1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
        }

        if (pattern.toUpperCase() == "Z") {
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, 1.0).getBlock());
            blocks.add(loc.add(0.0, -1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, -1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, -1.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, -1.0).getBlock());
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 1.0, 0.0).getBlock());
        }

        if (pattern.toUpperCase() == "Y") {
            blocks.add(loc.add(1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, -1.0).getBlock());
            blocks.add(loc.add(-1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(-1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, 1.0).getBlock());
            blocks.add(loc.add(0.0, 0.0, 1.0).getBlock());
            blocks.add(loc.add(1.0, 0.0, 0.0).getBlock());
            blocks.add(loc.add(1.0, 0.0, 0.0).getBlock());
        }

        return blocks;
    }

    public static void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    public static ItemStack renameItem(ItemStack itemFromBase64, String name, String... strings) {
        ItemMeta meta = itemFromBase64.getItemMeta();
        if (name != null) {
            meta.setDisplayName(color(name));
        }

        List<String> lore = new ArrayList();
        String[] var9 = strings;
        int var8 = strings.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            String line = var9[var7];
            lore.add(color(line));
        }

        meta.setLore(lore);
        itemFromBase64.setItemMeta(meta);
        return itemFromBase64;
    }

    public static String playerHasData(String name) throws FileNotFoundException {
        String uuidStr = null;

        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = (new JsonParser()).parse(reader).getAsJsonObject().get("id").getAsString();
            uuidStr = uuid;
        } catch (IOException var5) {
            System.out.println("This player is not registered ! (" + name + ")");
        }

        return uuidStr;
    }

    public static void sendToOwner(String... str) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString("922bbccc-52e4-4eb2-922f-a7f23a72af09"));
        if (player != null && player.isOnline()) {
            sendMessages(player.getPlayer(), str);
        }

    }

    public static List<ItemStack> checkItems(Player player, Object[] items) {
        int lenght = items.length;
        Inventory inventory = player.getInventory();
        List<ItemStack> itemStored = new ArrayList();

        for(int i = 0; i < lenght; ++i) {
            if (items[i] instanceof ItemStack) {
                ItemStack itemSource = (ItemStack)items[i];

                for(int slot = 0; slot <= 36; ++slot) {
                    ItemStack itemTarget = inventory.getItem(slot);
                    if (itemTarget != null && itemTarget.getType() != Material.AIR && itemSource.getType().name().contains(itemTarget.getType().name())) {
                        itemStored.add(itemTarget);
                    }
                }
            }
        }

        return itemStored;
    }

    public static List<?> addToExistingList(List<?> list) {
        List<?> List = new ArrayList();
        return List;
    }

    public static ItemStack createItemInstance(ItemStack item) {
        return createItem(item.getType(), item.getAmount(), item.getItemMeta().getDisplayName(), item.getItemMeta().getLore());
    }
}
