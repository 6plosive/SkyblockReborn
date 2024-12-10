package me.alwayslg.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static me.alwayslg.ui.UIItemStack.*;

public class AnvilUI implements Listener {
    public static void open(Player player){
        Inventory inventory = Bukkit.createInventory(null,54,"Anvil");
        //bottom bar fill with red background & Close barrier
        for(int i=45;i<=53;i++){
            if(i==49)inventory.setItem(i,CLOSE_BARRIER.getItem());
            else inventory.setItem(i,RED_BACKGROUND.getItem());
        }
        List<Integer> anvilLeftSlots = Arrays.asList(11,12,20);
        List<Integer> anvilRightSlots = Arrays.asList(14,15,24);
        List<Integer> anvilBackgroundSlots = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,16,17,18,19,21,23,25,26,27,28,30,31,32,34,35,36,37,38,39,40,41,42,43,44);
        for(int anvilLeftSlot:anvilLeftSlots){
            inventory.setItem(anvilLeftSlot, ANVIL_LEFT_RED.getItem());
        }
        for(int anvilRightSlot:anvilRightSlots){
            inventory.setItem(anvilRightSlot, ANVIL_RIGHT_RED.getItem());
        }
        for(int anvilBackgroundSlot:anvilBackgroundSlots){
            inventory.setItem(anvilBackgroundSlot, DARK_GRAY_BACKGROUND.getItem());
        }
        inventory.setItem(13,ANVIL_RESULT_BARRIER.getItem());
        inventory.setItem(22,ANVIL_BUTTON.getItem());

        player.openInventory(inventory);
    }
    @EventHandler
    public void onUIClick(InventoryClickEvent event){
//        Bukkit.broadcastMessage("view title:"+event.getView().getTitle());
//        Bukkit.broadcastMessage("clicked title"+event.getClickedInventory().getTitle());
        if(event.getWhoClicked() instanceof Player) {
            if (event.getClickedInventory().getTitle() == "Anvil") {
                Player player = (Player) event.getWhoClicked();
                Inventory inventory = event.getClickedInventory();
                int clickedSlot = event.getSlot();
                ItemStack clickedItem = event.getCurrentItem();
//                player.sendMessage("inventory name:"+inventory.getTitle()+"|clickedslot:"+clickedSlot+"|clickeditem:"+clickedItem.getItemMeta().getDisplayName());
                player.sendMessage("inventory name:"+inventory.getTitle()+"|clickedslot:"+clickedSlot);
                if(clickedItem.hasItemMeta()){
                    player.sendMessage("clickeditem:"+clickedItem.getItemMeta().getDisplayName());
                }else{
                    player.sendMessage("No item meta");
                }
                if (clickedSlot == 13) {
                    //Anvil result
                    if (clickedItem.getType() == ANVIL_RESULT_BARRIER.getItem().getType()) {
                        event.setCancelled(true);
                    } else {
                        //actual result item
                    }
                } else if (clickedSlot == 29 || clickedSlot == 33) {
                    //Anvil left input / right input
                } else {
                    //Background, button, close button
                    event.setCancelled(true);
                    if (clickedSlot == 22) {
                        //Anvil action button
                    } else if (clickedSlot == 49) {
                        //Anvil close button
                    }
                }
            }
        }
    }
    @EventHandler
    public void onUIDEBUGClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("event.getSlotType():"+event.getSlotType());
        player.sendMessage("event.getSlot():"+event.getSlot());
        player.sendMessage("event.getRawSlot():"+event.getRawSlot());
        player.sendMessage("event.getClickedInventory().getTitle():"+event.getClickedInventory().getTitle());
        player.sendMessage("event.getHotbarButton():"+event.getHotbarButton());
        player.sendMessage("event.getAction():"+event.getAction());
        if(event.getCurrentItem().getItemMeta()!=null) {
            player.sendMessage("event.getCurrentItem().getItemMeta().getDisplayName():"+event.getCurrentItem().getItemMeta().getDisplayName());
        }
//        event.

    }

}
