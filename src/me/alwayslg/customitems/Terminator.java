package me.alwayslg.customitems;

import org.bukkit.Bukkit;
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

    public Vector rotateVector(Vector vector, double whatAngle) {
        double sin = Math.sin(whatAngle);
        double cos = Math.cos(whatAngle);
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;

        return vector.setX(x).setZ(z);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().contains("Terminator")) {
                // Shoot 3 arrows in a fan pattern
                // Shoot 3 arrows in a fan pattern
                Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);

                Arrow arrow2 = player.getWorld().spawnArrow(player.getEyeLocation(), rotateVector(player.getLocation().getDirection(),0.1), 4.0f, 1.0f);
                Arrow arrow3 = player.getWorld().spawnArrow(player.getEyeLocation(), rotateVector(player.getLocation().getDirection(),-0.1), 4.0f, 1.0f);
                arrow.setShooter(player);
                arrow2.setShooter(player);
                arrow3.setShooter(player);
            }


                }


    }
}