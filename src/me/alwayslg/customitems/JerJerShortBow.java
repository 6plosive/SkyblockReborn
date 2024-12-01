package me.alwayslg.customitems;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JerJerShortBow extends CustomItem implements Listener {
    public JerJerShortBow(){
        setMaterial(Material.BOW);
        setRarity(Rarity.EPIC);
        setItemType(ItemType.SHORTBOW);

        setName("JerJer ShortBow");
        setDamage(10);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().contains("JerJer ShortBow")) {
                Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 2.0f, 12.0f);
                arrow.setShooter(player);


            }
        }
    }
}
