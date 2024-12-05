package me.alwayslg.custommobs;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class CustomMob {
//    private int fullHealth;
//    private String name;
//    private int level;
    private HealthBar healthBar;
    private LivingEntity entity;
//    private Class<? extends Entity> entityClass;
    private double health;
    private double healthMultiplier;
    private CustomMobID customMobID;

//    public CustomMob(){
//        healthBar =new HealthBar();
//    }
    public CustomMob(CustomMobID customMobID){
        this.healthBar = new HealthBar();
        this.customMobID = customMobID;
    }

    public void spawn(Location location){
        World world = location.getWorld();
        entity = (LivingEntity) world.spawn(location,getEntityClass());
//        entity.getMaxHealth()
//        Bukkit.broadcastMessage("Spawned mob UUID: "+entity.getUniqueId());
//        entity.setMaxHealth(getFullHealth());
        double realFullHealth = entity.getMaxHealth();
        healthMultiplier = getFullHealth()/realFullHealth;
        setHealth(getFullHealth());
//        entity.setMaximumNoDamageTicks(10);
//        entity.setMetadata("Custom", new FixedMetadataValue(SkyblockReborn.getPlugin(SkyblockReborn.class),true));
        DamageHandler.addMob(this);

        healthBar.setText(String.format("§8[§7Lv%d§8] §c%s §a%d§f/§a%d§c❤",getLevel(),getName(),(int)getHealth(),getFullHealth()));
        healthBar.spawn(entity);
        HealthBarHandler.addDisplay(healthBar);
    }



    public int getLevel(){
        return customMobID.getLevel();
    }
//    public double getHealth(){
//        return entity.getHealth();
//    }
    public double getHealth(){
        return health;
    }
    public int getFullHealth() {
        return customMobID.getFullHealth();
    }
    public String getName() {
        return customMobID.getName();
    }
    public Class<? extends Entity> getEntityClass(){
        return customMobID.getEntityClass();
    }
    public LivingEntity getEntity() {
        return entity;
    }
    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealth(double health){
        this.health = health;
    }
    public void setRealHealth(double health){
        entity.setHealth(health);
    }
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public double calculateRealHealth(double health){
        return health/healthMultiplier;
    }
}
