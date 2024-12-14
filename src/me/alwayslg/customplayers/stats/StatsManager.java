package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;

public class StatsManager {
    CustomPlayer customPlayer;
    private static final int defaultCritChance = 30;
    private static final int defaultCritDamage = 50;
    private static final int defaultHealth = 100;
    private static final int defaultMaxHealth = 100;

    private int critChance;
    private int critDamage;
    private int health;
    private int maxHealth;

    public StatsManager(CustomPlayer customPlayer){
        this.customPlayer = customPlayer;
        // Default stats
        this.critChance = 30;
        this.critDamage = 50;
        this.health = 100;
        this.maxHealth = 100;
    }
    public int getCritChance(){
        return critChance;
    }
    public int getCritDamage(){
        return critDamage;
    }
    public int getHealth(){
        return health;
    }
    public int getMaxHealth(){
        return maxHealth;
    }

    public void updateMaxHealth(){
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
        // update max health
        maxHealth = defaultMaxHealth + armorHealth;
    }

}
