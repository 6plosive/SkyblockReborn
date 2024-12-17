package me.alwayslg.custommobs;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class CustomMobManager {
    private static HashMap<UUID,CustomMob> customMobs = new HashMap<>();

    public static CustomMob getCustomMob(UUID entityUUID){
        return customMobs.get(entityUUID);
    }
    public static Collection<CustomMob> getCustomMobValues(){
        return customMobs.values();
    }

    public static void addMob(CustomMob mob){
        customMobs.put(mob.getEntity().getUniqueId(), mob);
    }
    public static void removeMob(UUID deathEntityUUID){
        if(customMobs.get(deathEntityUUID)!=null){
            CustomMob customMob = customMobs.get(deathEntityUUID);
            //Remove overhead display
            HealthBarHandler.removeDisplay(customMob.getHealthBar());
            //Remove from map
            customMobs.remove(deathEntityUUID);
        }
    }
}
