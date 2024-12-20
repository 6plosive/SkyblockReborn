package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;

public class Strength {
    public final static double defaultStrength = 0;
    public static double getDefaultStrength() {
        return defaultStrength;
    }
    public static double getArmorStrength(CustomPlayer customPlayer){
        // get player's armor health
        double armorStrength = 0;
        if(isHelmet(customPlayer.getPlayer().getInventory().getHelmet())){
            CustomArmor helmet = new CustomArmor(customPlayer.getPlayer().getInventory().getHelmet());
            armorStrength += helmet.getStrength();
        }
        if(isChestplate(customPlayer.getPlayer().getInventory().getChestplate())){
            CustomArmor chestplate = new CustomArmor(customPlayer.getPlayer().getInventory().getChestplate());
            armorStrength += chestplate.getStrength();
        }
        if(isLeggings(customPlayer.getPlayer().getInventory().getLeggings())){
            CustomArmor leggings = new CustomArmor(customPlayer.getPlayer().getInventory().getLeggings());
            armorStrength += leggings.getStrength();
        }
        if(isBoots(customPlayer.getPlayer().getInventory().getBoots())){
            CustomArmor boots = new CustomArmor(customPlayer.getPlayer().getInventory().getBoots());
            armorStrength += boots.getStrength();
        }
        return armorStrength;
    }
    public static double getItemInHandStrength(CustomPlayer customPlayer){
        if(isCustomItem(customPlayer.getPlayer().getInventory().getItemInHand()))
            return new CustomArmor(customPlayer.getPlayer().getInventory().getItemInHand()).getStrength();
        return 0;
    }

    public static double getStrength(CustomPlayer customPlayer){
        return getDefaultStrength() + getArmorStrength(customPlayer);
    }
}
