package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;
import static me.alwayslg.customitems.CustomArmor.isBoots;

public class Health {
//    public final static double defaultHealth = 100;
    public final static double defaultMaxHealth = 100;
    public final static double HealthRegen = 100;
    public static double getDefaultMaxHealth() {
        return defaultMaxHealth;
    }
    public static double getArmorHealth(CustomPlayer customPlayer){
        // get player's armor health
        double armorHealth = 0;
        if(isHelmet(customPlayer.getPlayer().getInventory().getHelmet())){
            CustomArmor helmet = new CustomArmor(customPlayer.getPlayer().getInventory().getHelmet());
            armorHealth += helmet.getHealth();
        }
        if(isChestplate(customPlayer.getPlayer().getInventory().getChestplate())){
            CustomArmor chestplate = new CustomArmor(customPlayer.getPlayer().getInventory().getChestplate());
            armorHealth += chestplate.getHealth();
        }
        if(isLeggings(customPlayer.getPlayer().getInventory().getLeggings())){
            CustomArmor leggings = new CustomArmor(customPlayer.getPlayer().getInventory().getLeggings());
            armorHealth += leggings.getHealth();
        }
        if(isBoots(customPlayer.getPlayer().getInventory().getBoots())){
            CustomArmor boots = new CustomArmor(customPlayer.getPlayer().getInventory().getBoots());
            armorHealth += boots.getHealth();
        }
        return armorHealth;
    }

    public static double calculateHeal(double maxHealth){
        double heal = 1.5 + (maxHealth / 100);
        heal *= HealthRegen /100;
        return heal;
    }

    public static int translateHpToHearts(double maxHealth) {
        if (maxHealth < 125) {
            return 20;
        } else if (maxHealth < 165) {
            return 22;
        } else if (maxHealth < 230) {
            return 24;
        } else if (maxHealth < 300) {
            return 26;
        } else if (maxHealth < 400) {
            return 28;
        } else if (maxHealth < 500) {
            return 30;
        } else if (maxHealth < 650) {
            return 32;
        } else if (maxHealth < 800) {
            return 34;
        } else if (maxHealth < 1000) {
            return 136;
        } else if (maxHealth < 1250) {
            return 138;
        } else {
            return 40;
        }
    }

    public static void updateHeart(CustomPlayer customPlayer, double health, double maxHealth){
        double maxHealthInHearts = translateHpToHearts((int) maxHealth);
        int healthInHearts = (int) (health * maxHealthInHearts / maxHealth);
        if(healthInHearts<1){
            healthInHearts = 1;
        }
        customPlayer.getPlayer().setHealth(healthInHearts);
    }
}
