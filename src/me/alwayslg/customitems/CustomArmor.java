package me.alwayslg.customitems;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.customitems.CustomItemID.*;

public class CustomArmor extends CustomItem {
    public CustomArmor(CustomItemID id) {
        super(id);
        setColor(getColor());
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

        //Stats chunk
        int tempLoreLength = tempLore.size();
        if (getHealth() != 0 || getHotPotatoCount() != 0) {
            int hotPotatoHealth = getHotPotatoCount() * 4;
            if (hotPotatoHealth != 0) {
                int totalHealth = getHealth();
                tempLore.add(String.format("§7Health: §a+%d §e(+%d)", totalHealth, hotPotatoHealth));
            } else {
                tempLore.add(String.format("§7Health: §a+%d", getHealth()));
            }
//            tempLore.add(" ");
        }
        if (getDefense() != 0 || getHotPotatoCount() != 0) {
            int hotPotatoDefense = getHotPotatoCount() * 2;
            if (hotPotatoDefense != 0) {
                int totalDefense = getDefense();
                tempLore.add(String.format("§7Defense: §a+%d §e(+%d)", totalDefense, hotPotatoDefense));
            } else {
                tempLore.add(String.format("§7Defense: §a+%d", getDefense()));
            }
        }
        if (tempLore.size() > tempLoreLength) tempLore.add(" ");

        //Description chunk
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
    public int getDefense(){
        return getDefenseByID(getID()) + getHotPotatoCount() * 2;
    }
    public Color getColor(){
        return getColorByID(getID());
    }
    void setColor(Color color){
        if(color==null)return;
        ItemMeta meta = getItemMeta();
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(color);
        setItemMeta(leatherArmorMeta);
    }

    public static boolean isCustomArmor(ItemStack item) {
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
