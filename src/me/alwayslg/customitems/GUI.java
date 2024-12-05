package me.alwayslg.customitems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GUI implements Listener {
    private static Inventory inventory;
    public GUI(){
        CustomItem aspectOfTheEnd = new AspectOfTheEnd();
        CustomItem diamondSword = new DiamondSword();
        CustomItem boomerang = new Boomerang();
        CustomItem jerjerShortBow = new JerJerShortBow();
        CustomItem terminator = new Terminator();
        CustomItem bonzostaff = new BonzoStaff();
        CustomItem hyperion = new Hyperion();
        CustomItem giantsword = new GiantsSword();
        CustomItem witherbow = new WitherShortBow();
        inventory = Bukkit.getServer().createInventory(null,54,"Alwayslg's Skyblock Items");
        inventory.addItem(aspectOfTheEnd);
        inventory.addItem(diamondSword);
        inventory.addItem(boomerang);
        inventory.addItem(jerjerShortBow);
        inventory.addItem(terminator);
        inventory.addItem(bonzostaff);
        inventory.addItem(hyperion);
        inventory.addItem(giantsword);
        inventory.addItem(witherbow);
    }

    public static Inventory getInventory(){
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Alwayslg's Skyblock Items")) {
            event.setCancelled(true); // Prevent taking items from the inventory

            Player player = (Player) event.getWhoClicked();
            CustomItem clickedItem = new CustomItem(event.getCurrentItem());

            if (clickedItem != null) {
                // Unstackable + Some items need to change material for example boomerang
                clickedItem.setUUID(UUID.randomUUID());
                // Give the item to the player
                player.getInventory().addItem(clickedItem);
                player.sendMessage("You have received "+clickedItem.getItemMeta().getDisplayName());
            }
        }
    }
}
