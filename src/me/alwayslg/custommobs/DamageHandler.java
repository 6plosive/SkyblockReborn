package me.alwayslg.custommobs;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;

import static me.alwayslg.customitems.CustomItem.*;

public class DamageHandler implements Listener {
    private static ArrayList<CustomMob> customMobs = new ArrayList<>();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damagedEntity = event.getEntity();
//        Bukkit.broadcastMessage("Damaged UUID: "+damagedentity.getUniqueId());
        // Check if the damaged entity is a Zombie
        for(CustomMob customMob:customMobs){
            if(customMob.getEntity().getUniqueId()==damagedEntity.getUniqueId()){
                if(event.getDamager() instanceof Player){
                    Player damager = (Player) event.getDamager();
//                    Bukkit.broadcastMessage("Item in hand:"+damager.getInventory().getItemInHand().getType()+" | is custom?: "+isCustomItem(damager.getInventory().getItemInHand()));
                    if(isCustomItem(damager.getInventory().getItemInHand())) {
                        double damage = getDoubleNBTTagsFromItemStack("damage", damager.getInventory().getItemInHand());
                        // This won't work because final damage is different
//                    event.setDamage(damage);
                        // Workaround for custom damage
                        event.setDamage(0);
                        // idk which func it is for setLastHurtByPlayer. Gave up after 1 min xd
//                    net.minecraft.server.v1_8_R3.EntityLiving  nmsEntity = ((CraftLivingEntity) livingEntity).getHandle();
//                    nmsEntity.setLastHurtByPlayer(((CraftPlayer) damager).getHandle());
                        customMob.setHealth(Math.max(0, (customMob.getHealth() - damage)));
                    }
                }
                int remainingHealth = (int) customMob.getHealth();
                char healthColor = 'a';
                if(customMob.getHealth()*2<customMob.getFullHealth()){
                    healthColor = 'e';
                }
//                Bukkit.broadcastMessage("Damage to zombie: "+damage+" | Remaining Health: "+remainingHealth);
                customMob.getOverheadDisplay().setText(String.format("§8[§7Lv%d§8] §c%s §%c%d§f/§a%d",customMob.getLevel(),customMob.getName(),healthColor,remainingHealth,customMob.getFullHealth()));

                // Remove no damage ticks completely making player damage it every tick
//                customMob.getEntity().setNoDamageTicks(1);
//                customMob.getEntity().setMaximumNoDamageTicks(1);
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
