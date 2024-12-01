package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
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

        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().equals("Niggerang")) {
                spawnMovingArmorStand(player);
            }
        }
    }

    private void spawnMovingArmorStand(Player player) {
        // Create the armor stand at the player's eye location
        Location location = player.getEyeLocation();
        ArmorStand armorStand = player.getWorld().spawn(location, ArmorStand.class);

        // Set armor stand properties
        armorStand.setVisible(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false); // Prevents removal when far away
        armorStand.setCanPickupItems(false); // Prevent item pickup
        armorStand.setHelmet(new ItemStack(Material.BONE)); // Ensure no visible helmet

        // Move the armor stand forward in a repeating task
        new BukkitRunnable() {
            @Override
            public void run() {
                if (armorStand.isDead()) {
                    cancel(); // Stop the task if the armor stand is dead
                    return;
                }

                // Move the armor stand forward
                Vector direction = player.getEyeLocation().getDirection();
                Location newLocation = armorStand.getLocation().add(direction.multiply(0.5)); // Adjust speed here
                armorStand.teleport(newLocation); // Use teleport to move the armor stand
            }
        }.runTaskTimer(SkyblockReborn.getInstance(), 0, 1); // Run every tick
    }
}