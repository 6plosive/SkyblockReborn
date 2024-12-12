package me.alwayslg.ui;

import me.alwayslg.customplayers.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.ui.UIItemStack.*;
import static me.alwayslg.util.Utilities.getPlayerHead;
import static me.alwayslg.util.Utilities.playerWarn;

public class SkyblockMenuUI implements Listener {
    private static final String name = "Skyblock Menu";
    private static Inventory inventory;
    private final static List<Integer> backgroundSlots = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,26,27,28,34,35,36,37,38,39,40,41,42,43,44,45,46,52,53);
    public static void open(CustomPlayer customPlayer){
        Player player = customPlayer.getPlayer();
        inventory = Bukkit.getServer().createInventory(null,54,name);

        ItemStack profileItem = getPlayerHead(player);
        inventory.setItem(13,profileItem);
        inventory.setItem(19, SKYBLOCK_MENU_SKILLS.getItem());
        inventory.setItem(20, SKYBLOCK_MENU_COLLECTIONS.getItem());
        inventory.setItem(21, SKYBLOCK_MENU_RECIPE.getItem());
        inventory.setItem(22,SKYBLOCK_MENU_LEVEL_PROGRESS.getItem());
        inventory.setItem(23, SKYBLOCK_MENU_QUEST.getItem());
        inventory.setItem(24, SKYBLOCK_MENU_CALENDER.getItem());
        inventory.setItem(25, SKYBLOCK_MENU_STORAGE.getItem());
        inventory.setItem(29, SKYBLOCK_MENU_BAGS.getItem());
        inventory.setItem(30, SKYBLOCK_MENU_PETS.getItem());
        inventory.setItem(31, SKYBLOCK_MENU_CRAFTING_TABLE.getItem());
        inventory.setItem(32, SKYBLOCK_MENU_WARDROBE.getItem());
        inventory.setItem(33, SKYBLOCK_MENU_PERSONAL_BANK.getItem());
        inventory.setItem(47, SKYBLOCK_MENU_FAST_TRAVEL.getItem());
        inventory.setItem(48, SKYBLOCK_MENU_SWITCH_PROFILES.getItem());
        inventory.setItem(49, CLOSE_BARRIER.getItem());
        inventory.setItem(50, SKYBLOCK_MENU_SETTINGS.getItem());
        inventory.setItem(51, SKYBLOCK_MENU_BOOSTER_COOKIE.getItem());
        for(int backgroundSlot : backgroundSlots){
            inventory.setItem(backgroundSlot, UIItemStack.DARK_GRAY_BACKGROUND.getItem());
        }

        player.openInventory(inventory);
    }
    @EventHandler
    public void onUIClick(InventoryClickEvent event){
//        Bukkit.broadcastMessage("view title:"+event.getView().getTitle());
//        Bukkit.broadcastMessage("clicked title"+event.getClickedInventory().getTitle());
        if(event.getWhoClicked() instanceof Player) {
            //Inventory view (Whole inventory UI including top and bottom's) title (top title usually) is skyblock menu UI
            if(!Objects.equals(event.getView().getTitle(), name))return;
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
            if (Objects.equals(event.getClickedInventory().getTitle(), name)) {
                event.setCancelled(true);
                if (clickedSlot == 13) {

                } else if (clickedSlot == 19) {

                } else if (clickedSlot == 20) {

                } else if (clickedSlot == 21) {

                } else if (clickedSlot == 22) {

                } else if (clickedSlot == 23) {

                } else if (clickedSlot == 24) {

                } else if (clickedSlot == 25) {

                } else if (clickedSlot == 29) {

                } else if (clickedSlot == 30) {

                } else if (clickedSlot == 31) {

                } else if (clickedSlot == 32) {

                } else if (clickedSlot == 33) {

                } else if (clickedSlot == 47) {

                } else if (clickedSlot == 48) {

                } else if (clickedSlot == 49) {
                    player.closeInventory();
                } else if (clickedSlot == 50) {

                } else if (clickedSlot == 51) {

                } else {
                    //Background slots
                }
            }else{// If clicked is bottom UI (Player inventory)

            }
        }
    }
}
