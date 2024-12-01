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
}
