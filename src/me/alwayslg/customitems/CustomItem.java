package me.alwayslg.customitems;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomItem {
    private int damage;
    private String name;
    private ItemStack itemStack;
    private Material material;
    private List<String> lore;
    private Rarity rarity;
    private ItemType itemType;

    public CustomItem(){
        //Initialize dummy itemStack
        itemStack=new ItemStack(Material.STONE);
//        setRarity(Rarity.NULL);
//        setItemType(ItemType.NULL);
    }

    public static void getDamage(ItemStack itemStack){
//        net.md_5.bungee.api.chat.

    }

    public void setNBTTags(NBTTagCompound nbtTagCompound){
//        nbtTagCompound
    }
    public void setDamage(int damage){
        this.damage=damage;
        ItemMeta h;
//        itemStack.getString();
        updateLore();
    }
    public void setMaterial(Material material) {
        this.material = material;
        itemStack.setType(material);
    }
    private void setItemMeta(ItemMeta itemMeta){
        itemStack.setItemMeta(itemMeta);
    }
    public void setName(String name) {
        this.name = name;
        updateName();
    }
    private void updateName(){
        if(getRarity()!=null) {
            ItemMeta tempItemMeta = getItemMeta();
            tempItemMeta.setDisplayName(ChatColor.RESET.toString() + getRarity().getColor().toString() + name);
            setItemMeta(tempItemMeta);
        }
    }
    private void setLore(List<String> lore) {
        this.lore = lore;
        ItemMeta tempItemMeta = getItemMeta();
        tempItemMeta.setLore(lore);
        setItemMeta(tempItemMeta);
    }
    private void updateLore(){
        List<String> tempLore = new ArrayList<>();
        if(damage != 0){
            tempLore.add(String.format("§7Damage: §c+%d",damage));
        }
        tempLore.add(" ");
        if(getRarity()!=null&&getItemType()!=null){
            tempLore.add(String.format("%s%s%s %s",getRarity().getColor().toString(),ChatColor.BOLD.toString(),getRarity().toString(),getItemType().toString()));
        }

        setLore(tempLore);
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
        updateLore();
        updateName();
    }
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
        updateLore();
    }

    public int getDamage(){
        return damage;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
    public ItemMeta getItemMeta(){
        return itemStack.getItemMeta();
    }
    public Material getMaterial() {
        return material;
    }
    public String getName() {
        return name;
    }
    public List<String> getLore() {
        return lore;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public ItemType getItemType() {
        return itemType;
    }
}
