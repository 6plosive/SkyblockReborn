package me.alwayslg.customitems;

import com.google.common.collect.Maps;
import me.alwayslg.util.CustomHeads;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CustomItemID {
    ASPECT_OF_THE_END("ASPECT_OF_THE_END","Aspect Of The End",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.RARE,100,100, 0,false,false,null),
    BONZO_STAFF("BONZO_STAFF","Bonzo's Staff",Material.BLAZE_ROD,ItemType.WAND,Rarity.LEGENDARY,160,0,1000,false,false,null),
    BOOMERANG("BOOMERANG","Bonemerang",Material.BONE,ItemType.DUNGEON_BOW,Rarity.LEGENDARY,270,130,0,false,false,null),
    DIAMOND_SWORD("DIAMOND_SWORD","Diamond Sword",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.UNCOMMON,35,0,0,false,false,null),
    JER_JER_SHORTBOW("JER_JER_SHORTBOW","Jer Jer Shortbow",Material.BOW,ItemType.BOW,Rarity.EPIC,310,40,0,false,false,null),
    TERMINATOR("TERMINATOR","Terminator",Material.BOW,ItemType.BOW,Rarity.LEGENDARY,310,50,0,false,false,null),
    WITHER_SHORTBOW("WITHER_SHORTBOW","Wither Shortbow",Material.BOW,ItemType.BOW,Rarity.RARE,50,5,0,false,false,null),
    HYPERION("HYPERION","Hyperion",Material.IRON_SWORD,ItemType.DUNGEON_SWORD,Rarity.LEGENDARY,260,150,300,false,false,Arrays.asList( "§7Deals §c+50% §7damage to Withers.", "§7Grants §c+1 §c❁ Damage §7and §a+2 §b✎", "§bIntelligence §7per §cCatacombs §7level.")),
    GIANTS_SWORD("GIANTS_SWORD","Giant's Sword",Material.IRON_SWORD,ItemType.DUNGEON_SWORD,Rarity.LEGENDARY,500,0,5000,false,false,null),
    SIGMA_SKIBIDI_SWORD("SIGMA_SKIBIDI_SWORD","Sigma Skibidi Sword",Material.GOLD_SWORD,ItemType.SWORD,Rarity.ULTIMATE,99999999,8964,0,false,false,null),
    HOT_POTATO_BOOK("HOT_POTATO_BOOK","Hot Potato Book", Material.BOOK, null, Rarity.EPIC, 0,0,0,true,true,Arrays.asList("§7When applied to armor, grants §a+2❈", "§aDefense §7and §c+4❤ Health§7.", "", "§7When applied to weapons, grants", "§7§c+2❁ Strength §7and §c+2❁ Damage§7.", "", "§7This can be applied to an item up to", "§7§a10 §7times!")),

    LAPIS_ARMOR_HELMET("LAPIS_ARMOR_HELMET","Lapis Armor Helmet",Material.SEA_LANTERN,ItemType.HELMET,Rarity.UNCOMMON,0,100,100,null,null,false,false,null),
    LAPIS_ARMOR_CHESTPLATE("LAPIS_ARMOR_CHESTPLATE","Lapis Armor Chestplate",Material.LEATHER_CHESTPLATE,ItemType.CHESTPLATE,Rarity.UNCOMMON,0,100,100,Color.fromRGB(255),null,false,false,null),
    LAPIS_ARMOR_LEGGINGS("LAPIS_ARMOR_LEGGINGS","Lapis Armor Leggings",Material.LEATHER_LEGGINGS,ItemType.LEGGINGS,Rarity.UNCOMMON,0,100,100,Color.fromRGB(255),null,false,false,null),
    LAPIS_ARMOR_BOOTS("LAPIS_ARMOR_BOOTS","Lapis Armor Boots",Material.LEATHER_BOOTS,ItemType.BOOTS,Rarity.UNCOMMON,0,100,100,Color.fromRGB(255),null,false,false,null),
    NECRON_HELMET("NECRON_HELMET","Necron Helmet",null,ItemType.HELMET,Rarity.LEGENDARY,40,180,100,null,CustomHeads.NECRON_HELMET_SKULL,false,false,null),
    NECRON_CHESTPLATE("NECRON_CHESTPLATE","Necron Chestplate",Material.LEATHER_CHESTPLATE,ItemType.CHESTPLATE,Rarity.LEGENDARY,40,260,140,Color.fromRGB(15155516),null,false,false,null),
    NECRON_LEGGINGS("NECRON_LEGGINGS","Necron Leggings",Material.LEATHER_LEGGINGS,ItemType.LEGGINGS,Rarity.LEGENDARY,40,230,125,Color.fromRGB(15162428),null,false,false,null),
    NECRON_BOOTS("NECRON_BOOTS","Necron Boots",Material.LEATHER_BOOTS,ItemType.BOOTS,Rarity.LEGENDARY,40,145,85,Color.fromRGB(15167036),null,false,false,null),

    SKYBLOCK_MENU("SKYBLOCK_MENU","§aSkyBlock Menu §7(Click)",Material.NETHER_STAR,null,null,0,0,0,false,false,Arrays.asList("§7View all of your SkyBlock progress,", "§7including your Skills, Collections,", "§7Recipes, and more!", "", "§eClick to open!"));

    private final String id;
    private final String name;
    private final Material material;
    private final ItemType itemType;
    private final Rarity rarity;
    private final int damage;
    private final int magicDamage;
    private final int strength;
    private final int health;
    private final int defense;
    private final Color color;
    private final CustomHeads customHeads;
    private final boolean isEnchanted;
    private final boolean isCombinableAnvil;
    private final List<String> description;
    private static final Map<String, CustomItemID> BY_ID = Maps.newHashMap();
    //Armor
    // If the item is a skull, fill material = null, color = null, customHeads = CustomHeads
    CustomItemID(String id, String name, Material material, ItemType itemType, Rarity rarity, int strength, int health, int defense, Color color, CustomHeads customHeads, boolean isEnchanted, boolean isCombinableAnvil, List<String> description){
        this.id = id;
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.rarity = rarity;
        this.damage = 0;
        this.magicDamage = 0;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.color = color;
        this.customHeads = customHeads;
        this.isEnchanted = isEnchanted;
        this.isCombinableAnvil = isCombinableAnvil;
        this.description = description;
    }
    //Weapons
    CustomItemID(String id, String name, Material material, ItemType itemType, Rarity rarity, int damage, int strength, int magicDamage, boolean isEnchanted, boolean isCombinableAnvil, List<String> description){
        this.id = id;
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.rarity = rarity;
        this.damage = damage;
        this.strength = strength;
        this.magicDamage = magicDamage;
        this.health = 0;
        this.defense = 0;
        this.color = null;
        this.customHeads = null;
        this.isEnchanted = isEnchanted;
        this.isCombinableAnvil = isCombinableAnvil;
        this.description = description;
    }

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
    public int getStrength() {
        return strength;
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
    public int getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public Color getColor() {
        return color;
    }
    public CustomHeads getCustomHeads() {
        return customHeads;
    }
    public boolean getIsEnchanted() {
        return isEnchanted;
    }
    public boolean getIsCombinableAnvil() {
        return isCombinableAnvil;
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
    public static int getStrengthByID(String id) {
        return getCustomItemID(id)==null?0:getCustomItemID(id).getStrength();
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
    public static int getHealthByID(String id) {
        return getCustomItemID(id)==null?0:getCustomItemID(id).getHealth();
    }
    public static int getDefenseByID(String id) {
        return getCustomItemID(id)==null?0:getCustomItemID(id).getDefense();
    }
    public static Color getColorByID(String id) {
        return getCustomItemID(id)==null?null:getCustomItemID(id).getColor();
    }
    public static CustomHeads getCustomHeadsByID(String id) {
        return getCustomItemID(id)==null?null:getCustomItemID(id).getCustomHeads();
    }
    public static boolean getIsEnchantedByID(String id) {
        return getCustomItemID(id)==null?false:getCustomItemID(id).getIsEnchanted();
    }
    public static boolean getIsCombinableAnvilByID(String id) {
        return getCustomItemID(id)==null?false:getCustomItemID(id).getIsCombinableAnvil();
    }
    public static List<String> getDescriptionByID(String id) {
        return getCustomItemID(id)==null?null:getCustomItemID(id).getDescription();
    }


    public static boolean isWeapon(String id){
        return ItemType.isWeapon(getItemTypeByID(id));
    }
    public static boolean isArmor(String id){
        return ItemType.isArmor(getItemTypeByID(id));
    }
}
