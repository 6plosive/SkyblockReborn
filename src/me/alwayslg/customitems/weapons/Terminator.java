package me.alwayslg.customitems.weapons;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.Cooldown;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Arrow;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import static me.alwayslg.customitems.CustomItemID.TERMINATOR;
import static me.alwayslg.util.Utilities.playerWarn;

public class Terminator extends CustomItem implements Listener {

    public Terminator() {
        super(CustomItemID.TERMINATOR);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        if (!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if ((event.getAction().toString().contains("RIGHT") || event.getAction().toString().contains("LEFT")) && customItem.getID().equals(TERMINATOR.getID())) {
            event.setCancelled(true);
            // Check for cooldown
            if (Cooldown.hasCooldown(customItem.getUUID())) {
                playerWarn(player, "You must wait before using this again!");
                return;
            }

            // Shoot 3 arrows in a fan pattern
            Location arrowLocation = player.getEyeLocation();
            Location arrow2Location = player.getEyeLocation();
            Location arrow3Location = player.getEyeLocation();
            arrow2Location.setYaw(arrowLocation.getYaw() - 5);
            arrow3Location.setYaw(arrowLocation.getYaw() + 5);

            Arrow arrow = player.getWorld().spawnArrow(arrowLocation, arrowLocation.getDirection(), 4.0f, 1.0f);
            Arrow arrow2 = player.getWorld().spawnArrow(arrow2Location, arrow2Location.getDirection(), 4.0f, 1.0f);
            Arrow arrow3 = player.getWorld().spawnArrow(arrow3Location, arrow3Location.getDirection(), 4.0f, 1.0f);

            arrow.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), customItem.getDamage()));
            arrow2.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), customItem.getDamage()));
            arrow3.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), customItem.getDamage()));

            arrow.setShooter(player);
            arrow2.setShooter(player);
            arrow3.setShooter(player);


            Cooldown.addCooldown(customItem.getUUID(), 6);
        }


    }





}