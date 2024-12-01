package me.alwayslg.customitems;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomItem extends ItemStack{
//    private int damage;
//    private String name;
//    private ItemStack itemStack;
//    private Material material;
//    private List<String> lore;
//    private Rarity rarity;
//    private ItemType itemType;
//    private UUID uuid;

    public CustomItem(){
        //Initialize dummy itemStack
//        itemStack=new ItemStack(Material.STONE);
        setNBTTags("customitem",new NBTTagByte((byte) 1));
//        setRarity(Rarity.NULL);
//        setItemType(ItemType.NULL);
    }

//    public static void getDamage(ItemStack itemStack){
////        net.md_5.bungee.api.chat.
//
//    }

    public void setNBTTags(String s, NBTBase nbtBase){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);
        NBTTagCompound nbtTagCompound = (nmsItemStack.hasTag()) ? nmsItemStack.getTag() : new NBTTagCompound();
        nbtTagCompound.set(s,nbtBase);
        nmsItemStack.setTag(nbtTagCompound);
        ItemStack updatedItemStack = CraftItemStack.asBukkitCopy(nmsItemStack);
        super.setItemMeta(updatedItemStack.getItemMeta()); // Update the meta
    }
    public String getStringNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getString(s);
            }
        }
        return null;
    }
    public double getDoubleNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getDouble(s);
            }
        }
        return 0;
    }
    public byte getByteNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getByte(s);
            }
        }
        return 0;
    }
    public int getIntNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getInt(s);
            }
        }
        return 0;
    }
    public boolean getBooleanNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getBoolean(s);
            }
        }
        return false;
    }
    public byte[] getByteArrayNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getByteArray(s);
            }
        }
        return null;
    }
    public NBTTagCompound getCompoundNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getCompound(s);
            }
        }
        return null;
    }
    public float getFloatNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getFloat(s);
            }
        }
        return null;
    }
    public int[] getIntArrayNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getIntArray(s);
            }
        }
        return null;
    }
    public NBTTagList getListNBTTags(String s, int i){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getList(s,i);
            }
        }
        return null;
    }
    public long getLongNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getLong(s);
            }
        }
        return 0;
    }
    public short getShortNBTTags(String s){
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(this);

        if (nmsItemStack.hasTag()) {
            NBTTagCompound compound = nmsItemStack.getTag();
            if (compound.hasKey(s)) {
                return compound.getShort(s);
            }
        }
        return 0;
    }

    public String getID(){
        return getStringNBTTags("id");
    }
    public void setID(String id){
        setNBTTags("id",new NBTTagString(id));
    }

    public int getDamage(){
        return getDamageByID(getID());
    }

    public void setName(String name) {
        ItemMeta tempItemMeta = getItemMeta();
        tempItemMeta.setDisplayName(name);
        setItemMeta(tempItemMeta);
    }
    public String getName() {
        return getItemMeta().getDisplayName();
    }
    private void updateName(){
        if(getRarity()!=null) {
            setName(ChatColor.RESET.toString() + getRarity().getColor().toString() + getName());
        }
    }
    private void setLore(List<String> lore) {
    //        this.lore = lore;
        ItemMeta tempItemMeta = getItemMeta();
        tempItemMeta.setLore(lore);
        setItemMeta(tempItemMeta);
    }
    private void updateLore(){
        List<String> tempLore = new ArrayList<>();
        if(getDamage() != 0){
            tempLore.add(String.format("§7Damage: §c+%d",getDamage()));
        }
        tempLore.add(" ");
        if(getRarity()!=null&&getItemType()!=null){
            tempLore.add(String.format("%s%s%s %s",getRarity().getColor().toString(),ChatColor.BOLD.toString(),getRarity().toString(),getItemType().toString()));
        }
        setLore(tempLore);
    }
    public Rarity getRarity(){
        return getRarityByID(getID());
    }
    public void setUUID(UUID uuid){
//        this.uuid = uuid;
        setNBTTags("uuid",new NBTTagString(uuid.toString()));
    }
    public UUID getUUID(){
        return UUID.fromString(getStringNBTTags("uuid"));
    }
    public ItemType getItemType(){
        return getItemTypeByID(getID());
    }

//    public void setDamage(int damage){
//        this.damage=damage;
//        //Set NBT Tags
//        setNBTTags("damage", new NBTTagInt(damage));
//        //Update lore cuz damage is inside lore
//        updateLore();
//    }
    // After running this function, Update player's inventory by setting
    // player's hold item to getItemStack(). Otherwise material will not change.
//    public void setMaterial(Material material) {
//        this.material = material;
//        itemStack.setType(material);
//    }
//    private void setItemMeta(ItemMeta itemMeta){
//        itemStack.setItemMeta(itemMeta);
//    }


//    public void setRarity(Rarity rarity) {
//        this.rarity = rarity;
//        updateLore();
//        updateName();
//    }
//    public void setItemType(ItemType itemType) {
//        this.itemType = itemType;
//        updateLore();
//    }


//    public ItemStack getItemStack() {
//        return itemStack;
//    }
//    public ItemMeta getItemMeta(){
//        return itemStack.getItemMeta();
//    }
//    public Material getMaterial() {
//        return itemStack.getType();
//    }
//    public List<String> getLore() {
//        return lore;
//    }
//    public Rarity getRarity() {
//        return rarity;
//    }
//    public ItemType getItemType() {
//        return itemType;
//    }


    public static boolean isCustomItem(ItemStack itemStack){
        if(itemStack == null || itemStack.getType().equals(Material.AIR)) return false;
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = (nmsItemStack.hasTag()) ? nmsItemStack.getTag() : new NBTTagCompound();
        return nbtTagCompound.getByte("customitem")==1;
    }
}
