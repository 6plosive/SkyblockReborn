package me.alwayslg.ui;

import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.armors.LapisArmorBoots;
import me.alwayslg.customitems.armors.LapisArmorChestplate;
import me.alwayslg.customitems.armors.LapisArmorHelmet;
import me.alwayslg.customitems.armors.LapisArmorLeggings;
import me.alwayslg.customitems.consumable.HotPotatoBook;
import me.alwayslg.customitems.unique.SkyblockMenu;
import me.alwayslg.customitems.weapons.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class AdminItemUI implements Listener {
    private static Inventory inventory;
    public AdminItemUI(){
        CustomItem aspectOfTheEnd = new AspectOfTheEnd();
        CustomItem diamondSword = new DiamondSword();
        CustomItem boomerang = new Boomerang();
        CustomItem jerjerShortBow = new JerJerShortBow();
        CustomItem terminator = new Terminator();
        CustomItem bonzostaff = new BonzoStaff();
        CustomItem hyperion = new Hyperion();
        CustomItem giantsword = new GiantsSword();
        CustomItem witherbow = new WitherShortBow();
        CustomItem skyblockMenu = new SkyblockMenu();
        CustomItem sigmaSkibidiSword = new SigmaSkibidiSword();
        CustomItem hotPotatoBook = new HotPotatoBook();
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
        inventory.addItem(skyblockMenu);
        inventory.addItem(sigmaSkibidiSword);
        inventory.addItem(hotPotatoBook);
        inventory.addItem(new LapisArmorHelmet());
        inventory.addItem(new LapisArmorChestplate());
        inventory.addItem(new LapisArmorLeggings());
        inventory.addItem(new LapisArmorBoots());

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
