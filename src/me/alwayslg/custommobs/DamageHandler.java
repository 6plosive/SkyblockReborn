package me.alwayslg.custommobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;

public class DamageHandler implements Listener {
    private static ArrayList<CustomMob> customMobs = new ArrayList<>();
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity damagedEntity = event.getEntity();
//        Bukkit.broadcastMessage("Damaged UUID: "+damagedentity.getUniqueId());
        // Check if the damaged entity is a Zombie
        for(CustomMob customMob:customMobs){
            if(customMob.getEntity().getUniqueId()==damagedEntity.getUniqueId()){
                // You can modify the damage, send messages, or implement other logic here
                double damage = event.getFinalDamage();
                int remainingHealth = Math.max(0, (int) (customMob.getHealth() - damage));
                char healthColor = 'a';
                if(remainingHealth*2<customMob.getFullHealth()){
                    healthColor = 'e';
                }
//                Bukkit.broadcastMessage("Damage to zombie: "+damage+" | Remaining Health: "+remainingHealth);
                customMob.getOverheadDisplay().setText(String.format("§8[§7Lv%d§8] §c%s §%c%d§f/§a%d",customMob.getLevel(),customMob.getName(),healthColor,remainingHealth,customMob.getFullHealth()));

                customMob.getEntity().setNoDamageTicks(1);
                break;
            }
        }
    }
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        Entity deathEntity = event.getEntity();
        Bukkit.broadcastMessage("Death entity UUID: "+deathEntity.getUniqueId());
        for(CustomMob customMob:customMobs){
            if(customMob.getEntity().getUniqueId()==deathEntity.getUniqueId()){
                //Remove overhead display
                OverheadDisplayHandler.removeDisplay(customMob.getOverheadDisplay());
                //Remove from array
                customMobs.remove(customMob);
                break;
            }
        }
    }
    public static void addMob(CustomMob mob){
        customMobs.add(mob);
    }
}
