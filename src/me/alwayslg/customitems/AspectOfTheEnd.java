package me.alwayslg.customitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class AspectOfTheEnd extends CustomItem implements Listener {

    public AspectOfTheEnd(){
//        setMaterial(Material.DIAMOND_SWORD);
//        setRarity(Rarity.RARE);
//        setItemType(ItemType.SWORD);
//        setName("Aspect of the End");
//        setID("ASPECT_OF_THE_END");
//        setDamage(100);
        super(CustomItemID.ASPECT_OF_THE_END);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Check if the player right-clicked and if they are holding the sword
        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && isCustomItem(item)) {
                CustomItem customItem = new CustomItem(item);
                if(customItem.getID().equals(CustomItemID.ASPECT_OF_THE_END.getID())) {
//                ArmorStand previousStamd = null;
                    Location previousLocation = null;
                    boolean teleported = false;
//                if(player.getLocation().getPitch()>=0){
//                    player.teleport(player.getLocation().add(0,1,0));
//                }
                    for (int trydistance = 1; trydistance <= 8; trydistance++) {
                        Vector direction = player.getLocation().getDirection().normalize().multiply(trydistance);
                        Location tplocation = player.getLocation().clone();
                        if (player.getLocation().getPitch() >= 0) {
                            tplocation.add(0, 1, 0);
                        }
                        tplocation.add(direction);
//                    ArmorStand armorStand = player.getWorld().spawn(tplocation, ArmorStand.class);
//                    armorStand.setGravity(false); // Disable gravity
//                    armorStand.setVisible(true); // Make the armor stand visible
//                    armorStand.setCustomName(String.format("Distance %d", trydistance)); // Set a custom name (optional)
//                    armorStand.setCustomNameVisible(true); // Make the custom name visible (optional)
                        if (tplocation.getBlock().isEmpty() || tplocation.getBlock().getType().equals(Material.GLASS) || tplocation.getBlock().getType().equals(Material.STAINED_GLASS)) {
                            // It's good! nothing blocking me!
//                        tplocation.getBlock().setType(Material.GLASS);
//                        armorStand.setHelmet(new ItemStack(Material.IRON_HELMET));
                        } else {
                            // BAD! Something is blocking us!
                            if (previousLocation != null) {
                                Location finaltplocation = previousLocation.clone();
                                finaltplocation.setX(previousLocation.getBlockX() + 0.5);
                                finaltplocation.setZ(previousLocation.getBlockZ() + 0.5);
//                            armorStand.remove();
//                            ArmorStand armorStand2 = player.getWorld().spawn(finaltplocation, ArmorStand.class);
//                            armorStand2.setGravity(false); // Disable gravity
//                            armorStand2.setVisible(true); // Make the armor stand visible
//                            armorStand2.setCustomName("Final TP"); // Set a custom name (optional)
//                            armorStand2.setCustomNameVisible(true); // Make the custom name visible (optional)

//                            player.sendMessage("final tped location: "+finaltplocation);
//                            previousStamd.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                                player.teleport(finaltplocation);
                                teleported = true;
                                break;
                            }
                        }
//                    previousStamd=armorStand;
                        previousLocation = tplocation;
                    }
                    if (!teleported) {
                        Location finaltplocation = previousLocation.clone();
                        finaltplocation.setX(previousLocation.getBlockX() + 0.5);
                        finaltplocation.setZ(previousLocation.getBlockZ() + 0.5);
//                    ArmorStand armorStand2 = player.getWorld().spawn(finaltplocation, ArmorStand.class);
//                    armorStand2.setGravity(false); // Disable gravity
//                    armorStand2.setVisible(true); // Make the armor stand visible
//                    armorStand2.setCustomName("Final TP"); // Set a custom name (optional)
//                    armorStand2.setCustomNameVisible(true); // Make the custom name visible (optional)
//                    player.sendMessage("final tped location: "+finaltplocation);
//                    previousStamd.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                        player.teleport(finaltplocation);
                    }
                }
            }
        }
    }
}
