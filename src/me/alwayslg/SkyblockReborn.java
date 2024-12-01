package me.alwayslg;

import me.alwayslg.commands.GetItems;
import me.alwayslg.commands.SpawnZombie;
import me.alwayslg.customitems.*;
import me.alwayslg.custommobs.CustomMob;
import me.alwayslg.custommobs.DamageHandler;
import me.alwayslg.custommobs.OverheadDisplayHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyblockReborn extends JavaPlugin {
    private static SkyblockReborn instance; // Singleton instance

    @Override
    public void onEnable() {
        instance = this; // Set the instance to this
        System.out.println("Skyblock Reborn is ENABLED! Hail Alwayslg!!!");

        // Initialize your classes and register events
        new GUI();
        new OverheadDisplayHandler();

        getCommand("getitems").setExecutor(new GetItems());
        getCommand("spawnzombie").setExecutor(new SpawnZombie());
        getServer().getPluginManager().registerEvents(new GUI(), this);
        getServer().getPluginManager().registerEvents(new AspectOfTheEnd(), this);
        getServer().getPluginManager().registerEvents(new DamageHandler(), this);
        getServer().getPluginManager().registerEvents(new JerJerShortBow(), this);
        getServer().getPluginManager().registerEvents(new Terminator(), this);
        getServer().getPluginManager().registerEvents(new Boomerang(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Skyblock Reborn is DISABLED! Hail Alwayslg!!!!!");
    }

    // Method to get the instance of the plugin
    public static SkyblockReborn getInstance() {
        return instance;
    }
}