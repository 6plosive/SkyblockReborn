package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.UUID;

public class Cooldown {
    static ArrayList<UUID> cooldownItems = new ArrayList<>();
    public static boolean hasCooldown(UUID uuid){
        return cooldownItems.contains(uuid);
    }
    public static void addCooldown(UUID uuid, int ticks){
        cooldownItems.add(uuid);
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            cooldownItems.remove(uuid);
        }, ticks); // 20 ticks = 1 second
    }
}
