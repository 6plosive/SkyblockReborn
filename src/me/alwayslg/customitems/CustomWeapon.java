package me.alwayslg.customitems;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.alwayslg.customitems.CustomItemID.*;
import static me.alwayslg.customitems.ItemType.isWeapon;

public class CustomWeapon extends CustomItem {
    public CustomWeapon(CustomItemID id) {
        super(id);
    }
    public CustomWeapon(ItemStack customItem) {
        super(customItem);
    }
    @Override
    void updateLore(){
        List<String> tempLore = new ArrayList<>();
        if(getIsCombinableAnvil()){
            tempLore.add("§7§8Combinable in Anvil");
            tempLore.add(" ");
        }
        //Stats chunk
        int tempLoreLength = tempLore.size();
        if(getDamage() != 0 || getHotPotatoCount() != 0){
            int hotPotatoDamage = getHotPotatoCount()*2;
            if(hotPotatoDamage!=0){
                int totalDamage = getDamage();
                tempLore.add(String.format("§7Damage: §c+%d §e(+%d)",totalDamage, hotPotatoDamage));
            }else {
                tempLore.add(String.format("§7Damage: §c+%d", getDamage()));
            }
        }
        if(getStrength() != 0 || getHotPotatoCount() != 0){
            int hotPotatoStrength = getHotPotatoCount()*2;
            if(hotPotatoStrength!=0){
                int totalStrength = getStrength();
                tempLore.add(String.format("§7Strength: §c+%d §e(+%d)",totalStrength, hotPotatoStrength));
            }else {
                tempLore.add(String.format("§7Strength: §c+%d", getStrength()));
            }
        }
        if(tempLore.size()>tempLoreLength) tempLore.add(" ");

        //Description chunk
        if(getDescription() != null){
            tempLore.addAll(getDescription());
            tempLore.add(" ");
        }
        if(getRarity()!=null){
//            tempLore.add(" ");
            String rarityString = String.format("%s%s%s",getRarity().getColor().toString(), ChatColor.BOLD.toString(),getRarity().toString());
            if(getItemType()!=null) rarityString+=' '+getItemType().toString();
            tempLore.add(rarityString);
        }
        // Remove empty gap at last of list if its present
        if(!tempLore.isEmpty()){
            assert(tempLore.size()>=1);
            if(Objects.equals(tempLore.get(tempLore.size() - 1), " ")){
                tempLore.remove(tempLore.size()-1);
            }
        }
        setLore(tempLore);
    }
    public int getDamage(){
        return getDamageByID(getID()) + getHotPotatoCount()*2;
    }
    public int getStrength(){
        return getStrengthByID(getID()) + getHotPotatoCount()*2;
    }
    public int getMagicDamage(){
        return getMagicDamageByID(getID());
    }

    public static boolean isCustomWeapon(ItemStack itemStack){
        return CustomItem.isCustomItem(itemStack) && isWeapon(new CustomItem(itemStack).getItemType());
    }
}
