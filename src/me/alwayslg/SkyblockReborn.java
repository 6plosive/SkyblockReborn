package me.alwayslg;

import me.alwayslg.commands.GetItems;
import me.alwayslg.commands.SpawnZombie;
import me.alwayslg.customitems.AspectOfTheEnd;
import me.alwayslg.customitems.GUI;
import me.alwayslg.custommobs.CustomMob;
import me.alwayslg.custommobs.DamageHandler;
import me.alwayslg.custommobs.OverheadDisplayHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyblockReborn extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Skyblock Reborn is ENABLED! Hail Alwayslg!!!");
        GUI.init();
        OverheadDisplayHandler overheadDisplayManager = new OverheadDisplayHandler();

        getCommand("getitems").setExecutor(new GetItems());
        getCommand("spawnzombie").setExecutor(new SpawnZombie());
        getServer().getPluginManager().registerEvents(new GUI(),this);
        getServer().getPluginManager().registerEvents(new AspectOfTheEnd(),this);
        getServer().getPluginManager().registerEvents(new DamageHandler(),this);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                //TODO
            }
        };
    }

    @Override
    public void onDisable() {
        System.out.println("Skyblock Reborn is DISABLED! Hail Alwayslg!!!!!");
    }
}
