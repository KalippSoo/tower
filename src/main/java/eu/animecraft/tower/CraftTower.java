//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.animecraft.tower;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import eu.animecraft.data.Lvl;
import eu.animecraft.tower.tools.AttackType;
import org.bukkit.entity.ArmorStand;

public class CraftTower {
    private boolean slash;
    private float distance;
    private float damage;
    private int maxTarget;
    private int spawnCount = 3;
    private int reloadTimeValue;
    private float reloadTime = 0.0F;
    private float slashRange;
    private float stunDuration;
    private float damageDealt = 0.0F;
    private AttackType AOE;
    public ArmorStand tower;

    public CraftTower clone() {
        try {
            Constructor<?> construtor = this.getClass().getDeclaredConstructor();
            Object newInstance = construtor.newInstance();
            Field[] var6;
            int var5 = (var6 = newInstance.getClass().getDeclaredFields()).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Field f = var6[var4];
                Field[] var10;
                int var9 = (var10 = this.getClass().getDeclaredFields()).length;

                for(int var8 = 0; var8 < var9; ++var8) {
                    Field f2 = var10[var8];
                    if (!f.getName().contains("org.bukkit.entity.ArmorStand") && f2.getName().equals(f.getName()) && f2.getType() == f.getType()) {
                        f.setAccessible(true);
                        f.set(newInstance, f2.get(this));
                        f.setAccessible(false);
                    }
                }
            }

            return (CraftTower)newInstance;
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var11) {
            var11.printStackTrace();
            return new CraftTower();
        }
    }


    /*
        public void reloadTimer() {
        if (this.reloadTime <= 0.0F) {
            if (!this.attack()) {
                return;
            }

            this.reloadTime = (float)this.reloadTimeValue;
        }

        --this.reloadTime;
    }
    public boolean attack() {
        List<?> list = ((CraftWorld)this.tower.getWorld()).getHandle().a(EntityLiving.class, ((CraftArmorStand)this.tower).getHandle().getBoundingBox().grow((double)this.distance, (double)(this.distance / 2.0F), (double)this.distance));
        Iterator<?> iterator = list.iterator();
        LivingEntity single = null;

        while(iterator.hasNext()) {
            EntityLiving living = (EntityLiving)iterator.next();
            if (!living.getBukkitEntity().isInvulnerable() && this.maxTarget == 1) {
                single = (LivingEntity)living.getBukkitEntity();
                break;
            }
        }

        if (single == null) {
            return false;
        } else {
            list = ((CraftWorld)single.getWorld()).getHandle().a(EntityLiving.class, ((CraftLivingEntity)single).getHandle().getBoundingBox().grow((double)this.slashRange, (double)(this.slashRange / 2.0F), (double)this.slashRange));
            iterator = list.iterator();
            TowerHittingTargetEvent event = null;
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                while(iterator.hasNext()) {
                    EntityLiving entityLiving = (EntityLiving)iterator.next();
                    if (!entityLiving.getBukkitEntity().isInvulnerable()) {
                        LivingEntity living = (LivingEntity)entityLiving.getBukkitEntity();
                        switch (this.type[0]) {
                            case ALL:
                            default:
                                break;
                            case FIRE:
                                living.setFireTicks(100);
                                break;
                            case ICE:
                                living.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
                                break;
                            case WIND:
                                living.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255));
                                living.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 0));
                        }

                        living.damage((double)this.damage);
                    }
                }
            }

            return true;
        }
    }
     */

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getMaxTarget() {
        return this.maxTarget;
    }

    public void setMaxTarget(int maxTarget) {
        this.maxTarget = maxTarget;
    }

    public boolean isSlash() {
        return this.slash;
    }

    public void setSlash(boolean slash) {
        this.slash = slash;
    }

    public int getSpawnCount() {
        return this.spawnCount;
    }

    public void setSpawnCount(int spawnCount) {
        this.spawnCount = spawnCount;
    }

    public float getReloadTime() {
        return this.reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getReloadTimeValue() {
        return this.reloadTimeValue;
    }

    public int getReloadTimeValueInSeconds() {
        return this.reloadTimeValue / 20;
    }

    public void setReloadTimeValue(int reloadTimeValue) {
        this.reloadTimeValue = reloadTimeValue;
    }

    public float getSlashRange() {
        return this.slashRange;
    }

    public void setSlashRange(float slashRange) {
        this.slashRange = slashRange;
    }

    public float getStunDuration() {
        return this.stunDuration;
    }

    public void setStunDuration(float stunDuration) {
        this.stunDuration = stunDuration;
    }

    public float getDamageDeal() {
        return this.damageDealt;
    }

    public void addDamageDeal(float damageDeal) {
        this.damageDealt += damageDeal;
    }

    public void setDamageDeal(float damageDeal) {
        this.damageDealt = damageDeal;
    }

    public AttackType getAOE() {
        return this.AOE;
    }

    public void setAOE(AttackType aOE) {
        this.AOE = aOE;
    }
}
