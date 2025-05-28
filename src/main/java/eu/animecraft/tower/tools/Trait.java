package eu.animecraft.tower.tools;

import java.util.concurrent.ThreadLocalRandom;

public enum Trait {
    DIVINE(0.05, Rarity.MYTHIC),
    UNIQUE(0.1, Rarity.MYTHIC),
    PARTICULAR(0.25, Rarity.MYTHIC),
    STAR(0.5, Rarity.MYTHIC),
    DEADLY(2, Rarity.LEGENDARY),
    SKILLED(10, Rarity.LEGENDARY),
    QUICKLEANER(15, Rarity.LEGENDARY),
    VISION(30, Rarity.EPIC),
    AGILITY(30, Rarity.EPIC),
    FORCE(30, Rarity.EPIC),
    none(-1, Rarity.EPIC),
    ;
	
    private double chance;
    private String name;
    private Rarity rarity;

    Trait(double chance, Rarity rarity){
        this.chance = chance;
        this.rarity = rarity;
        this.name = rarity.getColor()+name();
    }
    public static Trait getTraitResult(){

        double random = Math.random() * (getChances()+1), previous = 0;
        Trait t = Trait.FORCE;

        for (Trait trait : values()){
        	if (trait == none)continue;
            previous += trait.chance;
            if (random <= previous){
                t = trait;
                break;
            }
        }

        if (t.chance == 30){
            int i = ThreadLocalRandom.current().nextInt(3);
            switch (i){
                case 0: t = Trait.FORCE; break;
                case 1: t = Trait.AGILITY; break;
                case 2: t = Trait.VISION; break;
            }
        }

        return t;

    }
    
    public static double getChances(){
        double d = 0;
        for (Trait trait : values()){
            d+=trait.chance;
        }
        return d;
    }
    
    public static Trait getTrait(String name){
        for (Trait trait : values()){
            if (trait.name() == name)
                return trait;
        }
        return null;
    }
    
	public Rarity getRarity() {
		return rarity;
	}
	public String getName() {
		return name;
	}

}
