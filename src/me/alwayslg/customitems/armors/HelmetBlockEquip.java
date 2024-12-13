package me.alwayslg.customitems.armors;

import me.alwayslg.customitems.CustomArmor;
import me.alwayslg.customitems.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import static me.alwayslg.customitems.CustomArmor.isHelmet;

public class HelmetBlockEquip implements Listener {
    // Equip helmet on head if player clicks on helmet slot in his inventory and the current item is a custom helmet
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //Helmet slot is 5
        if(event.getSlotType()==InventoryType.SlotType.ARMOR && event.getSlot()==39){
            ItemStack item = event.getCursor();
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("Clicked on helmet slot");
            if(isHelmet(item)){
                player.getInventory().setHelmet(item);
                event.setCurrentItem(null);
            }

//            event.setCancelled(true);
        }
//        event.getSlotType();
//        event.
//        if (event.getView().getTitle().equals("Alwayslg's Skyblock Items")) {
//            event.setCancelled(true); // Prevent taking items from the inventory
//
//            Player player = (Player) event.getWhoClicked();
//            CustomItem clickedItem = new CustomItem(event.getCurrentItem());
//
//            if (clickedItem != null) {
//                if (clickedItem instanceof CustomArmor) {
//                    CustomArmor customArmor = (CustomArmor) clickedItem;
//                    if (customArmor.isHelmet()) {
//                        player.getInventory().setHelmet(customArmor);
//                    }
//                }
//            }
//        }
    }

}
