package me.alwayslg.util;

import me.alwayslg.customitems.CustomItem;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;


public class Utilities {
    public static void playSoundToNearbyPlayers(Location armorStandLocation, double radius) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distance(armorStandLocation) <= radius) {
                player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 2.0f, 1f); // Play sound
            }
        }
    }
    public static void playCustomSoundToNearbyPlayers(Location armorStandLocation, double radius , Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distance(armorStandLocation) <= radius) {
                player.playSound(player.getLocation(), sound, 2.0f, 1f); // Play sound
            }
        }
    }
//    public static Boolean damageNearbyEntities(Location location, double damageRadius, Player player) {
//        Collection<Entity> nearbyEntities = location.getWorld().getNearbyEntities(location,damageRadius,damageRadius,damageRadius);
//        for (Entity entity : nearbyEntities) {
//            if (entity instanceof LivingEntity && !(entity instanceof ArmorStand)) {
//                LivingEntity livingEntity = (LivingEntity) entity; //define
//                if (entity != player) { // Ensure the entity is not the player who triggered the event
//                    livingEntity.damage(1); // Deal damage
//
//                    return (true);
//
//                }
//            }break;
//        }return (false);
//    }

    public static AxisAlignedBB getBoundingBox(Entity e){
        // width = e.boundingBox.d - e.boundingBox.a;
        // height = e.boundingBox.e - e.boundingBox.b;
        // length= e.boundingBox.f - e.boundingBox.c;
        return ((CraftEntity) e).getHandle().getBoundingBox();
    }
    public static boolean checkCollisionLocationBoundingBox(Location location, AxisAlignedBB bb){
        return bb.a(new Vec3D(location.getX(), location.getY(), location.getZ()));
    }
    public static boolean checkCollisionBoundingBoxes(AxisAlignedBB bb1, AxisAlignedBB bb2){
        return bb1.b(bb2);
    }

    public static int getItemSlotFromUUID(PlayerInventory playerInventory, UUID uuid){
        for(int i=0;i<playerInventory.getContents().length;i++){
//            ItemStack item = playerInventory.getItem(i);
            if(playerInventory.getItem(i) == null) continue;
            CustomItem customItem = new CustomItem(playerInventory.getItem(i));
            //getStringNBTTagsFromItemStack("uuid",playerInventory.getItem(i)).equals(uuid.toString())
            if(customItem.getType()!= Material.AIR && customItem.getUUID().equals(uuid)){
                return i;
            }
        }
        return -1;
    }
    public static void playerWarn(Player player, String message){
        player.sendMessage(ChatColor.RED.toString()+message);
    }
    public static void playerSuccess(Player player, String message){
        player.sendMessage(ChatColor.GREEN.toString()+message);
    }
    public static String numberFormatComma(int number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

}
