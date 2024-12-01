package me.alwayslg.customitems;

import org.bukkit.ChatColor;

public enum Rarity{
    COMMON(ChatColor.WHITE,"COMMON"),
    UNCOMMON(ChatColor.GREEN,"UNCOMMON"),
    RARE(ChatColor.BLUE,"RARE"),
    EPIC(ChatColor.DARK_PURPLE,"EPIC"),
    LEGENDARY(ChatColor.GOLD,"LEGENDARY"),
    MYTHIC(ChatColor.LIGHT_PURPLE,"MYTHIC"),
    DIVINE(ChatColor.AQUA,"DIVINE"),
    SPECIAL(ChatColor.RED,"SPECIAL"),
    VERY_SPECIAL(ChatColor.RED,"VERY SPECIAL"),
    ULTIMATE(ChatColor.DARK_RED,"ULTIMATE");
//    NULL(ChatColor.DARK_RED,"NULL");
    private final ChatColor color;
    private final String name;
    private Rarity(ChatColor color,String name){
        this.color = color;
        this.name = name;
    }
    public String toString(){
        return name;
    }
    public ChatColor getColor(){
        return color;
    }
}
