package me.alwayslg.custommobs;

import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class OverheadDisplayHandler {
    private static ArrayList<OverheadDisplay> displays = new ArrayList<>();
    public OverheadDisplayHandler(){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            for(OverheadDisplay display : displays){
                display.updatePerTick();
//                LivingEntity erm = (LivingEntity) display.getTarget();
//                Bukkit.broadcastMessage(erm.getName()+":"+erm.getHealth());
            }
        }, 20L*1L /*<-- the initial delay */, 1L * 1L /*<-- the interval */);
    }
    public static void addDisplay(OverheadDisplay display){
        displays.add(display);
    }
}
