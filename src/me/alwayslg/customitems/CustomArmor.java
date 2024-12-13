package me.alwayslg.customitems;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.customitems.CustomItemID.*;

public class CustomArmor extends CustomItem {
    public CustomArmor(CustomItemID id) {
        super(id);
    }
    public CustomArmor(ItemStack customItem) {
        super(customItem);
    }
    @Override
    void updateLore() {
        List<String> tempLore = new ArrayList<>();
        if (getIsCombinableAnvil()) {
            tempLore.add("§7§8Combinable in Anvil");
            tempLore.add(" ");
        }
        if (getHealth() != 0 || getHotPotatoCount() != 0) {
            int hotPotatoHealth = getHotPotatoCount() * 4;
            if (hotPotatoHealth != 0) {
                int totalHealth = getHealth();
                tempLore.add(String.format("§7Health: §a+%d §e(+%d)", totalHealth, hotPotatoHealth));
            } else {
                tempLore.add(String.format("§7Health: §a+%d", getHealth()));
            }
            tempLore.add(" ");
        }
        if (getDescription() != null) {
            tempLore.addAll(getDescription());
            tempLore.add(" ");
        }
        if (getRarity() != null) {
//            tempLore.add(" ");
            String rarityString = String.format("%s%s%s", getRarity().getColor().toString(), ChatColor.BOLD.toString(), getRarity().toString());
            if (getItemType() != null) rarityString += ' ' + getItemType().toString();
            tempLore.add(rarityString);
        }
        // Remove empty gap at last of list if its present
        if (!tempLore.isEmpty()) {
            assert (tempLore.size() >= 1);
            if (Objects.equals(tempLore.get(tempLore.size() - 1), " ")) {
                tempLore.remove(tempLore.size() - 1);
            }
        }
        setLore(tempLore);
    }
    public int getHealth() {
        return getHealthByID(getID()) + getHotPotatoCount() * 4;
    }

    public static boolean isArmor(ItemStack item) {
        return isCustomItem(item) && ItemType.isArmor(new CustomItem(item).getItemType());
    }

    public static boolean isHelmet(ItemStack item) {
        return isCustomItem(item) && new CustomItem(item).getItemType()==ItemType.HELMET;
    }
    public static boolean isChestplate(ItemStack item) {
        return isCustomItem(item) && new CustomItem(item).getItemType()==ItemType.CHESTPLATE;
    }
    public static boolean isLeggings(ItemStack item) {
        return isCustomItem(item) && new CustomItem(item).getItemType()==ItemType.LEGGINGS;
    }
    public static boolean isBoots(ItemStack item) {
        return isCustomItem(item) && new CustomItem(item).getItemType()==ItemType.BOOTS;
    }

}
