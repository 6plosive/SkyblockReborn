package me.alwayslg.customitems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI implements Listener {
    private static Inventory inventory;
    public static void init(){
        AspectOfTheEnd.init();

        inventory = Bukkit.getServer().createInventory(null,54,"Alwayslg's Skyblock Items");
        inventory.addItem(AspectOfTheEnd.getItem());
    }

    public static Inventory getInventory(){
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Alwayslg's Skyblock Items")) {
            event.setCancelled(true); // Prevent taking items from the inventory

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                // Give the item to the player
                player.getInventory().addItem(clickedItem);
                player.sendMessage("You have received "+clickedItem.getItemMeta().getDisplayName());
            }
        }
    }
}
