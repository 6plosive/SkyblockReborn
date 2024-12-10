package me.alwayslg.customitems.weapons;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.Cooldown;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.UUID;

import static me.alwayslg.custommobs.DamageHandler.dealRealDamageNearbyEntities;
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
        if(heldItemSlot==-1)return;

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

        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(CustomItemID.BOOMERANG.getID())){
            event.setCancelled(true);
            Boomerang boomerang = new Boomerang(customItem);
            if (!boomerang.getThrown()) { //Have not thrown
//                Bukkit.broadcastMessage("Right clicked my boner");
                //event.setCancelled(true);
                setThrown(true, player.getInventory(), player.getInventory().getHeldItemSlot());
                spawnMovingArmorStand(player, customItem.getUUID());
                // Set cooldown which acts as a timer. Just for the event if the armorstand returned and the bone isn't in player's inventory.
                // In which it will set thrown to false if after 5 seconds if the bone still haven't returned to player's inventory.
                // Cooldown does NOT apply when boomerang is not thrown. Since its usage is just a timer, not a real cooldown.
                Cooldown.addCooldown(boomerang.getUUID(),100); // 5 second timer
            }else{ //Thrown
                if(!Cooldown.hasCooldown(boomerang.getUUID())){
                    //Boomerang that is thrown, but 5 seconds passed.
                    setThrown(false,player.getInventory(),player.getInventory().getHeldItemSlot());
                }
            }
        }
    }
    private void spawnMovingArmorStand(Player player, UUID uuid) {
        // Create the armor stand at the player's location
        Location damageLocation = player.getEyeLocation().clone();

        //Add correct bone
        Location flyingBoneLocation = damageLocation.clone();// .5 1.6 .5 0d
        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+270); // .5 1.6 .5 270d
        flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.35)); // .15 1.6 .5 270d
        flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-270); // .15 1.6 .5 0d
        flyingBoneLocation.add(0,-0.85,0);// .1 .75 .5 0d

        ArmorStand flyingBone = player.getWorld().spawn(flyingBoneLocation,ArmorStand.class);

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
                dealRealDamageNearbyEntities(damageLocation,1,player);
//                damageEntitiesInLocation(damageLocation,player);
                if (flyingBone.isDead()) {// for IDK what reason
                    flyingBone.remove();
                    int itemSlot = getItemSlotFromUUID(player.getInventory(),CustomItemID.BOOMERANG,uuid);
                    setThrown(false, player.getInventory(), itemSlot);
                    cancel(); // Stop the task if the armor stand is dead
                    return;
                }

                // If returning phase
                if (returning) {
                    // Update direction to the player's current location
                    Location returnLocation = player.getEyeLocation().clone();
                    Vector playerDirection = returnLocation.toVector().subtract(damageLocation.toVector()).normalize();
                    // Move the armor stand back toward the player
                    damageLocation.add(playerDirection.multiply(speed)); // Move back

                    //Add rotation
                    damageLocation.setYaw(damageLocation.getYaw()+30);

                    //Add correct bone
                    Location flyingBoneLocation = damageLocation.clone();// .5 1.6 .5 0d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()+270); // .5 1.6 .5 270d
                    flyingBoneLocation.add(flyingBoneLocation.getDirection().normalize().multiply(0.35)); // .15 1.6 .5 270d
                    flyingBoneLocation.setYaw(flyingBoneLocation.getYaw()-270); // .15 1.6 .5 0d
                    flyingBoneLocation.add(0,-0.85,0);// .1 .75 .5 0d

                    flyingBone.teleport(flyingBoneLocation);
                    distanceTraveled += speed;
                    // Check if the armor stand has reached the player
                    if (damageLocation.distance(returnLocation) < 0.5) {

                        flyingBone.remove();

                        int itemSlot = getItemSlotFromUUID(player.getInventory(),CustomItemID.BOOMERANG,uuid);
                        setThrown(false, player.getInventory(), itemSlot);

                        cancel(); // Stop the task
                        return;
                    }
                    if (distanceTraveled>30){

                        flyingBone.remove();

                        int itemSlot = getItemSlotFromUUID(player.getInventory(),CustomItemID.BOOMERANG,uuid);
                        setThrown(false, player.getInventory(), itemSlot);

                        cancel();
                        return;
                    }
                } else {
                    // Move the armor stand forward
                    damageLocation.add(direction.clone().multiply(speed)); // Move forward

                    //Add rotation
                    damageLocation.setYaw(damageLocation.getYaw()+30);

                    //Add correct bone
                    Location flyingBoneLocation = damageLocation.clone();// .5 1.6 .5 0d
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



