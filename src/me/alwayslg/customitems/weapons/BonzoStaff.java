package me.alwayslg.customitems.weapons;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.Cooldown;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import me.alwayslg.util.CustomHeads;
import me.alwayslg.util.InstantFirework;
import me.alwayslg.util.Utilities;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.custommobs.DamageHandler.dealMagicDamageNearbyEntities;
import static me.alwayslg.util.CustomHeads.*;
import static me.alwayslg.util.Utilities.*;

public class BonzoStaff extends CustomItem implements Listener { //hi alwayslg is gay im not UR RR R URU RU UR OGMMGMGM u stupid niggerfuck u
//    private int damage = 69;
    public BonzoStaff() {
        super(CustomItemID.BONZO_STAFF);
    }
//    private HashMap<Player, Long> cooldowns = new HashMap<>(); // Store cooldowns
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        // Check if the player right-clicked and if they are holding the sword
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(event.getItem()))return;
        CustomItem customItem = new CustomItem(event.getItem());
//        player.sendMessage(event.getAction().toString());
        if (event.getAction().toString().contains("RIGHT") && Objects.equals(customItem.getID(), CustomItemID.BONZO_STAFF.getID())) {
            event.setCancelled(true);
//            player.sendMessage("You used the item!");
            // Check if the player is on cooldown
            if(Cooldown.hasCooldown(customItem.getUUID())) {
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }else {
                spawnMovingArmorStand(player);
                Cooldown.addCooldown(customItem.getUUID(), 2); // Set the cooldown to 2 ticks (0.1 second)
            }

        }
    }
//    private void applyKnockback(Player player, Location explosionLocation) {
//        Vector direction = player.getLocation().toVector().subtract(explosionLocation.toVector()).normalize(); // Calculate direction away from explosion
//        player.setVelocity(direction.multiply(3)); // Adjust the multiplier for knockback strength
//    }
//
//
    private void spawnMovingArmorStand(Player player) {
        // Distance settings
        final double travelDistance = 150; // Distance to travel
        final double speed = 1; // Movement speed
        final double damageRadius = 0.5; // Radius of damage

        // Create the armor stand at the player's eye location
        Location damageLocation = player.getEyeLocation().add(0,-0.5,0);
        //Armorstand location is armorstand's eye location - player's eye location
        Location armorStandLocation = damageLocation.clone().add(0,-1.7,0);
        ArmorStand armorStand = player.getWorld().spawn(armorStandLocation, ArmorStand.class);
        final Vector direction = player.getEyeLocation().getDirection().normalize().multiply(speed); // Get the direction the player is looking

        // Set armor stand properties
        armorStand.setVisible(true);
        armorStand.setGravity(false);
//        armorStand.setSmall(true);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false); // Prevents removal when far away
        armorStand.setCanPickupItems(false); // Prevent item pickup
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        // Set a random balloon as the helmet
        List<ItemStack> balloons = Arrays.asList(
                RED_BALLOON.getItem(),ORANGE_BALLOON.getItem(),
                YELLOW_BALLOON.getItem(),GREEN_BALLOON.getItem(),
                LIME_BALLOON.getItem(),AQUA_BALLOON.getItem(),
                BLUE_BALLOON.getItem(),PURPLE_BALLOON.getItem()
        );
        // generate a random number between 0 and 7
        int randomIndex = (int) (Math.random() * balloons.size());
        ItemStack skull = balloons.get(randomIndex);
        armorStand.setHelmet(skull);


//        final double damageRadius = 1;
//        final double damageAmount = getDamage();


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
                damageLocation.add(direction);
                //Armorstand location is player's eye location - armor stand eye location
                armorStandLocation.add(direction).setYaw(armorStandLocation.getYaw()+20);
                armorStand.teleport(armorStandLocation); // Move the armor stand
                //Add rotation
//                Location prev = armorStand.getLocation();
//                prev.setYaw(prev.getYaw() + 15);
//                armorStand.teleport(prev);

                // debug message send player damage location y level and armorstandlocation y level
//                player.sendMessage(String.format("Damage Location: %f, Armor Stand Location: %f", damageLocation.getY(), armorStandLocation.getY()));

                // Update the distance traveled
                distanceTraveled += speed;

                // Check if the armor stand has traveled the specified distance
                if (distanceTraveled >= travelDistance) {
                    armorStand.remove();
                    //Spawn firework explosion and sparkles
                    FireworkEffect effect = FireworkEffect.builder()
                            .with(FireworkEffect.Type.BURST)
                            .withFlicker()
                            .withColor(InstantFirework.randomColor())
                            .withColor(InstantFirework.randomColor())
                            .build();
                    new InstantFirework(effect, damageLocation);
                    // bounce player the blast direction if player is within 2 blocks
                    if(player.getLocation().distance(damageLocation)<=2){
                        Vector bounceVelocity = player.getLocation().subtract(damageLocation).toVector().setY(0.00000005).normalize().multiply(1.5).setY(0.5);
                        player.setVelocity(bounceVelocity);
                    }
                    cancel();
                    return;
                }

                // Check if the armor stand is colliding with a block
                if(!passableBlocks.contains(damageLocation.getBlock().getType())){
                    armorStand.remove();
                    //Spawn firework explosion and sparkles
                    FireworkEffect effect = FireworkEffect.builder()
                            .with(FireworkEffect.Type.BURST)
                            .withFlicker()
                            .withColor(InstantFirework.randomColor())
                            .withColor(InstantFirework.randomColor())
                            .build();
                    new InstantFirework(effect, damageLocation);
                    //deal 2x radius damage when hitting a block
                    dealMagicDamageNearbyEntities(damageLocation, damageRadius*2, player);
                    // bounce player the blast direction if player is within 2 blocks
//                    player.sendMessage("DISTANCE "+player.getLocation().distance(damageLocation));
                    if(player.getLocation().distance(damageLocation)<=2){
                        Vector bounceVelocity = player.getLocation().subtract(damageLocation).toVector().setY(0.00000005).normalize().multiply(1.5).setY(0.5);
                        player.setVelocity(bounceVelocity);
                    }
                    cancel();
                    return;
                }
                // Check if the armor stand radius 1 is colliding with a custom mob
                if(dealMagicDamageNearbyEntities(damageLocation, damageRadius, player)){
                    armorStand.remove();
                    //Spawn firework explosion and sparkles
                    FireworkEffect effect = FireworkEffect.builder()
                            .with(FireworkEffect.Type.BURST)
                            .withFlicker()
                            .withColor(InstantFirework.randomColor())
                            .withColor(InstantFirework.randomColor())
                            .build();
                    new InstantFirework(effect, damageLocation);
                    // bounce player the blast direction if player is within 2 blocks
                    if(player.getLocation().distance(damageLocation)<=2){
                        Vector bounceVelocity = player.getLocation().subtract(damageLocation).toVector().setY(0.00000005).normalize().multiply(1.5).setY(0.5);
                        player.setVelocity(bounceVelocity);
                    }
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(SkyblockReborn.getInstance(), 0, 1); // Run every tick

    }

}



