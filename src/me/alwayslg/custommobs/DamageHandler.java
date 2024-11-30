package me.alwayslg.custommobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class DamageHandler implements Listener {
    private static ArrayList<CustomMob> customMobs = new ArrayList<>();
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity damagedentity = event.getEntity();
        Bukkit.broadcastMessage("Damaged UUID: "+damagedentity.getUniqueId());
        // Check if the damaged entity is a Zombie
        for(CustomMob customMob:customMobs){
            if(customMob.getEntity().getUniqueId()==damagedentity.getUniqueId()){
                LivingEntity damagedLivingEntity = (LivingEntity) damagedentity;
                // You can modify the damage, send messages, or implement other logic here
                double damage = event.getFinalDamage();
                int remainingHealth = (int) (customMob.getHealth()-damage);
//                Bukkit.broadcastMessage("Damage to zombie: "+damage+" | Remaining Health: "+remainingHealth);
                customMob.getOverheadDisplay().setText(String.format("§8[§7Lv%d§8] §c%s §a%d§f/§a%d",customMob.getLevel(),customMob.getName(),remainingHealth,customMob.getFullHealth()));
//                event.setDamage(event.getDamage());
                customMob.getEntity().setNoDamageTicks(1);
                // Example: Cancel the damage if you want to prevent the zombie from taking damage
                // event.setCancelled(true);
            }
        }
    }
    public static void addMob(CustomMob mob){
        customMobs.add(mob);
    }
}
