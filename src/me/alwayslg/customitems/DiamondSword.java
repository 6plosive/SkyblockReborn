package me.alwayslg.customitems;

import org.bukkit.Material;

public class DiamondSword extends CustomItem{
    public DiamondSword(){
        setMaterial(Material.DIAMOND_SWORD);
        setRarity(Rarity.UNCOMMON);
        setItemType(ItemType.SWORD);

        setName("Diamond Sword");
        setDamage(35);
    }
}
