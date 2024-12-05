package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;

import static me.alwayslg.customitems.CustomItemID.JER_JER_SHORTBOW;
import static me.alwayslg.util.Utilities.playerWarn;

public class WitherShortBow extends CustomItem implements Listener {
    public WitherShortBow(){
        super(CustomItemID.JER_JER_SHORTBOW);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item==null && !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if((event.getAction().toString().contains("RIGHT") || event.getAction().toString().contains("LEFT")) && customItem.getID().equals(JER_JER_SHORTBOW.getID())) {
            // Check for cooldown
            if(Cooldown.hasCooldown(customItem.getUUID())){
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }
            event.setCancelled(true);

            Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);
            arrow.setMetadata("damage",new FixedMetadataValue(SkyblockReborn.getInstance(),customItem.getDamage()));

            arrow.setShooter(player);

            Cooldown.addCooldown(customItem.getUUID(),10);// 0.5 seconds
        }
    }
}
