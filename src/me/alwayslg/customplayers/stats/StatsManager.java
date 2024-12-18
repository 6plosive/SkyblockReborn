package me.alwayslg.customplayers.stats;

import me.alwayslg.custommobs.CustomMobID;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.util.Utilities;
import org.bukkit.Bukkit;

public class StatsManager {
    CustomPlayer customPlayer;

    private int critChance;
    private int critDamage;
    private int health;
    private int maxHealth;

    public StatsManager(CustomPlayer customPlayer){
        this.customPlayer = customPlayer;
        // get player spawn max health
        updateMaxHealth();
        // Default stats
        this.critChance = CritChance.getDefaultCritChance();
        this.critDamage = CritDamage.getDefaultCritDamage();
        this.health = maxHealth;
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

    public void setHealth(int health){
        this.health = health;
        updateHeart();
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

    public void dealDamage(int damage, CustomMobID damagerID){
        // Deal damage to the player
        health -= damage;
        // If health is less than 0, player died
        if(health <= 0){
            // Broadcast death message
            customPlayer.getPlayer().sendMessage(String.format(" §c☠ §7You were killed by %s.",damagerID.getName()));
            String playerNameColor = customPlayer.getRank().getChatPrefix().substring(0,2);
            Utilities.broadcastMessageExcept(String.format(" §c☠ %s%s §7was killed by %s.",playerNameColor,customPlayer.getPlayer().getName(),damagerID.getName()), customPlayer.getPlayer());
            // Respawn player at hub portal location
            customPlayer.respawn();
        }
        // Update real health bar (heart) display
        Health.updateHeart(customPlayer, health, maxHealth);
    }

    public void dealFallDamage(int damage){
        // Deal fall damage to the player
        health -= damage;
        // If health is less than 0, player died
        if(health <= 0){
            // Broadcast death message
            customPlayer.getPlayer().sendMessage(" §c☠ §7You fell to your death.");
            String playerNameColor = customPlayer.getRank().getChatPrefix().substring(0,2);
            Utilities.broadcastMessageExcept(String.format(" §c☠ %s%s §7fell to their death.",playerNameColor,customPlayer.getPlayer().getName()), customPlayer.getPlayer());
            // Respawn player at hub portal location
            customPlayer.respawn();
        }
        // Update real health bar (heart) display
        Health.updateHeart(customPlayer, health, maxHealth);
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