package me.alwayslg.custommobs;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

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
    private boolean noAI;

//    public CustomMob(){
//        healthBar =new HealthBar();
//    }
    public CustomMob(CustomMobID customMobID){
        this.healthBar = new HealthBar();
        this.customMobID = customMobID;
    }

    public void spawn(Location location, boolean noAI){
        World world = location.getWorld();
        entity = (LivingEntity) world.spawn(location,getEntityClass());
//        entity.getMaxHealth()
//        Bukkit.broadcastMessage("Spawned mob UUID: "+entity.getUniqueId());
//        entity.setMaxHealth(getFullHealth());
//        if(noAI) {
//            net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
//            NBTTagCompound tag = nmsEntity.getNBTTag();
//            if (tag == null) {
//                tag = new NBTTagCompound();
//            }
//            nmsEntity.c(tag);
//            tag.setInt("NoAI", 1);
//            nmsEntity.f(tag);
////            entity = (LivingEntity) nmsEntity;
//            entity = (LivingEntity) nmsEntity.getBukkitEntity();
////            entity = (LivingEntity) ((CraftLivingEntity) ((CraftEntity) nmsEntity.getBukkitEntity()).getHandle()).getBukkitEntity();        }
//        }
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
        setRealHealth(health);
    }
    private void setRealHealth(double health){
        double realHealth = calculateRealHealth(health);
        entity.setHealth(realHealth);
    }
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    private double calculateRealHealth(double health){
        return health/healthMultiplier;
    }
}
