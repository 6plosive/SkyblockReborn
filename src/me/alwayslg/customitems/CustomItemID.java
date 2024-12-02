package me.alwayslg.customitems;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.Map;

public enum CustomItemID {
    ASPECT_OF_THE_END("ASPECT_OF_THE_END","Aspect Of The End",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.RARE,100),
    BONZO_STAFF("BONZO_STAFF","Bonzo's Staff",Material.BLAZE_ROD,ItemType.WAND,Rarity.LEGENDARY,20),
    BOOMERANG("BOOMERANG","Bonemerang",Material.BONE,ItemType.DUNGEON_BOW,Rarity.LEGENDARY,69),
    DIAMOND_SWORD("DIAMOND_SWORD","Diamond Sword",Material.DIAMOND_SWORD,ItemType.SWORD,Rarity.UNCOMMON,35),
    JER_JER_SHORTBOW("JER_JER_SHORTBOW","Jer Jer Shortbow",Material.BOW,ItemType.BOW,Rarity.EPIC,20),
    TERMINATOR("TERMINATOR","Terminator",Material.BOW,ItemType.BOW,Rarity.LEGENDARY,50);
    private final String id;
    private final String name;
    private final Material material;
    private final ItemType itemType;
    private final Rarity rarity;
    private final int damage;
    private static final Map<String, CustomItemID> BY_ID = Maps.newHashMap();
    CustomItemID(String id, String name, Material material, ItemType itemType, Rarity rarity, int damage){
        this.id = id;
        this.name = name;
        this.material = material;
        this.itemType = itemType;
        this.rarity = rarity;
        this.damage = damage;
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
    public Material getMaterial() {
        return material;
    }
    public String getName() {
        return name;
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
        Bukkit.broadcastMessage("Damage for "+id+":"+getCustomItemID(id));
        return getCustomItemID(id)==null?0:getCustomItemID(id).getDamage();
    }
    public static Material getMaterialByID(String id){
        return getCustomItemID(id)==null?null:getCustomItemID(id).getMaterial();
    }
    public static String getNameByID(String id){
        return getCustomItemID(id)==null?null:getCustomItemID(id).getName();
    }
}
