package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

import static me.alwayslg.custommobs.DamageHandler.damageEntitiesInLocation;
import static me.alwayslg.custommobs.DamageHandler.damageNearbyEntities;
import static me.alwayslg.util.Utilities.getItemSlotFromUUID;

public class Boomerang extends CustomItem implements Listener {
    public Boomerang() {
        super(CustomItemID.BOOMERANG);
        setNBTTags("thrown",new NBTTagByte((byte) 0));
    }
    public Boomerang(CustomItem customItem) {
        super(customItem);
    }
    private void setThrown(boolean isThrown, PlayerInventory playerInventory, int heldItemSlot){
        CustomItem customItem = new CustomItem(playerInventory.getItem(heldItemSlot));
        customItem.setNBTTags("thrown", new NBTTagByte((byte) (isThrown ? 1 : 0 )));
        if(isThrown){
            customItem.setType(Material.GHAST_TEAR);
            playerInventory.setItem(heldItemSlot,customItem);
        }else{
            customItem.setType(Material.BONE);
            playerInventory.setItem(heldItemSlot,customItem);
        }
    }
    private boolean getThrown(){
        return getByteNBTTags("thrown") == 1;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(item==null && !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(CustomItemID.BOOMERANG.getID())){
            event.setCancelled(true);
            Boomerang boomerang = new Boomerang(customItem);
            if (!boomerang.getThrown()) { //Have not thrown
                Bukkit.broadcastMessage("Right clicked my boner");
                //event.setCancelled(true);
                setThrown(true, player.getInventory(), player.getInventory().getHeldItemSlot());
//                        Bukkit.broadcastMessage("bone get material: " + (getMaterial()));
                spawnMovingArmorStand(player, customItem.getUUID());
            }
        }
    }
    private void spawnMovingArmorStand(Player player, UUID uuid) {
        // Create the armor stand at the player's location
        Location initialLocation = player.getLocation().add(0,1.6,0);
        ArmorStand damageBox = player.getWorld().spawn(initialLocation, ArmorStand.class);
        //Add correct bone
        Location flyingBoneLocation = damageBox.getLocation();// .5 1.6 .5 0d
//        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+225); // .5 1.6 .5 225d
//        flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.565685424949));// 0.9 1.6 .1 225d
//        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-225); // 0.9 1.6 .1 0d
//        flyingBoneLocation.add(0,-0.85,0);// .9 .75 .1 0d
        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+270); // .5 1.6 .5 270d
        flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.35)); // .15 1.6 .5 270d
        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-270); // .15 1.6 .5 0d
        flyingBoneLocation.add(0,-0.85,0);// .1 .75 .5 0d

        ArmorStand flyingBone = damageBox.getWorld().spawn(flyingBoneLocation,ArmorStand.class);

        damageBox.setMetadata("owner",new FixedMetadataValue(SkyblockReborn.getInstance(),uuid));
        flyingBone.setMetadata("owner",new FixedMetadataValue(SkyblockReborn.getInstance(),uuid));
        damageBox.setMetadata("player",new FixedMetadataValue(SkyblockReborn.getInstance(),player.getUniqueId().toString()));

        // Set armor stand properties
        damageBox.setMarker(true);
        damageBox.setVisible(false);
        damageBox.setGravity(false);
        damageBox.setCustomNameVisible(false);
        damageBox.setRemoveWhenFarAway(false); // Prevents removal when far away
        damageBox.setCanPickupItems(false); // Prevent item pickup
//        armorStand.setItemInHand(new ItemStack(Material.BONE)); // Set bone as the held item

        flyingBone.setVisible(false);
        flyingBone.setMarker(true);
        flyingBone.setGravity(false);
        flyingBone.setCustomNameVisible(false);
        flyingBone.setRemoveWhenFarAway(false); // Prevents removal when far away
        flyingBone.setCanPickupItems(false); // Prevent item pickup
        flyingBone.setItemInHand(new ItemStack(Material.BONE)); // Set bone as the held item
        flyingBone.setRightArmPose(new EulerAngle(0, 0, 0));

        // Distance settings
        final double travelDistance = 10.0; // Distance to travel
        final double speed = 0.5; // Movement speed
        Vector direction = player.getEyeLocation().getDirection().normalize(); // Get the direction the player is looking

        // Move the armor stand forward in a repeating task
        new BukkitRunnable() {
            private double distanceTraveled = 0.0; // Track distance traveled
            private boolean returning = false; // Track if the armor stand is returning

            @Override
            public void run() {
                if (damageBox.isDead()) {
                    flyingBone.remove();
                    List<MetadataValue> values = damageBox.getMetadata("owner");
                    if (!values.isEmpty()) {
                        UUID ownerUUID = UUID.fromString(values.get(0).asString());
                        Bukkit.broadcastMessage("killed metadata:"+ownerUUID.toString());
                        int itemSlot = getItemSlotFromUUID(player.getInventory(),ownerUUID);
                        setThrown(false, player.getInventory(), itemSlot);
                    }
                    cancel(); // Stop the task if the armor stand is dead
                    return;
                }

                // If returning phase
                if (returning) {
                    // Update direction to the player's current location
                    Location returnLocation = player.getLocation().add(0,1.6,0);
                    Vector playerDirection = returnLocation.toVector().subtract(damageBox.getLocation().toVector()).normalize();
                    // Move the armor stand back toward the player
                    damageBox.teleport(damageBox.getLocation().add(playerDirection.multiply(speed))); // Move back

                    //Add rotation
                    Location prev = damageBox.getLocation();
                    prev.setYaw(prev.getYaw()+30);
                    damageBox.teleport(prev);

                    //Add correct bone
                    Location flyingBoneLocation = damageBox.getLocation();// .5 1.6 .5 0d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+270); // .5 1.6 .5 270d
                    flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.35)); // .15 1.6 .5 270d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-270); // .15 1.6 .5 0d
                    flyingBoneLocation.add(0,-0.85,0);// .1 .75 .5 0d

                    flyingBone.teleport(flyingBoneLocation);
                    distanceTraveled += speed;
                    // Check if the armor stand has reached the player
                    if (damageBox.getLocation().distance(returnLocation) < 0.5) {
//                        damageBox.teleport(player.getLocation()); // Teleport back to the player
                        damageBox.remove(); // Remove the armor stand
                        flyingBone.remove();
                        List<MetadataValue> values = damageBox.getMetadata("owner");
                        if (!values.isEmpty()) {
                            UUID ownerUUID = UUID.fromString(values.get(0).asString());
                            Bukkit.broadcastMessage("killed metadata:"+ownerUUID.toString());
                            int itemSlot = getItemSlotFromUUID(player.getInventory(),ownerUUID);
                            setThrown(false, player.getInventory(), itemSlot);
                        }
                        cancel(); // Stop the task
                        return;
                    }
                    if (distanceTraveled>30){
                        damageBox.remove();
                        flyingBone.remove();
                        List<MetadataValue> values = damageBox.getMetadata("owner");
                        if (!values.isEmpty()) {
                            UUID ownerUUID = UUID.fromString(values.get(0).asString());
                            Bukkit.broadcastMessage("killed metadata:"+ownerUUID.toString());
                            int itemSlot = getItemSlotFromUUID(player.getInventory(),ownerUUID);
                            setThrown(false, player.getInventory(), itemSlot);
                        }
                        cancel();
                        return;
                    }
                } else {
                    // Move the armor stand forward
                    damageBox.teleport(damageBox.getLocation().add(direction.clone().multiply(speed))); // Move forward

                    //Add rotation
                    Location prev = damageBox.getLocation();
                    prev.setYaw(prev.getYaw()+30);
                    damageBox.teleport(prev);

                    //Add correct bone
                    Location flyingBoneLocation = damageBox.getLocation();// .5 1.6 .5 0d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+270); // .5 1.6 .5 270d
                    flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.35)); // .15 1.6 .5 270d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-270); // .15 1.6 .5 0d
                    flyingBoneLocation.add(0,-0.85,0);// .1 .75 .5 0d

                    flyingBone.teleport(flyingBoneLocation);

                    // Update the distance traveled
                    distanceTraveled += speed;

                    // Check if the armor stand has traveled the specified distance
                    if (distanceTraveled >= travelDistance) {
                        returning = true; // Start returning
                        distanceTraveled = 0.0; // Reset distance for return trip
                    }
                }
            }
        }.runTaskTimer(SkyblockReborn.getInstance(), 0, 1); // Run every tick
    }
}



