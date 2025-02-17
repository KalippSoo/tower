package eu.animecraft.tower.tools;

import java.util.concurrent.ThreadLocalRandom;

public enum Trait {
    none(0, -1, -1),
    FORCE(3, 0, 30),
    AGILITY(3, 1, 30),
    VISION(3, 2, 30),
    SKILLED(2, 3, 10),
    DEADLY(2, 5, 0.5),
    STAR(2, 5, 0.4),
    PARTICULAR(2, 5, 0.25),
    UNIQUE(1, 6, 0.1),
    ;

    private int level;
    private int id;
    private double chance;

    Trait(int level, int id, double chance){
        this.level = level;
        this.id = id;
        this.chance = chance;
    }
    public static Trait getTraitResult(){

        double random = Math.random() * 101, previous = 0;
        Trait t = Trait.none;

        for (Trait trait : values()){
            previous += trait.chance;
            if (random <= previous){
                t = trait;
                break;
            }
        }

        if (t.level == 3 && t.chance == 30 || t == Trait.none){
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
    public int getLevel(){
        return this.level;
    }
    public static Trait getTrait(String name){
        for (Trait trait : values()){
            if (trait.name() == name)
                return trait;
        }
        return null;
    }

}
