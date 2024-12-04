package me.alwayslg.custommobs;

import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class HealthBarHandler {
    private static ArrayList<HealthBar> displays = new ArrayList<>();
    public HealthBarHandler(){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            for(HealthBar display : displays){
                display.updatePerTick();
//                LivingEntity erm = (LivingEntity) display.getTarget();
//                Bukkit.broadcastMessage(erm.getName()+":"+erm.getHealth());
            }
        }, 20L*1L /*<-- the initial delay */, 1L * 1L /*<-- the interval */);
    }
    public static void addDisplay(HealthBar display){
        displays.add(display);
    }
    public static void removeDisplay(HealthBar display){
        display.kill();
        displays.remove(display);
    }
}
