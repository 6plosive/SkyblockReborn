package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;
import static me.alwayslg.customitems.CustomArmor.isBoots;

public class Health {
//    public final static int defaultHealth = 100;
    public final static int defaultMaxHealth = 100;
    public final static int HealthRegen = 100;
    public static int getDefaultMaxHealth() {
        return defaultMaxHealth;
    }
    public static int getArmorHealth(CustomPlayer customPlayer){
        // get player's armor health
        int armorHealth = 0;
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

    public static int calculateHeal(int maxHealth){
        double heal = 1.5 + ((double) maxHealth / 100);
        heal *= (double) HealthRegen /100;
        return (int) heal;
    }

    public static int translateHpToHearts(int maxHealth) {
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
        customPlayer.getPlayer().setHealth(healthInHearts);
    }
}
