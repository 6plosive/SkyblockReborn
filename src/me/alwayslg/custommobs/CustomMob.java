package me.alwayslg.custommobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class CustomMob {
    private int fullHealth;
    private String name;
    private int level;
    private HealthBar healthBar;
    private LivingEntity entity;
    private Class<? extends Entity> entityClass;

    public CustomMob(){
        healthBar =new HealthBar();
    }

    public void spawn(Location location){
        World world = location.getWorld();
        entity = (LivingEntity) world.spawn(location,entityClass);
//        Bukkit.broadcastMessage("Spawned mob UUID: "+entity.getUniqueId());
        entity.setMaxHealth(fullHealth);
        setHealth(fullHealth);
        entity.setMaximumNoDamageTicks(10);
//        entity.setMetadata("Custom", new FixedMetadataValue(SkyblockReborn.getPlugin(SkyblockReborn.class),true));
        DamageHandler.addMob(this);

        healthBar.setText(String.format("§8[§7Lv%d§8] §c%s §a%d§f/§a%d§c❤",getLevel(),getName(),(int)getHealth(),getFullHealth()));
        healthBar.spawn(entity);
        HealthBarHandler.addDisplay(healthBar);
    }



    public int getLevel(){
        return level;
    }
    public double getHealth(){
        return entity.getHealth();
    }
    public int getFullHealth() {
        return fullHealth;
    }
    public String getName() {
        return name;
    }
    public LivingEntity getEntity() {
        return entity;
    }
    public HealthBar getOverheadDisplay() {
        return healthBar;
    }

    public void setLevel(int level){
        this.level=level;
    }
    public void setHealth(double health){
        entity.setHealth(health);
    }
    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }
    public void setName(String name) {
        this.name = name;
    }
    public <T extends Entity> void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
    public void setOverheadDisplay(HealthBar healthBar) {
        this.healthBar = healthBar;
    }
}
