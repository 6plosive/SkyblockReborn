package me.alwayslg.customitems;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Terminator extends CustomItem implements Listener {
    public Terminator(){
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
            if (meta != null && meta.getDisplayName().contains("Terminator")) {
                for (int i = 0; i < 3; i++) {
                    Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);
                    arrow.setShooter(player);

                    // Optional: Add some spread for the arrows
                    arrow.setVelocity(arrow.getVelocity().add(player.getLocation().getDirection().multiply(0.1 * (i - 1))));
                }


            }
        }
    }
}
