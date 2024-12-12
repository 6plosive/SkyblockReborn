package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.UUID;

public class Cooldown {
    static ArrayList<UUID> cooldownItems = new ArrayList<>();
    public static boolean hasCooldown(UUID itemUUID){
        return cooldownItems.contains(itemUUID);
    }
    public static void addCooldown(UUID itemUUID, int ticks){
        cooldownItems.add(itemUUID);
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            cooldownItems.remove(itemUUID);
        }, ticks); // 20 ticks = 1 second
    }
}
