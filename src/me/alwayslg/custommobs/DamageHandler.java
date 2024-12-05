package me.alwayslg.custommobs;

import me.alwayslg.customitems.CustomItem;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

import static me.alwayslg.customitems.CustomItem.*;
import static me.alwayslg.listeners.DingOnHit.playDing;
import static me.alwayslg.util.Utilities.*;

public class DamageHandler implements Listener {
    private static HashMap<UUID,CustomMob> customMobs = new HashMap<>();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damagedEntity = event.getEntity();
//        Bukkit.broadcastMessage("Damaged UUID: "+damagedentity.getUniqueId());
        if(customMobs.get(damagedEntity.getUniqueId())!=null){
            Bukkit.broadcastMessage("Damager type:"+event.getDamager().getType());

            CustomMob customMob = customMobs.get(damagedEntity.getUniqueId());
            if(event.getDamager() instanceof Player){
                Player damager = (Player) event.getDamager();
                event.setDamage(0);
                dealCustomDamage(damager,customMob);
            }else if(event.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow) event.getDamager();
                event.setDamage(0);
                dealArrowDamage(arrow,customMob);
            }

            // Remove no damage ticks completely making player damage it every tick
//                customMob.getEntity().setNoDamageTicks(1);
//                customMob.getEntity().setMaximumNoDamageTicks(1);
        }
    }
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        Entity deathEntity = event.getEntity();
//        Bukkit.broadcastMessage("Death entity UUID: "+deathEntity.getUniqueId());
        removeMob(deathEntity.getUniqueId());
    }
    public static void removeMob(UUID deathEntityUUID){
        if(customMobs.get(deathEntityUUID)!=null){
            CustomMob customMob = customMobs.get(deathEntityUUID);
            //Remove overhead display
            HealthBarHandler.removeDisplay(customMob.getOverheadDisplay());
            //Remove from map
            customMobs.remove(deathEntityUUID);
        }
    }
    public static void dealMagicDamageNearbyEntities(Location location, double damageRadius, Player player){
        List<CustomMob> nearbyCustomMobs = getNearbyCustomMobs(location,damageRadius,damageRadius,damageRadius);
        for (CustomMob customMob : nearbyCustomMobs) {
//            dealCustomDamage(player,customMob);
            dealMagicDamage(player,customMob);
        }
    }
    public static void dealRealDamageNearbyEntities(Location location, double damageRadius, Player player){
        List<CustomMob> nearbyCustomMobs = getNearbyCustomMobs(location,damageRadius,damageRadius,damageRadius);
        for (CustomMob customMob : nearbyCustomMobs) {
            dealCustomDamage(player,customMob);
//            dealMagicDamage(player,customMob);
        }
    }
    // x,y,z is the radius of the collision box
    public static List<CustomMob> getNearbyCustomMobs(Location location, double x, double y, double z){
        List<CustomMob> nearbyCustomMobs = new ArrayList<>();
        for (CustomMob customMob : customMobs.values()) {
//            Location mobLocation = customMob.getEntity().getLocation();
//            if (location.distanceSquared(mobLocation) <= (x * x + y * y + z * z)) {
//                nearbyCustomMobs.add(customMob);
//            }
            AxisAlignedBB mobBoundingBox = getBoundingBox(customMob.getEntity());
            double x1,y1,z1,x2,y2,z2;
            x1=location.getX()-x;
            x2=location.getX()+x;
            y1=location.getY()-y;
            y2=location.getY()+y;
            z1=location.getZ()-z;
            z2=location.getZ()+z;
            AxisAlignedBB damageBox = new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
            if(checkCollisionBoundingBoxes(mobBoundingBox,damageBox)){
                nearbyCustomMobs.add(customMob);
            }
        }

        return nearbyCustomMobs;
    }
    //
    public static void damageEntitiesInLocation(Location location, Player player){
        List<CustomMob> collisions = getCustomMobsAtLocation(location);
        for(CustomMob customMob:collisions){
//            dealCustomDamage(player,customMob);
            dealMagicDamage(player,customMob);
        }
    }
    public static List<CustomMob> getCustomMobsAtLocation(Location location){
        List<CustomMob> collisions = new ArrayList<>();
        for(CustomMob customMob:customMobs.values()){
            AxisAlignedBB boundingBox = getBoundingBox(customMob.getEntity());
            if(checkCollisionLocationBoundingBox(location,boundingBox)){
                collisions.add(customMob);
            }
        }
        return collisions;
    }

    private static void dealCustomDamage(Player damager, CustomMob target){
        // Turn mob to red effect
        target.getEntity().damage(0);
        if(isCustomItem(damager.getInventory().getItemInHand())) {
            CustomItem itemInHand = new CustomItem(damager.getInventory().getItemInHand());
            double damage = itemInHand.getDamage();
            // If damage is final blow, remove mob from map & his overhead display
            if(target.getHealth() <= damage){
                removeMob(target.getEntity().getUniqueId());
            }
            target.setHealth(Math.max(0, (target.getHealth() - damage)));
            // Update Health Bar
            updateHealthBar(target);
            // Play satisfying ding hit sound
            playDing(damager);
            // Spawn damage indicator
            DamageIndicator.spawn(target.getEntity(),(int)damage);
        }
    }
    private static void dealMagicDamage(Player damager, CustomMob target){
        // Turn mob to red effect
        target.getEntity().damage(0);
        if(isCustomItem(damager.getInventory().getItemInHand())) {
            CustomItem itemInHand = new CustomItem(damager.getInventory().getItemInHand());
            double damage = itemInHand.getMagicDamage();
            // If damage is final blow, remove mob from map & his overhead display
            if(target.getHealth() <= damage){
                removeMob(target.getEntity().getUniqueId());
            }
            target.setHealth(Math.max(0, (target.getHealth() - damage)));

            // Update Health Bar
            updateHealthBar(target);
            // Play satisfying ding hit sound
            playDing(damager);
            // Spawn damage indicator
            DamageIndicator.spawn(target.getEntity(),(int)damage);
        }
    }
    private void dealArrowDamage(Arrow arrow,CustomMob target){
        // Turn mob to red effect
        target.getEntity().damage(0);
        if(arrow.getMetadata("damage").isEmpty()){
            return;
        }
        double damage = arrow.getMetadata("damage").get(0).asInt();
        Player damager = null;
        if (arrow.getShooter() instanceof Player) {
            damager = (Player) arrow.getShooter();
        }
        // If damage is final blow, remove mob from map & his overhead display
        if(target.getHealth() <= damage){
            removeMob(target.getEntity().getUniqueId());
        }
        target.setHealth(Math.max(0, (target.getHealth() - damage)));
        // Update Health Bar
        updateHealthBar(target);
        // Play satisfying ding hit sound
        if(damager != null) playDing(damager);
        // Spawn damage indicator
        DamageIndicator.spawn(target.getEntity(),(int)damage);
    }

    private static void updateHealthBar(CustomMob target){
        int remainingHealth = (int) target.getHealth();
        char healthColor = 'a';
        if(target.getHealth()*2<target.getFullHealth()){
            healthColor = 'e';
        }
        target.getOverheadDisplay().setText(String.format("§8[§7Lv%d§8] §c%s §%c%d§f/§a%d§c❤",target.getLevel(),target.getName(),healthColor,remainingHealth,target.getFullHealth()));
    }
    public static void addMob(CustomMob mob){
        customMobs.put(mob.getEntity().getUniqueId(), mob);
    }
}
