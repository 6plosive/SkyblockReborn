package me.alwayslg.customitems.armors;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alwayslg.customitems.CustomArmor.isHelmet;

public class HelmetBlockEquip implements Listener {

    // Equip helmet in cursor if player clicks on helmet slot in his inventory and the current item is a custom helmet
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player))return;
        if(event.getClickedInventory()==null)return;
        if(event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack cursorItem = event.getCursor();
        ItemStack currentItem = event.getCurrentItem();
        // Player clicked on helmet slot and its placing an item
        if(event.getSlotType()==InventoryType.SlotType.ARMOR && event.getSlot()==39 && event.getAction().toString().contains("PLACE")){
            if(cursorItem==null || !cursorItem.hasItemMeta()) return;
            ItemMeta meta = cursorItem.getItemMeta();
            if(meta==null) return;
            if(!isHelmet(cursorItem)) return;
            CustomArmor customItem = new CustomArmor(cursorItem);
            new BukkitRunnable() {
                @Override
                public void run() {
                    assert currentItem==null;

                    player.getInventory().setHelmet(customItem);
                    player.setItemOnCursor(null);
                }
            }.runTaskLater(SkyblockReborn.getInstance(), 1);
        }
        //Player shift click helmet inside inventory
        if(event.getClickedInventory().getType()==InventoryType.PLAYER && event.getSlot()>=0 && event.getSlot()<=35 && event.getClick().toString().contains("SHIFT")){
            if(currentItem==null || !currentItem.hasItemMeta()) return;
            ItemMeta meta = currentItem.getItemMeta();
            if(meta==null) return;
            if(!isHelmet(currentItem)) return;
            CustomArmor customItem = new CustomArmor(currentItem);

            event.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    // if player helmet slot is empty
                    if(player.getInventory().getHelmet()==null) {
                        player.getInventory().setHelmet(customItem);
                        event.setCurrentItem(null);
                    }
                }
            }.runTaskLater(SkyblockReborn.getInstance(), 1);
        }
    }

    // Equip helmet outside inventory if player right clicks with a custom helmet
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        // Check if the player right-clicked and if they are holding the sword
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isHelmet(item)) return;
        CustomArmor customItem = new CustomArmor(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getItemType().toString().contains("HELMET")) {
            event.setCancelled(true);
//            player.sendMessage("Equipping helmet: "+item.getItemMeta().getDisplayName());
            // If player head doesnt have a helmet currently
            if(player.getInventory().getHelmet()==null){
                player.getInventory().setHelmet(item);
                player.setItemInHand(null);
                // update health
//                updateHealth(player);
            }
        }
    }


}
