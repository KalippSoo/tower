package eu.animecraft.tower.tools;

public enum Rarity {
    COMMON("&a&lCOMMON TOWER", "&a"),
    RARE("&9&lRARE TOWER", "&9"),
    EPIC("&5&lEPIC TOWER", "&5"),
    LEGENDARY("&6&lLEGENDARY TOWER", "&6"),
    MYTHIC("&d&kO &r&d&lMythic TOWER &d&kO", "&d"),
    LIMITED("&c&kOOO &r&c&lLIMITED TOWER &c&kOOO", "&c");

    private String name;
    private String color;

    private Rarity(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }
}
