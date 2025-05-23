package me.alwayslg.custommobs;

import me.alwayslg.custommobs.customentities.CustomEntityWither;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;

public enum CustomMobID {
    ZOMBIE("ZOMBIE","Graveyard Zombie",1,100,20,Zombie.class),
    BABY_NECRON("BABY_NECRON","Baby Necron",69,1000000,0, Wither.class, CustomEntityWither.class),
    GOLEM("GOLEM","Whigger",200,200000,100,IronGolem.class);
    final String id;
    final String name;
    final int level;
    final int fullHealth;
    final int damage;
    final Class<? extends Entity> entityClass;
    final Class<? extends net.minecraft.server.v1_8_R3.Entity> customEntityClass;

    CustomMobID(String id,String name,int level,int fullHealth,int damage,Class<? extends Entity> entityClass){
        this(id,name,level,fullHealth,damage,entityClass,null);
    }
    CustomMobID(String id, String name, int level, int fullHealth, int damage, Class<? extends Entity> entityClass, Class<? extends net.minecraft.server.v1_8_R3.Entity> customEntityClass){
        this.id = id;
        this.name = name;
        this.level = level;
        this.fullHealth = fullHealth;
        this.damage = damage;
        this.entityClass = entityClass;
        this.customEntityClass = customEntityClass;
    }


    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getFullHealth() {
        return fullHealth;
    }
    public int getDamage() {
        return damage;
    }
    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
    public Class<? extends net.minecraft.server.v1_8_R3.Entity> getCustomEntityClass(){
        return customEntityClass;
    }
}
