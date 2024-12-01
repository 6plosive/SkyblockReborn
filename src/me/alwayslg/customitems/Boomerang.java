package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.plugin.Plugin;

public class Boomerang extends CustomItem implements Listener {
    public Boomerang() {
        setMaterial(Material.BONE);
        setRarity(Rarity.LEGENDARY);
        setItemType(ItemType.SHORTBOW);

        setName("Niggerang"); // Consider renaming for appropriateness
        setDamage(69);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if ( item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().contains("Niggerang")) {
                Bukkit.broadcastMessage("NIGGER RIGHT CLICKED");
                spawnMovingArmorStand(player);

            }
        }
    }
    private void spawnMovingArmorStand(Player player) {
        // Create the armor stand at the player's eye location
        Location initialLocation = player.getEyeLocation();
        ArmorStand armorStand = player.getWorld().spawn(initialLocation, ArmorStand.class);

        // Set armor stand properties
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false); // Prevents removal when far away
        armorStand.setCanPickupItems(false); // Prevent item pickup
        armorStand.setHelmet(new ItemStack(Material.AIR)); // Ensure no visible helmet

        // Distance settings
        final double travelDistance = 10.0; // Distance to travel
        final double speed = 0.5; // Movement speed
        Vector direction = player.getEyeLocation().getDirection().normalize(); // Get the direction the player is looking

        // Move the armor stand forward in a repeating task
        new BukkitRunnable() {
            private double distanceTraveled = 0.0; // Track distance traveled

            @Override
            public void run() {
                if (armorStand.isDead()) {
                    cancel(); // Stop the task if the armor stand is dead
                    return;
                }

                // Move the armor stand forward
                armorStand.teleport(armorStand.getLocation().add(direction.clone().multiply(speed))); // Move the armor stand

                // Update the distance traveled
                distanceTraveled += speed;

                // Check if the armor stand has traveled the specified distance
                if (distanceTraveled >= travelDistance) {
                    // Return the armor stand to the player
                    armorStand.teleport(player.getLocation());
                    cancel(); // Stop the task
                }
            }
        }.runTaskTimer(SkyblockReborn.getInstance(), 0, 1); // Run every tick
    }
    }



