package me.alwayslg.util;

import com.mojang.authlib.GameProfile;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import static me.alwayslg.customitems.CustomItem.isCustomItem;


public class Utilities {
    public static ArrayList<Material> passableBlocks = new ArrayList<>(Arrays.asList(
            Material.LONG_GRASS,Material.RAILS,Material.ACTIVATOR_RAIL,Material.POWERED_RAIL,Material.DETECTOR_RAIL,Material.AIR,Material.LADDER,
            Material.LAVA,Material.STATIONARY_LAVA,Material.WATER,Material.STATIONARY_WATER,Material.RED_ROSE,Material.YELLOW_FLOWER,Material.DEAD_BUSH,
            Material.BANNER,Material.SIGN,Material.SIGN_POST,Material.TORCH,Material.SAPLING,Material.DOUBLE_PLANT,Material.STANDING_BANNER,Material.WALL_BANNER,
            Material.BROWN_MUSHROOM,Material.RED_MUSHROOM,Material.REDSTONE_TORCH_ON,Material.REDSTONE_TORCH_OFF,Material.REDSTONE_WIRE,Material.WALL_SIGN,
            Material.TRIPWIRE,Material.TRIPWIRE_HOOK,Material.WOOD_BUTTON,Material.STONE_BUTTON,Material.LEVER,Material.GOLD_PLATE,Material.IRON_PLATE,
            Material.WOOD_PLATE,Material.STONE_PLATE
    ));
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
        location.getWorld().playSound(location, sound, 1.0f, 1f);
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
    /**
     * Creates a skull item stack that uses the given base64-encoded texture
     *
     * @param texture The texture value. Can be found on e.g. https://minecraft-heads.com/custom-heads/
     *                in the "Value" field.
     * @return an ItemStack with this texture.
     */
    public static ItemStack getPlayerHead(String texture) {
        // for non-legacy api versions, use PLAYER_HEAD or LEGACY_SKULL_ITEM instead of SKULL_ITEM.
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    /* This code is compiled against version 1.8.8, but works on any version if you use
       the correct package name. */
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        try {
            String nbtString = String.format(
                    "{SkullOwner:{Id:%s,Properties:{textures:[{Value:\"%s\"}]}}}",
                    serializeUuid(UUID.randomUUID()), texture
            );
            NBTTagCompound nbt = MojangsonParser.parse(nbtString);
            nmsItem.setTag(nbt);
        } catch (Exception e) { // Not catching a more specific exception here because that exception changes across versions
            throw new AssertionError("NBT Tag parsing failed - This should never happen.", e);
        }
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    private static String serializeUuid(UUID uuid) {
        return '"' + uuid.toString() + '"';
//        if (newStorageSystem) {
//            StringBuilder result = new StringBuilder();
//            long msb = uuid.getMostSignificantBits();
//            long lsb = uuid.getLeastSignificantBits();
//            return result.append("[I;")
//                    .append(msb >> 32)
//                    .append(',')
//                    .append(msb & Integer.MAX_VALUE)
//                    .append(',')
//                    .append(lsb >> 32)
//                    .append(',')
//                    .append(lsb & Integer.MAX_VALUE)
//                    .append(']')
//                    .toString();
//        } else {
//            return '"' + uuid.toString() + '"';
//        }
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
