package me.alwayslg.custommobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;

public enum CustomMobID {
    ZOMBIE("ZOMBIE","Graveyard Zombie",1,100, Zombie.class),
    BABY_NECRON("BABY_NECRON","Baby Necron",69,20000, Wither.class),
    GOLEM("GOLEM","Whigger",200,2000, IronGolem.class);
    final String id;
    final String name;
    final int level;
    final int fullHealth;
    final Class<? extends Entity> entityClass;

    CustomMobID(String id,String name,int level,int fullHealth,Class<? extends Entity> entityClass){
        this.id = id;
        this.name = name;
        this.level = level;
        this.fullHealth = fullHealth;
        this.entityClass = entityClass;
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
    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}
