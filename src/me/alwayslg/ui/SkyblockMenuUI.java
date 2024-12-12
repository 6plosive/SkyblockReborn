package me.alwayslg.ui;

import me.alwayslg.customplayers.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static me.alwayslg.ui.UIItemStack.*;
import static me.alwayslg.util.Utilities.getPlayerHead;

public class SkyblockMenuUI {
    private static Inventory inventory;
    private final static List<Integer> backgroundSlots = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,26,27,28,34,35,36,37,38,39,40,41,42,43,44,45,46,52,53);
    public static void open(CustomPlayer customPlayer){
        Player player = customPlayer.getPlayer();
        inventory = Bukkit.getServer().createInventory(null,54,"Skyblock Menu");

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
}
