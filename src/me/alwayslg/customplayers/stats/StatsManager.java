package me.alwayslg.customplayers.stats;

import me.alwayslg.custommobs.CustomMobID;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.util.Utilities;
import org.bukkit.Bukkit;

public class StatsManager {
    CustomPlayer customPlayer;

    private double critChance;
    private double critDamage;
    private double health;
    private double maxHealth;
    private double defense;
    private double strength;

    public StatsManager(CustomPlayer customPlayer){
        this.customPlayer = customPlayer;
        // get player spawn max health
        updateMaxHealth();
        // get player spawn defense
        updateDefense();
        // get player spawn strength
        updateStrength();
        // Default stats
        this.critChance = CritChance.getDefaultCritChance();
        this.critDamage = CritDamage.getDefaultCritDamage();
        this.health = maxHealth;
    }
    public double getCritChance(){
        return critChance;
    }
    public double getCritDamage(){
        return critDamage;
    }
    public double getHealth(){
        return health;
    }
    public double getMaxHealth(){
        return maxHealth;
    }
    public double getDefense(){
        return defense;
    }
    public double getStrength(){
        return strength;
    }

    public void setHealth(double health){
        this.health = health;
        updateHeart();
    }

    public void updateHeart(){
        // Update real health bar (heart) display
        Health.updateHeart(customPlayer, health, maxHealth);
    }

    public void updateMaxHealth(){
        // get player's armor health
        double armorHealth = Health.getArmorHealth(customPlayer);
        // update max health
        maxHealth = Health.getDefaultMaxHealth() + armorHealth;
        // check if health is greater than max health
        if(health > maxHealth){
            health = maxHealth;
        }
        // Update real health bar display
        customPlayer.getPlayer().setMaxHealth(Health.translateHpToHearts(maxHealth));
    }
    public void updateDefense(){
        // get player's armor defense
        double armorDefense = Defense.getArmorDefense(customPlayer);
        // update defense
        defense = Defense.getDefaultDefense() + armorDefense;
    }
    public void updateStrength(){
        // get player's armor and item in hand's strength
        strength = Strength.getArmorStrength(customPlayer);
    }

    public void dealDamage(double damage, CustomMobID damagerID){
        // calculate damage reduction
        double damageReduction = 1 - calculateDamageReduction(defense);
        damage = (damage * damageReduction);
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

    public static double calculateEHP(double health, double defense){
        return health * (1 + (defense / 100.0));
    }
    public static double calculateDamageReduction(double defense){
        return defense / (defense + 100.0);
    }

    // Run the below method to heal the player
    // Should only be run once per 2 seconds
    public void healPlayerNaturally(){
        double healthGained = Health.calculateHeal(maxHealth);
        if(health + healthGained > maxHealth){
            health = maxHealth;
        }else {
            health += healthGained;
        }
    }


}