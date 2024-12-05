package me.alwayslg.customitems;

import me.alwayslg.util.Utilities;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

import static me.alwayslg.custommobs.DamageHandler.dealMagicDamageNearbyEntities;

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
    private HashMap<Player, Long> cooldowns = new HashMap<>(); // Store cooldowns
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && isCustomItem(item)) {
                CustomItem customItem = new CustomItem(item);
                if (customItem.getID().equals(CustomItemID.GIANTS_SWORD.getID()) && event.getAction().toString().contains("RIGHT")) {
                    event.setCancelled(true);
                    if (cooldowns.containsKey(player)) {
                        long lastUsed = cooldowns.get(player);
                        long currentTime = System.currentTimeMillis();

                        // If the cooldown is still active
                        long cooldown = 1;
                        if (currentTime - lastUsed < cooldown) { // 1000 milliseconds = 1 second
                            player.sendMessage("You must wait " + ((currentTime - lastUsed) -cooldown)/-1000 +" seconds before using this again!");
                            return; // Exit the method if on cooldown
                        }

                    }




                    dealMagicDamageNearbyEntities(player.getLocation(),8,player);
                    Utilities.playCustomSoundToNearbyPlayers(player.getLocation(),20, Sound.ANVIL_USE);
                    cooldowns.put(player, System.currentTimeMillis());

                }
            }
        }
    }
}
