package me.alwayslg.customitems.weapons;

import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import org.bukkit.event.Listener;

public class BonzoStaff extends CustomItem implements Listener { //hi alwayslg is gay im not UR RR R URU RU UR OGMMGMGM u stupid niggerfuck u
//    private int damage = 69;
    public BonzoStaff() {
//        setMaterial(Material.BLAZE_ROD);
//        setRarity(Rarity.LEGENDARY);
//        setItemType(ItemType.WAND);
//        setName("Bonzo's Staff"); // Consider renaming for appropriateness
//        setDamage((int) damage);
        super(CustomItemID.BONZO_STAFF);
    }
//    private HashMap<Player, Long> cooldowns = new HashMap<>(); // Store cooldowns
//    @EventHandler
//    public void onPlayerRightClick(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        ItemStack item = event.getItem();
//
//        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
//
//
//            ItemMeta meta = item.getItemMeta();
//            if (meta != null && meta.getDisplayName().contains("Bonzo's Staff")) {
//                event.setCancelled(true);
//                Bukkit.broadcastMessage("NIGGER RIGHT CLICKED");
//                if (cooldowns.containsKey(player)) {
//                    long lastUsed = cooldowns.get(player);
//                    long currentTime = System.currentTimeMillis();
//
//                    // If the cooldown is still active
//                    if (currentTime - lastUsed < 100) { // 1000 milliseconds = 1 second
//                        player.sendMessage("You must wait before using this again!");
//                        return; // Exit the method if on cooldown
//                    }
//                }
//                player.sendMessage("You used the item!");
//                spawnMovingArmorStand(player);
//                cooldowns.put(player, System.currentTimeMillis());
//
//            }
//        }
//    }
//    private void applyKnockback(Player player, Location explosionLocation) {
//        Vector direction = player.getLocation().toVector().subtract(explosionLocation.toVector()).normalize(); // Calculate direction away from explosion
//        player.setVelocity(direction.multiply(3)); // Adjust the multiplier for knockback strength
//    }
//
//
//    private void spawnMovingArmorStand(Player player) {
//        // Create the armor stand at the player's eye location
//        Location initialLocation = player.getLocation();
//        ArmorStand armorStand = player.getWorld().spawn(initialLocation, ArmorStand.class);
//        Vector direction = player.getEyeLocation().getDirection().normalize(); // Get the direction the player is looking
//
//        // Set armor stand properties
//        armorStand.setVisible(true);
//        armorStand.setGravity(false);
//        armorStand.setSmall(true);
//        armorStand.setCustomNameVisible(false);
//        armorStand.setRemoveWhenFarAway(false); // Prevents removal when far away
//        armorStand.setCanPickupItems(false); // Prevent item pickup
//        // Set the player's head as the helmet
//        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
//        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
//        skullMeta.setOwner("Throwing60");
//        skull.setItemMeta(skullMeta);
//        armorStand.setHelmet(skull);
//
//        // Distance settings
//        final double travelDistance = 100; // Distance to travel
//        final double speed = 1; // Movement speed
//        final double damageRadius = 1;
//        final double damageAmount = getDamage();
//
//
//        // Move the armor stand forward in a repeating task
//        new BukkitRunnable() {
//            private double distanceTraveled = 0.0; // Track distance traveled
//
//            @Override
//            public void run() {
//                if (armorStand.isDead()) {
//                    cancel(); // Stop the task if the armor stand is dead
//                    return;
//                }
//
//                // Move the armor stand forward
//                armorStand.teleport(armorStand.getLocation().add(direction.clone().multiply(speed))); // Move the armor stand
//                //Add rotation
//                Location prev = armorStand.getLocation();
//                prev.setYaw(prev.getYaw()+15);
//                armorStand.teleport(prev);
//
//
//                // Update the distance traveled
//                distanceTraveled += speed;
//                if(damageNearbyEntities(armorStand.getLocation(), damageRadius, player)){ // Its boolean so that i can know damaged or not
//                    armorStand.remove();
//                    playSoundToNearbyPlayers(armorStand.getLocation(),20);
//                    cancel();
//                }
//                // Check if the armor stand has traveled the specified distance
//                if (distanceTraveled >= travelDistance) {
//                    armorStand.remove();
//                    playSoundToNearbyPlayers(armorStand.getLocation(),20);// PLAY SOUYND
//                    cancel();
//                }
//
//                //KILL IF THERES BLOCK IN THE WAY
//
//                if (!armorStand.getLocation().add(0, 2, 0).getBlock().isEmpty() &&
//                        !armorStand.getLocation().add(0,2,0).getBlock().isLiquid() &&
//                        !armorStand.getLocation().add(0,2,0).getBlock().getType().equals(Material.GRASS) &&
//                        !armorStand.getLocation().add(0,2,0).getBlock().getType().equals(Material.LONG_GRASS) &&
//                        !armorStand.getLocation().add(0,2,0).getBlock().getType().equals(Material.LAVA)
//                ){
//                    Bukkit.broadcastMessage("aba");
//                    if (player.getLocation().distance(armorStand.getLocation()) <= 5) {
//                            applyKnockback(player, armorStand.getLocation().add(0,0,0));
//                        }
//                    //if (player.getLocation().distance(armorStand.getLocation()) <= 2) {
//                    //    Bukkit.broadcastMessage("asdasdaba");
//                    //    applyKnockback(player, armorStand.getLocation());
//                    //}
//                    armorStand.remove();
//                    playSoundToNearbyPlayers(armorStand.getLocation(),20);
//                    cancel();
//                }
//            }
//        }.runTaskTimer(SkyblockReborn.getInstance(), 0, 1); // Run every tick
//    }
}



