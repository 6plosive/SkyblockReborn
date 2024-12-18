package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;
import static me.alwayslg.customitems.CustomArmor.isBoots;

public class Defense {
    public final static double defaultDefense = 0;
    public static double getDefaultDefense() {
        return defaultDefense;
    }
    public static double getArmorDefense(CustomPlayer customPlayer){
        // get player's armor health
        double armorDefense = 0;
        if(isHelmet(customPlayer.getPlayer().getInventory().getHelmet())){
            CustomArmor helmet = new CustomArmor(customPlayer.getPlayer().getInventory().getHelmet());
            armorDefense += helmet.getDefense();
        }
        if(isChestplate(customPlayer.getPlayer().getInventory().getChestplate())){
            CustomArmor chestplate = new CustomArmor(customPlayer.getPlayer().getInventory().getChestplate());
            armorDefense += chestplate.getDefense();
        }
        if(isLeggings(customPlayer.getPlayer().getInventory().getLeggings())){
            CustomArmor leggings = new CustomArmor(customPlayer.getPlayer().getInventory().getLeggings());
            armorDefense += leggings.getDefense();
        }
        if(isBoots(customPlayer.getPlayer().getInventory().getBoots())){
            CustomArmor boots = new CustomArmor(customPlayer.getPlayer().getInventory().getBoots());
            armorDefense += boots.getDefense();
        }
        return armorDefense;
    }
}
