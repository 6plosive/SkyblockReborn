package me.alwayslg.customplayers.stats;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customplayers.CustomPlayer;

import static me.alwayslg.customitems.CustomArmor.*;

public class StatsManager {
    CustomPlayer customPlayer;

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
        int armorHealth = Health.getArmorHealth(customPlayer);
        // update max health
        maxHealth = Health.getDefaultMaxHealth() + armorHealth;
    }

    // Run the below method to heal the player
    // Should only be run once per 2 seconds
    public void healPlayer(){
        int healthGained = Health.calculateHeal(maxHealth);
        if(health + healthGained > maxHealth){
            health = maxHealth;
        }else {
            health += healthGained;
        }
    }

}
