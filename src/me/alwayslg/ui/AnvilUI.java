package me.alwayslg.ui;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.CustomItemID;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.customitems.CustomItem.isCustomItem;
import static me.alwayslg.customitems.CustomItemID.isWeapon;
import static me.alwayslg.ui.UIItemStack.*;
import static me.alwayslg.util.Utilities.playerWarn;

public class AnvilUI implements Listener {
    private static final List<Integer> anvilLeftSlots = Arrays.asList(11,12,20);
    private static final List<Integer> anvilRightSlots = Arrays.asList(14,15,24);
    private static final List<Integer> anvilBackgroundSlots = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,16,17,18,19,21,23,25,26,27,28,30,31,32,34,35,36,37,38,39,40,41,42,43,44);
    private static final List<Integer> anvilBottomBarSlots = Arrays.asList(45,46,47,48,50,51,52,53);
    private static Inventory inventory;
    public static void open(Player player){
        inventory = Bukkit.createInventory(null,54,"Anvil");

        for(int anvilLeftSlot:anvilLeftSlots){
            inventory.setItem(anvilLeftSlot, ANVIL_LEFT_RED.getItem());
        }
        for(int anvilRightSlot:anvilRightSlots){
            inventory.setItem(anvilRightSlot, ANVIL_RIGHT_RED.getItem());
        }
        for(int anvilBackgroundSlot:anvilBackgroundSlots){
            inventory.setItem(anvilBackgroundSlot, DARK_GRAY_BACKGROUND.getItem());
        }
        for(int anvilBottomBarSlot:anvilBottomBarSlots){
            inventory.setItem(anvilBottomBarSlot, RED_BACKGROUND.getItem());
        }
        inventory.setItem(13, ANVIL_RESULT_BARRIER.getItem());
        inventory.setItem(22, ANVIL_BUTTON_OFF.getItem());
        inventory.setItem(49,CLOSE_BARRIER.getItem());

        player.openInventory(inventory);
    }

    private void updateUI(Player player){
        Bukkit.getScheduler().runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), ()->{
            boolean leftReady = false;
            if(inventory.getItem(29)!=null){
                //Check if left input is custom item
                if(isCustomItem(inventory.getItem(29))){
                    leftReady = true;
                }
            }
            boolean rightReady = false;
            if(inventory.getItem(33)!=null){
                //Check if right input is custom item and combinable in anvil
                if(isCustomItem(inventory.getItem(33))){
                    CustomItem customItem = new CustomItem(inventory.getItem(33));
                    if(customItem.getIsCombinableAnvil()){
                        rightReady = true;
                    }
                }
            }

//            Bukkit.broadcastMessage("l:r:"+leftReady+rightReady);
            if(leftReady){
                for(int anvilLeftSlot:anvilLeftSlots) inventory.setItem(anvilLeftSlot, ANVIL_LEFT_GREEN.getItem());
            }else{
                for(int anvilLeftSlot:anvilLeftSlots) inventory.setItem(anvilLeftSlot, ANVIL_LEFT_RED.getItem());
            }
            if(rightReady){
                for(int anvilRightSlot:anvilRightSlots) inventory.setItem(anvilRightSlot, ANVIL_RIGHT_GREEN.getItem());
            }else{
                for(int anvilRightSlot:anvilRightSlots) inventory.setItem(anvilRightSlot, ANVIL_RIGHT_RED.getItem());
            }
            if(leftReady && rightReady) {
                assert isCustomItem(inventory.getItem(29));
                assert isCustomItem(inventory.getItem(33));
                CustomItem leftCustomItem = new CustomItem(inventory.getItem(29));
                CustomItem rightCustomItem = new CustomItem(inventory.getItem(33));
                assert rightCustomItem.getIsCombinableAnvil();

                // Check what item is on the right slot
                if(Objects.equals(rightCustomItem.getID(), CustomItemID.HOT_POTATO_BOOK.getID())){
                    //Hot potato book
                    if(isWeapon(leftCustomItem.getID())){
                        //if left item has under 10 hot potato count
                        if(leftCustomItem.getHotPotatoCount()<10){
                            //Show preview on result slot
                            CustomItem resultItemPreview = new CustomItem(leftCustomItem);
                            resultItemPreview.setHotPotatoCount(resultItemPreview.getHotPotatoCount()+1);
                            ItemMeta tempItemMeta = resultItemPreview.getItemMeta();
                            List<String> tempLore = tempItemMeta.getLore();
                            tempLore.addAll(Arrays.asList("§8§m-----------------", "§7§aThis is the item you will get.", "§7§aClick the §cANVIL BELOW§a to combine."));
                            tempItemMeta.setLore(tempLore);
                            resultItemPreview.setItemMeta(tempItemMeta);
                            inventory.setItem(13,resultItemPreview);
                            //Set anvil button to pressable
                            inventory.setItem(22, ANVIL_BUTTON_ON.getItem());
                            // Set bottom bar to green
                            for(int anvilBottomBarSlot:anvilBottomBarSlots){
                                inventory.setItem(anvilBottomBarSlot, GREEN_BACKGROUND.getItem());
                            }
                        }else{
                            //Left item has 10 hot potato count
                            inventory.setItem(13, ANVIL_RESULT_BARRIER.getItem());
                            inventory.setItem(22, ANVIL_BUTTON_OFF.getItem());
                            for(int anvilBottomBarSlot:anvilBottomBarSlots){
                                inventory.setItem(anvilBottomBarSlot, RED_BACKGROUND.getItem());
                            }
                        }
                    }
                }
            }else{
                inventory.setItem(13, ANVIL_RESULT_BARRIER.getItem());
                inventory.setItem(22, ANVIL_BUTTON_OFF.getItem());
                for(int anvilBottomBarSlot:anvilBottomBarSlots){
                    inventory.setItem(anvilBottomBarSlot, RED_BACKGROUND.getItem());
                }
            }
//        player.getOpenInventory().getTopInventory().setContents(inventory.getContents());
            player.updateInventory();
//        Bukkit.getScheduler().runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), player::updateInventory, 1L);
        }, 1L);
    }

    private void attemptCombineItems(Player player){
        Bukkit.getScheduler().runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            //Check if left & right input is custom item
            if(!isCustomItem(inventory.getItem(29)) || !isCustomItem(inventory.getItem(33)))return;

            CustomItem leftCustomItem = new CustomItem(inventory.getItem(29));
            CustomItem rightCustomItem = new CustomItem(inventory.getItem(33));
            if(!rightCustomItem.getIsCombinableAnvil()) return;

            assert rightCustomItem.getIsCombinableAnvil();

            // if right item is hot potato book
            if(Objects.equals(rightCustomItem.getID(), CustomItemID.HOT_POTATO_BOOK.getID())){
                if(isWeapon(leftCustomItem.getID())){
                    //if left item has under 10 hot potato count
                    if(leftCustomItem.getHotPotatoCount()<10){
                        //add hot potato count
                        CustomItem resultItem = new CustomItem(leftCustomItem);
                        resultItem.setHotPotatoCount(resultItem.getHotPotatoCount()+1);
                        //remove left and right and preview item
                        inventory.setItem(29, null);
                        inventory.setItem(33, null);
                        inventory.setItem(13, null);
                        //set result item
                        inventory.setItem(13, resultItem);
                        //set anvil to claim mode
                        inventory.setItem(22, ANVIL_BUTTON_CLAIM.getItem());
                        //play anvil sound
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);

                        //set bottom bar, left and right to red
                        for(int anvilBottomBarSlot:anvilBottomBarSlots){
                            inventory.setItem(anvilBottomBarSlot, RED_BACKGROUND.getItem());
                        }
                        for(int anvilLeftSlot:anvilLeftSlots) inventory.setItem(anvilLeftSlot, ANVIL_LEFT_RED.getItem());
                        for(int anvilRightSlot:anvilRightSlots) inventory.setItem(anvilRightSlot, ANVIL_RIGHT_RED.getItem());
                        player.updateInventory();
                    }else{
                        playerWarn(player, "§cThis item has reached the maximum hot potato count.");
                    }
                }
            }



        }, 1L);
    }
    private void safeReset(Player player){
        Bukkit.getScheduler().runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            if(inventory.getItem(13)==null) {
                inventory.setItem(13, ANVIL_RESULT_BARRIER.getItem());
                inventory.setItem(22, ANVIL_BUTTON_OFF.getItem());
            }
            player.updateInventory();
        }, 1L);
    }

    private void safeClose(Player player){
        Bukkit.getScheduler().runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            boolean shouldClose = true;
            // check if result item is claimable
            if(inventory.getItem(13)!=null && Objects.equals(inventory.getItem(13), ANVIL_RESULT_BARRIER.getItem())){
                // check if anvil is in claim mode
                if(Objects.equals(inventory.getItem(22), ANVIL_BUTTON_CLAIM.getItem())){
                    // check if player has empty slot
                    if(player.getInventory().firstEmpty() != -1){
                        // give item to player
                        player.getInventory().addItem(inventory.getItem(13));
                        // remove item from anvil
                        inventory.setItem(13, null);
                    }else{
                        // player inventory is full
                        player.sendMessage("§cYour inventory is full! Please make some space before Closing the Anvil.");
                        shouldClose = false;
                    }
                }
            }
            // check if left input has item, give back to player if player have empty slot
            if(inventory.getItem(29)!=null){
                if(player.getInventory().firstEmpty() != -1) {
                    // give item back to player
                    player.getInventory().addItem(inventory.getItem(29));
                    // remove item from anvil
                    inventory.setItem(29, null);
                }else{
                    // player inventory is full
                    player.sendMessage("§cYour inventory is full! Please make some space before Closing the Anvil.");
                    shouldClose = false;
                }
            }
            // check if right input has item, give back to player if player have empty slot
            if(inventory.getItem(33)!=null){
                if(player.getInventory().firstEmpty() != -1) {
                    // give item back to player
                    player.getInventory().addItem(inventory.getItem(33));
                    // remove item from anvil
                    inventory.setItem(33, null);
                }else{
                    // player inventory is full
                    player.sendMessage("§cYour inventory is full! Please make some space before Closing the Anvil.");
                    shouldClose = false;
                }
            }

            if(shouldClose){
                player.closeInventory();
            }else{
                //make player reopen the UI
                player.openInventory(inventory);
            }
        }, 1L);
    }

    @EventHandler
    public void onUIClick(InventoryClickEvent event){
//        Bukkit.broadcastMessage("view title:"+event.getView().getTitle());
//        Bukkit.broadcastMessage("clicked title"+event.getClickedInventory().getTitle());
        if(event.getWhoClicked() instanceof Player) {
            //Inventory view (Whole inventory UI including top and bottom's) title (top title usually) is anvil UI
            if(!Objects.equals(event.getView().getTitle(), "Anvil"))return;
            if(event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
            if (event.getCurrentItem() == null)
                return;

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getClickedInventory();
            int clickedSlot = event.getSlot();
            ItemStack clickedItem = event.getCurrentItem();
            InventoryAction inventoryAction = event.getAction();

            //Disable shift click & hotbar swap
            if(inventoryAction.toString().contains("HOTBAR") || inventoryAction==InventoryAction.MOVE_TO_OTHER_INVENTORY){
                event.setCancelled(true);
                return;
            }

            //If clicked is top UI (anvil)
            if (Objects.equals(event.getClickedInventory().getTitle(), "Anvil")) {
                if (clickedSlot == 13) {
                    //Anvil result
                    if (clickedItem.getType() == ANVIL_RESULT_BARRIER.getItem().getType()) {
                        event.setCancelled(true);
                    } else if (inventory.getItem(22).getType() == ANVIL_BUTTON_ON.getItem().getType()) {
                        // preview item
                        event.setCancelled(true);
                    } else {
                        //actual result item
                        safeReset(player);
                    }
                } else if (clickedSlot == 29 || clickedSlot == 33) {
                    //Anvil left input / right input
                    //Update when something happen in these slot
                    updateUI(player);

                } else {
                    //Background, button, close button
                    event.setCancelled(true);
                    if (clickedSlot == 22) {
                        //Anvil action button
                        attemptCombineItems(player);
                    } else if (clickedSlot == 49) {
                        //Anvil close button
                        safeClose(player);
                    }
                }
            }else{// If clicked is bottom UI (Player inventory)
                // if anvil result is clamable, do not allow player to move item to anvil
                if(event.getView().getTopInventory().getItem(22).getType() == ANVIL_BUTTON_CLAIM.getItem().getType()){
                    event.setCancelled(true);
                    playerWarn(player, "§cPlease claim the result item before moving items.");
                }
            }
        }
    }

    @EventHandler
    public void onUIClose(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = (Player) event.getPlayer();
            if(Objects.equals(event.getInventory().getTitle(), "Anvil")){
                safeClose(player);
            }
        }
    }

    @EventHandler
    public void onUIDEBUGClick(InventoryClickEvent event){
        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();
        player.sendMessage("---------DBG START----------");
        player.sendMessage("event.getSlotType():"+event.getSlotType());
        player.sendMessage("event.getSlot():"+event.getSlot());
        player.sendMessage("event.getRawSlot():"+event.getRawSlot());
        player.sendMessage("event.getClickedInventory().getTitle():"+event.getClickedInventory().getTitle());
        player.sendMessage("event.getHotbarButton():"+event.getHotbarButton());
        player.sendMessage("event.getAction():"+event.getAction());
        if(event.getCursor().hasItemMeta()) {
            player.sendMessage("event.getCursor().getItemMeta().getDisplayName():" + event.getCursor().getItemMeta().getDisplayName());
        }else player.sendMessage("NO CURSOR ITEM META");
        if(event.getCurrentItem().hasItemMeta()) {
            player.sendMessage("event.getCurrentItem().getItemMeta().getDisplayName():"+event.getCurrentItem().getItemMeta().getDisplayName());
        }else player.sendMessage("NO ITEM META");
        player.sendMessage("event.getView().getTitle();"+event.getView().getTitle());
        player.sendMessage("event.getView().getTopInventory().getTitle();"+event.getView().getTopInventory().getTitle());
        player.sendMessage("---------DBG END----------");

    }

}
