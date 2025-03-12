//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.animecraft.data;

import eu.animecraft.tower.Tower;

public class Lvl {
    Tower tower;
    int maxLevel = 50;
    int currentLevel = 1;
    double currentExp = 0.0;
    //Gain after each lvl
    double pourcentage = 4.6;
    
    double[] levelingUp = new double[]{
            2050.0, 8050.0, 18050.0, 32050.0, 50050.0, 72050.0, 98050.0, 128100.0, 162100.0, 200100.0, 242100.0, 288100.0,
            338100.0, 392100.0, 450100.0, 512100.0, 578100.0, 648100.0, 722100.0, 800100.0, 882100.0, 968100.0, 1058000.0,
            1152000.0, 1250000.0, 1352000.0, 1458000.0, 1568000.0, 1682000.0, 1800000.0, 1922000.0, 2048000.0, 2178000.0,
            2312000.0, 2450000.0, 2592000.0, 2738000.0, 2888000.0, 3042000.0, 3200000.0, 3362000.0, 3528000.0, 3698100.0,
            3872100.0, 4050100.0, 4232100.0, 4418100.0, 4608100.0, 4802100.0, 5000000.0};

    public Lvl(Tower tower) {
        this.tower = tower;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public double getCurrentExp() {
        return this.currentExp;
    }

    public void setLevel(int level) {
        if (level > maxLevel) {
            this.currentLevel = maxLevel;
        } else {
            this.currentLevel = level;
        }

    }

    public void addLevel() {
        if (this.currentLevel != this.maxLevel) {
            ++this.currentLevel;
            tower.updateItem();
        }
    }

    public void addExp(double exp) {
        this.currentExp += exp;
        if (this.currentLevel < 50) {
            if (this.currentExp >= this.levelingUp[this.currentLevel]) {
                this.addLevel();
            }

        }
    }

    public void setExp(double exp) {
        this.currentExp = exp;
    }

    public double getMaxExp() {
        return this.currentLevel == 50 ? 0.0 : this.levelingUp[this.currentLevel];
    }

    public double getDamage(){
    	int damage = (int) (tower.damage + ((tower.damage * (pourcentage * currentLevel))/100));
        return damage;
    }
}
