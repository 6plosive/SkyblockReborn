package me.alwayslg.customitems;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class JerJerShortBow extends CustomItem implements Listener {
    public JerJerShortBow(){
        setMaterial(Material.BOW);
        setRarity(Rarity.EPIC);
        setItemType(ItemType.SHORTBOW);

        setName("JerJer ShortBow");
        setDamage(10);
    }
    private HashMap<Player, Long> cooldowns = new HashMap<>(); // Store cooldowns
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();




            if (meta != null && meta.getDisplayName().contains("JerJer ShortBow")) {
                event.setCancelled(true);
                if (cooldowns.containsKey(player)) {
                    long lastUsed = cooldowns.get(player);
                    long currentTime = System.currentTimeMillis();

                    // If the cooldown is still active
                    if (currentTime - lastUsed < 500) { // 1000 milliseconds = 1 second
                        player.sendMessage("You must wait before using this again!");
                        return; // Exit the method if on cooldown
                    }
                }



                Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);
                arrow.setShooter(player);



                cooldowns.put(player, System.currentTimeMillis());

            }
        }
    }
}
