package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;
import org.bukkit.entity.Player;

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
}
