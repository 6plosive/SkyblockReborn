package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
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

public class Boomerang extends CustomItem implements Listener { //hi alwayslg is gay im not UR RR R URU RU UR OGMMGMGM u stupid niggerfuck u
    public Boomerang() {
        setMaterial(Material.BONE);
        setRarity(Rarity.LEGENDARY);
        setItemType(ItemType.DUNGEON_BOW);

        setName("Boner"); // Consider renaming for appropriateness
        setDamage(69);
        setNBTTags("thrown",new NBTTagByte((byte) 0));
        setUUID(UUID.randomUUID());
    }
    private void setThrown(boolean isThrown, PlayerInventory playerInventory, int heldItemSlot){
        setNBTTags("thrown", new NBTTagByte((byte) (isThrown ? 1 : 0 )));
        if(isThrown){
            Bukkit.broadcastMessage("UUID before setitem:"+getStringNBTTagsFromItemStack("uuid",playerInventory.getItem(heldItemSlot)));
            setMaterial(Material.GHAST_TEAR);
            Bukkit.broadcastMessage("UUID between setitem:"+getStringNBTTagsFromItemStack("uuid",playerInventory.getItem(heldItemSlot)));
            Bukkit.broadcastMessage("UUID between 2 setitem:"+getStringNBTTagsFromItemStack("uuid",getItemStack()));
            playerInventory.setItem(heldItemSlot,getItemStack());

            Bukkit.broadcastMessage("UUID after setitem:"+getStringNBTTagsFromItemStack("uuid",playerInventory.getItem(heldItemSlot)));
        }else{
            setMaterial(Material.BONE);
            playerInventory.setItem(heldItemSlot,getItemStack());
        }
    }
    private boolean getThrown(){
        return getByteNBTTags("thrown") == 1;
    }
    private static int getItemSlotFromUUID(PlayerInventory playerInventory,UUID uuid){
        for(int i=0;i<playerInventory.getContents().length;i++){
            if(playerInventory.getItem(i).getType()!=Material.AIR && getStringNBTTagsFromItemStack("uuid",playerInventory.getItem(i)).equals(uuid.toString())){
                return i;
            }
        }
        return -1;
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().contains("Boner")) { //
                event.setCancelled(true);


                if(!getThrown()) { //Have not thrown
                    Bukkit.broadcastMessage("Right clicked my boner");
                    setThrown(true, player.getInventory(),player.getInventory().getHeldItemSlot());
                    Bukkit.broadcastMessage("bone get material: "+(getMaterial()));
                    spawnMovingArmorStand(player);
                }
            }
        }
    }
    private void spawnMovingArmorStand(Player player) {
        // Create the armor stand at the player's eye location
        Location initialLocation = player.getLocation();
        ArmorStand armorStand = player.getWorld().spawn(initialLocation, ArmorStand.class);
        //Add correct bone
        Location armorStand2loc = armorStand.getLocation();// .5 .5 0d
        armorStand2loc.setYaw(armorStand2loc.getYaw()+225); //.5 .5 225d
        Bukkit.broadcastMessage("armorStand2loc.getDirection().normalize(:"+armorStand2loc.getDirection().normalize());
        Bukkit.broadcastMessage("armorStand2loc.getDirection()"+armorStand2loc.getDirection());
        armorStand2loc.add(armorStand2loc.getDirection().normalize().multiply(0.565685424949));//0.9 .1 225d
        armorStand2loc.setYaw(armorStand2loc.getYaw()-225);

        ArmorStand armorStand2 = armorStand.getWorld().spawn(armorStand2loc,ArmorStand.class);
//        MetadataValue mdv;
        armorStand.setMetadata("owner",new FixedMetadataValue(SkyblockReborn.getInstance(),getUUID()));

        // Set armor stand properties
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false); // Prevents removal when far away
        armorStand.setCanPickupItems(false); // Prevent item pickup
//        armorStand.setItemInHand(new ItemStack(Material.BONE)); // Set bone as the held item

        armorStand2.setVisible(true);
        armorStand2.setMarker(true);
        armorStand2.setGravity(false);
        armorStand2.setCustomNameVisible(false);
        armorStand2.setRemoveWhenFarAway(false); // Prevents removal when far away
        armorStand2.setCanPickupItems(false); // Prevent item pickup
        armorStand2.setItemInHand(new ItemStack(Material.BONE)); // Set bone as the held item
        armorStand2.setRightArmPose(new EulerAngle(0, 0, 0));

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
                if (armorStand.isDead()) {
                    List<MetadataValue> values = armorStand.getMetadata("owner");
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
                    Vector playerDirection = player.getLocation().toVector().subtract(armorStand.getLocation().toVector()).normalize();
                    // Move the armor stand back toward the player
                    armorStand.teleport(armorStand.getLocation().add(playerDirection.multiply(speed))); // Move back

                    //Add rotation
                    Location prev = armorStand.getLocation();
                    prev.setYaw(prev.getYaw()+30);
                    armorStand.teleport(prev);

                    //Add correct bone
                    Location armorStand2loc = armorStand.getLocation();// .5 .5 0d
                    armorStand2loc.setYaw(armorStand2loc.getYaw()+225); //.5 .5 225d
                    armorStand2loc.add(armorStand2loc.getDirection().normalize().multiply(0.565685424949));//0.9 .1 225d
                    armorStand2loc.setYaw(armorStand2loc.getYaw()-225);

                    armorStand2.teleport(armorStand2loc);
                    distanceTraveled += speed;
                    // Check if the armor stand has reached the player
                    if (armorStand.getLocation().distance(player.getLocation()) < 0.5) {
                        armorStand.teleport(player.getLocation()); // Teleport back to the player
                        armorStand.remove(); // Remove the armor stand
                        List<MetadataValue> values = armorStand.getMetadata("owner");
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
                        armorStand.remove();
                        List<MetadataValue> values = armorStand.getMetadata("owner");
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
                    armorStand.teleport(armorStand.getLocation().add(direction.clone().multiply(speed))); // Move forward

                    //Add rotation
                    Location prev = armorStand.getLocation();
                    prev.setYaw(prev.getYaw()+30);
                    armorStand.teleport(prev);

                    //Add correct bone
                    Location armorStand2loc = armorStand.getLocation();// .5 .5 0d
                    armorStand2loc.setYaw(armorStand2loc.getYaw()+225); //.5 .5 225d
                    armorStand2loc.add(armorStand2loc.getDirection().normalize().multiply(0.565685424949));//0.9 .1 225d
                    armorStand2loc.setYaw(armorStand2loc.getYaw()-225);

                    armorStand2.teleport(armorStand2loc);

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



