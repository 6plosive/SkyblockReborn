package me.alwayslg.customitems;

import me.alwayslg.util.Utilities;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.alwayslg.customitems.CustomItemID.GIANTS_SWORD;
import static me.alwayslg.custommobs.DamageHandler.dealMagicDamageNearbyEntities;
import static me.alwayslg.util.Utilities.playerWarn;

public class GiantsSword extends CustomItem implements Listener{
    public GiantsSword(){
//        setMaterial(Material.BOW);
//        setRarity(Rarity.EPIC);
//        setItemType(ItemType.SHORTBOW);
//
//        setName("JerJer ShortBow");
//        setDamage(10);
        super(CustomItemID.GIANTS_SWORD);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(GIANTS_SWORD.getID())) {
//            event.setCancelled(true);
            //z` Check for cooldown
            if(Cooldown.hasCooldown(customItem.getUUID())){
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }

            dealMagicDamageNearbyEntities(player.getLocation(),8,player);
            Utilities.playSoundAtLocation(player.getLocation(),20, Sound.ANVIL_USE);
            // Set cooldown to 1 because this event triggers twice when crosshair on block somehow...
            Cooldown.addCooldown(customItem.getUUID(),30*20);
        }
    }
}
