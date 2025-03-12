package eu.animecraft.data;

import eu.animecraft.AnimeCraft;
import eu.animecraft.tower.Tower;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentRelated {

    public static void createGetDocument(Player player, Data data){

        Document playerData = new Document("uuid", player.getUniqueId().toString());
        Document found = AnimeCraft.profiles.find(playerData).first();

        int gems = 500;
        int inventorySize = 50;
        int gold = 0;
        int experience = 0;
        int level = 1;
        int rerolls = 0;
        String title = "";

        Document stats, quests, menu, collection, inventory, banner, classic, event, profile;

        List<String> list = new ArrayList<>();
        if (found == null){
            //Create data for player
            stats = new Document();
            quests = new Document();
            menu = new Document();
            collection = new Document();
            inventory = new Document();
            banner = new Document();
            classic = new Document();
            event = new Document();
            profile = new Document();

            stats.append("level", 1);
            stats.append("title", null);
            stats.append("gems", gems);
            stats.append("gold", 0);
            stats.append("reroll", 0);
            stats.append("rank", "player");

            collection.append("size", 200);
            collection.append("contents", new ArrayList<>());

            inventory.append("size", inventorySize);
            inventory.append("contents", new ArrayList<>());

            menu.append("collection", collection);
            menu.append("inventory", inventory);

            for(int i = 1; i<= 6; i++){
                list.add("empty");
            }

            menu.append("selection", list);

            quests.append("0", new Document("value", "0").append("done", false));

            classic.append("classic", new Document().append("legpity", 0).append("mithycpity",0));
            event.append("classic", new Document().append("legpity", 0).append("mithycpity",0));

            banner.append("classic", classic);
            banner.append("event", event);

            profile.append("info", banner);
            profile.append("stats", stats);
            profile.append("menu", menu);
            profile.append("quests", quests);

            playerData.append("account-creation", new Date().toString());
            playerData.append("profile", profile);

            AnimeCraft.profiles.insertOne(playerData);
        }else{
            menu = getSpecificDocument(player, "menu");
            inventory = getSpecificDocument(player, "inventory");
            collection = getSpecificDocument(player, "collection");
            stats = getSpecificDocument(player, "stats");

            gems = stats.getInteger("gems");
            gold = stats.getInteger("gold");
            level = stats.getInteger("level");
            list = menu.getList("selection", String.class);
            inventorySize = inventory.getInteger("size");
            title = stats.getString("title");
            rerolls = stats.getInteger("reroll");
        }

        data.gems = gems;
        data.experience = experience;
        data.gold = gold;
        data.level = level;
        data.title = title;
        data.rerolls = rerolls;

        data.setListSelected(list);
        data.setMaxStorageSize(inventorySize);

    }

    public static void saveDocument(Player player){

        Data data = AnimeCraft.instance.getDataManager().getPlayerData().get(player.getUniqueId());
        Document playerData = getPlayerDocument(player);
        if (playerData == null)return;

        Document
                profile = new Document(),
                stats = new Document(),
                menu = new Document(),
                quests = new Document(),

                collection = new Document(),
                inventory = new Document()
                ;

        stats.append("level", data.level);
        stats.append("title", data.title);
        stats.append("gems", data.gems);
        stats.append("gold", data.gold);
        stats.append("reroll", data.rerolls);
        stats.append("rank", data.getGroup().getGroup() == null ? "player" : data.getGroup().getGroup());

        collection.append("size", 200);
        collection.append("contents", new ArrayList<>());

        ArrayList<String> towersList = new ArrayList<>();
        for (Tower tower : data.getTowers()){
            towersList.add(tower.getDocumentCode());
        }

        inventory.append("size", data.getMaxStorageSize());
        inventory.append("contents", towersList);

        menu.append("collection", collection);
        menu.append("inventory", inventory);
        if (data.getListSelected().isEmpty()){
            List<String> list = new ArrayList<>();
            for (int i = 1; i<= 6; i++){
                list.add("none");
            }
            menu.append("selection", list);
        }else{
            menu.append("selection", data.getListSelected());
        }

        //profile.append("info", banner);
        profile.append("stats", stats);
        profile.append("menu", menu);
        profile.append("quests", quests);

        Document update = new Document("profile", profile);
        Document updateOperation = new Document("$set", update);
        try {
            AnimeCraft.profiles.updateOne(playerData, updateOperation);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error to update profile !");
        }

    }

    public static Document getPlayerDocument(Player player){
        return AnimeCraft.profiles.find(new Document("uuid", player.getUniqueId().toString())).first();
    }

    public static Document getSpecificDocument(Player player, String name){

        Document playerData = new Document("uuid", player.getUniqueId().toString());
        Document found = AnimeCraft.profiles.find(playerData).first();

        if (found == null)return null;

        Document
                profile = (Document) found.get("profile"),

                stats = (Document) profile.get("stats"),
                menu = (Document) profile.get("menu"),
                quests = (Document) profile.get("quests"),

                collection = (Document) menu.get("collection"),
                inventory = (Document) menu.get("inventory")
                        ;

        switch (name){
            case "profile": return profile;
            case "stats": return stats;
            case "menu": return menu;
            case "quests": return quests;
            case "collection": return collection;
            case "inventory": return inventory;

            default:
                return null;
        }

    }

}
