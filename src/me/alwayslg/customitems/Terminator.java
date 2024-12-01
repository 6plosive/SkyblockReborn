package me.alwayslg.customitems;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Terminator extends CustomItem implements Listener {

    public Terminator() {
        setMaterial(Material.BOW);
        setRarity(Rarity.LEGENDARY);
        setItemType(ItemType.SHORTBOW);

        setName("Terminator");
        setDamage(20);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().equals("Terminator")) {
                // Shoot 3 arrows in a fan pattern
                for (int i = -1; i <= 1; i++) {
                    // Calculate the direction for each arrow
                    double angleOffset = i * 0.2; // Adjust for spread
                    Vector direction = player.getLocation().getDirection();

                    // Calculate new direction with a slight spread
                    Vector arrowDirection = new Vector(
                            direction.getX() + Math.sin(angleOffset), // Sideways offset
                            direction.getY() + 0.2, // Slight upward offset
                            direction.getZ() + Math.cos(angleOffset) // Forward offset
                    ).normalize(); // Normalize the direction

                    Arrow arrow = player.getWorld().spawnArrow(
                            player.getEyeLocation(),
                            arrowDirection,
                            4.0f,
                            1.0f
                    );
                    arrow.setShooter(player);
                }
            }
        }
    }
}