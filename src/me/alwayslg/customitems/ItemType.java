package me.alwayslg.customitems;

public enum ItemType {
    SWORD("SWORD"),
    PICKAXE("PICKAXE"),
    WAND("WAND"),
    HELMET("HELMET"),
    CHESTPLATE("CHESTPLATE"),
    LEGGINGS("LEGGINGS"),
    BOOTS("BOOTS"),
    ACCESSORY("ACCESSORY"),
    DUNGEON_SWORD("DUNGEON SWORD"),
    DUNGEON_BOW("DUNGEON BOW"),
    SHORTBOW("SHORTBOW"),
    BOW("BOW"),
    LONGSWORD("LONGSWORD");
//    NULL("NULL");
    final private String name;
    private ItemType(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }
    public static boolean isWeapon(ItemType type){
        return type==SWORD ||
                type==BOW ||
                type==WAND ||
                type==DUNGEON_SWORD ||
                type==DUNGEON_BOW ||
                type==LONGSWORD ||
                type==SHORTBOW;
    }
    public static boolean isArmor(ItemType type){
        return type==HELMET ||
                type==CHESTPLATE ||
                type==LEGGINGS ||
                type==BOOTS;
    }
}
