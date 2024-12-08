package me.alwayslg.customitems;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CustomItemID {
    ASPECT_OF_THE_END("ASPECT_OF_THE_END","Aspect Of The End",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.RARE,100, 0, null),
    BONZO_STAFF("BONZO_STAFF","Bonzo's Staff",Material.BLAZE_ROD,ItemType.WAND,Rarity.LEGENDARY,20,0,null),
    BOOMERANG("BOOMERANG","Bonemerang",Material.BONE,ItemType.DUNGEON_BOW,Rarity.LEGENDARY,270,0,null),
    DIAMOND_SWORD("DIAMOND_SWORD","Diamond Sword",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.UNCOMMON,35,0,null),
    JER_JER_SHORTBOW("JER_JER_SHORTBOW","Jer Jer Shortbow",Material.BOW,ItemType.BOW,Rarity.EPIC,310,0,null),
    TERMINATOR("TERMINATOR","Terminator",Material.BOW,ItemType.BOW,Rarity.LEGENDARY,310,0,null),
    WITHER_SHORTBOW("WITHER_SHORTBOW","Wither Shortbow",Material.BOW,ItemType.BOW,Rarity.RARE,50,0,null),
    HYPERION("HYPERION","Hyperion",Material.IRON_SWORD,ItemType.DUNGEON_SWORD,Rarity.LEGENDARY,260,300,null),
    GIANTS_SWORD("GIANTS_SWORD","Giant's Sword",Material.IRON_SWORD,ItemType.DUNGEON_SWORD,Rarity.LEGENDARY,500,5000,null),
    SKYBLOCK_MENU("SKYBLOCK_MENU","§aSkyBlock Menu §7(Click)",Material.NETHER_STAR,null,null,0,0, Arrays.asList("§7View all of your SkyBlock progress,", "§7including your Skills, Collections,", "§7Recipes, and more!", "", "§eClick to open!"));

    private final String id;
    private final String name;
    private final Material material;
    private final ItemType itemType;
    private final Rarity rarity;
    private final int damage;
    private final int magicDamage;
    private final List<String> description;
    private static final Map<String, CustomItemID> BY_ID = Maps.newHashMap();
    CustomItemID(String id, String name, Material material, ItemType itemType, Rarity rarity, int damage, int magicDamage, List<String> description){
        this.id = id;
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.rarity = rarity;
        this.damage = damage;
        this.magicDamage = magicDamage;
        this.description = description;
    }
//    CustomItemID(String id, String name, Material material, ItemType itemType, Rarity rarity, int damage, int magicDamage){
//        this.id = id;
//        this.name = name;
//        this.material = material;
//        this.itemType = itemType;
//        this.rarity = rarity;
//        this.damage = damage;
//        this.magicDamage = magicDamage;
//    }
    static{
        CustomItemID[] var0;
        for(CustomItemID customItemID : var0 = values()) {
            BY_ID.put(customItemID.id, customItemID);
        }
    }

    public String getID() {
        return id;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public int getDamage() {
        return damage;
    }
    public Material getMaterial() {
        return material;
    }
    public String getName() {
        return name;
    }
    public int getMagicDamage() {
        return magicDamage;
    }
    public List<String> getDescription(){
        return description;
    }

    public static CustomItemID getCustomItemID(String id){
        return BY_ID.get(id);
    }
    public static ItemType getItemTypeByID(String id) {
        return getCustomItemID(id)==null?null:getCustomItemID(id).getItemType();
    }
    public static Rarity getRarityByID(String id){
        return getCustomItemID(id)==null?null:getCustomItemID(id).getRarity();
    }
    public static int getDamageByID(String id) {
//        Bukkit.broadcastMessage("Damage for "+id+":"+getCustomItemID(id));
        return getCustomItemID(id)==null?0:getCustomItemID(id).getDamage();
    }
    public static Material getMaterialByID(String id){
        return getCustomItemID(id)==null?null:getCustomItemID(id).getMaterial();
    }
    public static String getNameByID(String id){
        return getCustomItemID(id)==null?null:getCustomItemID(id).getName();
    }
    public static int getMagicDamageByID(String id) {
        return getCustomItemID(id)==null?0:getCustomItemID(id).getMagicDamage();
    }
    public static List<String> getDescriptionByID(String id) {
        return getCustomItemID(id)==null?null:getCustomItemID(id).getDescription();
    }
}
