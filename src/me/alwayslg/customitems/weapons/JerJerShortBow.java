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

public class JerJerShortBow extends CustomWeapon implements Listener {
    public JerJerShortBow(){
        super(CustomItemID.JER_JER_SHORTBOW);
    }
    private HashMap<Player, Long> cooldowns = new HashMap<>(); // Store cooldowns
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomWeapon(item)) return;
        CustomWeapon customItem = new CustomWeapon(item);
        if((event.getAction().toString().contains("RIGHT") || event.getAction().toString().contains("LEFT")) && customItem.getID().equals(JER_JER_SHORTBOW.getID())) {
            // Check for cooldown
            if(Cooldown.hasCooldown(customItem.getUUID())){
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }
            event.setCancelled(true);
            CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(player.getUniqueId());

            Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);
            Damage arrowDamage = DamageHandler.calculateDamage(customItem,customPlayer);
            arrow.setMetadata("damage",new FixedMetadataValue(SkyblockReborn.getInstance(),arrowDamage.finalDamage));
            arrow.setMetadata("iscrit",new FixedMetadataValue(SkyblockReborn.getInstance(),arrowDamage.isCrit));

            arrow.setShooter(player);

            Cooldown.addCooldown(customItem.getUUID(),10);// 0.5 seconds
        }
    }
}
