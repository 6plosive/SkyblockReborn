package me.alwayslg.customplayers.stats;

import me.alwayslg.customplayers.CustomPlayer;

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
        this.health = 500;
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

    public void updateHeart(){
        // Update real health bar (heart) display
        Health.updateHeart(customPlayer, health, maxHealth);
    }

    public void updateMaxHealth(){
        // get player's armor health
        int armorHealth = Health.getArmorHealth(customPlayer);
        // update max health
        maxHealth = Health.getDefaultMaxHealth() + armorHealth;
        // check if health is greater than max health
        if(health > maxHealth){
            health = maxHealth;
        }
        // Update real health bar display
        customPlayer.getPlayer().setMaxHealth(Health.translateHpToHearts(maxHealth));
    }

    // Run the below method to heal the player
    // Should only be run once per 2 seconds
    public void healPlayerNaturally(){
        int healthGained = Health.calculateHeal(maxHealth);
        if(health + healthGained > maxHealth){
            health = maxHealth;
        }else {
            health += healthGained;
        }
    }


}