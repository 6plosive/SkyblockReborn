package me.alwayslg.customitems.unique;

import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.alwayslg.customitems.CustomItemID.*;
import static me.alwayslg.util.Utilities.getPlayerHead;

public class SkyblockMenu extends CustomItem implements Listener {
    public SkyblockMenu(){
        super(CustomItemID.SKYBLOCK_MENU);
    }

    private static Inventory createSkyblockMenuUI(CustomPlayer customPlayer){
        Inventory inventory = Bukkit.getServer().createInventory(null,54,"Skyblock Menu");

        ItemStack profileItem = getPlayerHead(customPlayer.getPlayer());
        inventory.setItem(13,profileItem);
        return inventory;
    }
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        // Check if the player right/left-clicked and if they are holding the sword
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(customItem.getID().equals(SKYBLOCK_MENU.getID())) {
            event.setCancelled(true);
            Inventory inventory = createSkyblockMenuUI(CustomPlayerManager.getCustomPlayer(player.getUniqueId()));
            player.openInventory(inventory);
        }
    }
    @EventHandler
    public void onPlayerGUIClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getSlotType() == InventoryType.SlotType.QUICKBAR &&(event.getSlot() == 8 || event.getHotbarButton() == 8)){
            event.setCancelled(true);
            Inventory inventory = createSkyblockMenuUI(CustomPlayerManager.getCustomPlayer(player.getUniqueId()));
            player.openInventory(inventory);
        }
    }
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        // Check if the player right/left-clicked and if they are holding the sword
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(customItem.getID().equals(SKYBLOCK_MENU.getID())) {
            event.setCancelled(true);
            Inventory inventory = createSkyblockMenuUI(CustomPlayerManager.getCustomPlayer(player.getUniqueId()));
            player.openInventory(inventory);
        }
    }
}
