package me.alwayslg.customitems;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.alwayslg.customitems.CustomItemID.*;

public class CustomItem extends ItemStack{
//    private int damage;
//    private String name;
//    private ItemStack itemStack;
//    private Material material;
//    private List<String> lore;
//    private Rarity rarity;
//    private ItemType itemType;
//    private UUID uuid;
//    private CustomItemID id;

    public CustomItem(CustomItemID id){
        //Initialize itemStack
        super(id.getMaterial());
        // ID contains all lore, dmg, everything base weapon have
        setID(id);

        setNBTTags("customitem",new NBTTagByte((byte) 1));
        // Default hideflags and unbreakable
        setNBTTags("Unbreakable",new NBTTagByte((byte) 1));
        setNBTTags("HideFlags",new NBTTagInt(254));
        // inherent flashy enchanted effect depends on item id
        if(getIsEnchanted()){
            setNBTTags("ench", new NBTTagList());
        }
    }
    public CustomItem(ItemStack itemStack){
        super(itemStack);
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
        return 0;
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
    private void setID(CustomItemID id){
        setNBTTags("id",new NBTTagString(id.getID()));
        setName(id.getName());
        updateName();
        updateLore();
    }

    private void setName(String name) {
        ItemMeta tempItemMeta = getItemMeta();
        tempItemMeta.setDisplayName(ChatColor.RESET.toString() + name);
        setItemMeta(tempItemMeta);
    }

    private void updateName(){
        if(getRarity()!=null) {
            setName(getRarity().getColor().toString() + getName());
        }else{ //Some item have no rarity i.e skyblock menu
            setName(getName());
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
        if(getIsCombinableAnvil()){
            tempLore.add("§7§8Combinable in Anvil");
            tempLore.add(" ");
        }
        if(getDamage() != 0 || getHotPotatoCount() != 0){
            int hotPotatoDamage = getHotPotatoCount()*2;
            if(hotPotatoDamage!=0){
                int totalDamage = getDamage();
                tempLore.add(String.format("§7Damage: §c+%d §e(+%d)",totalDamage, hotPotatoDamage));
            }else {
                tempLore.add(String.format("§7Damage: §c+%d", getDamage()));
            }
            tempLore.add(" ");
        }
        if(getDescription() != null){
            tempLore.addAll(getDescription());
            tempLore.add(" ");
        }
        if(getRarity()!=null){
//            tempLore.add(" ");
            String rarityString = String.format("%s%s%s",getRarity().getColor().toString(),ChatColor.BOLD.toString(),getRarity().toString());
            if(getItemType()!=null) rarityString+=' '+getItemType().toString();
            tempLore.add(rarityString);
        }
        // Remove empty gap at last of list if its present
        if(!tempLore.isEmpty()){
            assert(tempLore.size()>=1);
            if(tempLore.get(tempLore.size() - 1)==" "){
                tempLore.remove(tempLore.size()-1);
            }
        }
        setLore(tempLore);
    }
    public void setUUID(UUID uuid){
//        this.uuid = uuid;
        setNBTTags("uuid",new NBTTagString(uuid.toString()));
    }
    public UUID getUUID(){
        String uuidString = getStringNBTTags("uuid");
        if(uuidString==null)return null;
        return UUID.fromString(uuidString);
    }
    public int getHotPotatoCount(){
        return getIntNBTTags("hot_potato_count");
    }
    public void setHotPotatoCount(int hotPotatoCount){
        setNBTTags("hot_potato_count",new NBTTagInt(hotPotatoCount));
        updateLore();
    }
    public int getDamage(){
//        return getDamageByID(getID());
        return getDamageByID(getID()) + getHotPotatoCount()*2;
    }
    public Rarity getRarity(){
        return getRarityByID(getID());
    }
    public String getName() {
        return getNameByID(getID());
    }
    public ItemType getItemType(){
        return getItemTypeByID(getID());
    }
    public Material getMaterial(){
        return getMaterialByID(getID());
    }
    public int getMagicDamage(){
        return getMagicDamageByID(getID());
    }
    public boolean getIsEnchanted(){
        return getIsEnchantedByID(getID());
    }
    public boolean getIsCombinableAnvil(){
        return getIsCombinableAnvilByID(getID());
    }
    public List<String> getDescription(){
        return getDescriptionByID(getID());
    }


    public static boolean isCustomItem(ItemStack itemStack){
        if(itemStack == null || itemStack.getType().equals(Material.AIR)) return false;
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = (nmsItemStack.hasTag()) ? nmsItemStack.getTag() : new NBTTagCompound();
        return nbtTagCompound.getByte("customitem")==1;
    }
//    public static CustomItem parseCustomItem(ItemStack itemStack){
//        return this
//    }
}
