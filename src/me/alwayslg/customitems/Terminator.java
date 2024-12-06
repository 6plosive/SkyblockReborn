package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

import static me.alwayslg.customitems.CustomItemID.JER_JER_SHORTBOW;
import static me.alwayslg.customitems.CustomItemID.TERMINATOR;
import static me.alwayslg.custommobs.DamageHandler.dealRealDamageNearbyEntities;
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