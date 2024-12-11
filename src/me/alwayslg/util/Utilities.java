package me.alwayslg.util;

import com.mojang.authlib.GameProfile;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

import static me.alwayslg.customitems.CustomItem.isCustomItem;


public class Utilities {
    public static void playSoundToNearbyPlayers(Location armorStandLocation, double radius) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distance(armorStandLocation) <= radius) {
                player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 2.0f, 1f); // Play sound
            }
        }
    }
    public static void playSoundAtLocation(Location location, double radius , Sound sound) {
//        for (Player player : Bukkit.getOnlinePlayers()) {
//            if (player.getLocation().distance(location) <= radius) {
//                player.playSound(player.getLocation(), sound, 2.0f, 1f); // Play sound
//            }
//        }
        location.getWorld().playSound(location, sound, 2.0f, 1f);
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

    public static int getItemSlotFromUUID(PlayerInventory playerInventory, CustomItemID customItemID, UUID uuid){
        for(int i=0;i<playerInventory.getContents().length;i++){
//            ItemStack item = playerInventory.getItem(i);
            if(playerInventory.getItem(i) == null) continue;
            if(!isCustomItem(playerInventory.getItem(i))) continue; //not custom item
            CustomItem customItem = new CustomItem(playerInventory.getItem(i));
            //say customitem name, custom item slot, custom item uuid, uuid searching for
//            Bukkit.broadcastMessage((customItem.getID().equals(customItemID.getID()))+" "+i+" "+(customItem.getUUID().equals(uuid)));
//            if(i==0){
//                //debug info
//                Bukkit.broadcastMessage("'"+customItem.getID()+"'");
//                Bukkit.broadcastMessage("'"+customItemID.getID()+"'");
//                Bukkit.broadcastMessage("'"+customItem.getUUID().toString()+"'");
//                Bukkit.broadcastMessage("'"+uuid.toString()+"'");
//            }
            if(!customItem.getID().equals(customItemID.getID())) continue;
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
    public static String numberFormatComma(long number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
    public static ItemStack getPlayerHead(Player player) { // Thank you claude!
        GameProfile profile = ((CraftPlayer)player).getProfile();
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);
        return head;
    }
    public static ItemStack setNBTTags(ItemStack item, String s, NBTBase nbtBase){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbtTagCompound = (nmsItemStack.hasTag()) ? nmsItemStack.getTag() : new NBTTagCompound();
        nbtTagCompound.set(s,nbtBase);
        nmsItemStack.setTag(nbtTagCompound);
        ItemStack updatedItemStack = CraftItemStack.asBukkitCopy(nmsItemStack);
        item.setItemMeta(updatedItemStack.getItemMeta()); // Update the meta
        return item;
    }

}
