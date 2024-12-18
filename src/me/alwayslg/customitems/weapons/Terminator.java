package me.alwayslg.customitems.weapons;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.Cooldown;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import me.alwayslg.customitems.CustomWeapon;
import me.alwayslg.custommobs.Damage;
import me.alwayslg.custommobs.DamageHandler;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
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

public class Terminator extends CustomWeapon implements Listener {

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
        if (!isCustomWeapon(item)) return;
        CustomWeapon customItem = new CustomWeapon(item);
        if ((event.getAction().toString().contains("RIGHT") || event.getAction().toString().contains("LEFT")) && customItem.getID().equals(TERMINATOR.getID())) {
            event.setCancelled(true);
            // Check for cooldown
            if (Cooldown.hasCooldown(customItem.getUUID())) {
                playerWarn(player, "You must wait before using this again!");
                return;
            }

            CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(player.getUniqueId());

            // Shoot 3 arrows in a fan pattern
            Location arrowLocation = player.getEyeLocation();
            Location arrow2Location = player.getEyeLocation();
            Location arrow3Location = player.getEyeLocation();
            arrow2Location.setYaw(arrowLocation.getYaw() - 5);
            arrow3Location.setYaw(arrowLocation.getYaw() + 5);

            Arrow arrow = player.getWorld().spawnArrow(arrowLocation, arrowLocation.getDirection(), 4.0f, 1.0f);
            Arrow arrow2 = player.getWorld().spawnArrow(arrow2Location, arrow2Location.getDirection(), 4.0f, 1.0f);
            Arrow arrow3 = player.getWorld().spawnArrow(arrow3Location, arrow3Location.getDirection(), 4.0f, 1.0f);

            Damage arrowDamage = DamageHandler.calculateDamage(getDamage(),getStrength(),customPlayer.getStatsManager().getCritChance(),customPlayer.getStatsManager().getCritDamage());
            Damage arrow2Damage = DamageHandler.calculateDamage(getDamage(),getStrength(),customPlayer.getStatsManager().getCritChance(),customPlayer.getStatsManager().getCritDamage());
            Damage arrow3Damage = DamageHandler.calculateDamage(getDamage(),getStrength(),customPlayer.getStatsManager().getCritChance(),customPlayer.getStatsManager().getCritDamage());

            arrow.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), arrowDamage.finalDamage));
            arrow2.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), arrow2Damage.finalDamage));
            arrow3.setMetadata("damage", new FixedMetadataValue(SkyblockReborn.getInstance(), arrow3Damage.finalDamage));
            arrow.setMetadata("iscrit", new FixedMetadataValue(SkyblockReborn.getInstance(), arrowDamage.isCrit));
            arrow2.setMetadata("iscrit", new FixedMetadataValue(SkyblockReborn.getInstance(), arrow2Damage.isCrit));
            arrow3.setMetadata("iscrit", new FixedMetadataValue(SkyblockReborn.getInstance(), arrow3Damage.isCrit));

            arrow.setShooter(player);
            arrow2.setShooter(player);
            arrow3.setShooter(player);


            Cooldown.addCooldown(customItem.getUUID(), 6);
        }


    }





}