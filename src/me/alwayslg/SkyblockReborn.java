package me.alwayslg;

import me.alwayslg.commands.*;
import me.alwayslg.customitems.*;
import me.alwayslg.custommobs.BabyNecron;
import me.alwayslg.custommobs.DamageHandler;
import me.alwayslg.custommobs.HealthBarHandler;
import me.alwayslg.customplayers.ChatListener;
import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
import me.alwayslg.listeners.DingOnHit;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.java.JavaPlugin;


public class SkyblockReborn extends JavaPlugin {
    private static SkyblockReborn instance; // Singleton instance
    public static final String DB_URL = "jdbc:sqlite:F:/Projects/Java/SkyblockReborn/db.db"; // SQLite file in project root


    @Override
    public void onEnable() {
        instance = this; // Set the instance to this
        System.out.println("Skyblock Reborn is ENABLED! Hail Alwayslg!!!");

        // Initialize your classes and register events
        new GUI();
        new HealthBarHandler();
        new CustomPlayerManager();

        getCommand("getitems").setExecutor(new GetItems());
        getCommand("spawnzombie").setExecutor(new SpawnZombie());
        getCommand("spawnnecron").setExecutor(new SpawnNecron());
        getCommand("spawngolem").setExecutor(new SpawnGolem());
        getCommand("test").setExecutor(new testCommand());
        getCommand("ride").setExecutor(new RideCommand());
        getCommand("setpurse").setExecutor(new SetPurse());
        getCommand("setrank").setExecutor(new SetRank());
        getServer().getPluginManager().registerEvents(new GUI(), this);
        getServer().getPluginManager().registerEvents(new AspectOfTheEnd(), this);
        getServer().getPluginManager().registerEvents(new DamageHandler(), this);
        getServer().getPluginManager().registerEvents(new JerJerShortBow(), this);
        getServer().getPluginManager().registerEvents(new Terminator(), this);
        getServer().getPluginManager().registerEvents(new Boomerang(), this);
        getServer().getPluginManager().registerEvents(new BonzoStaff(), this);
        getServer().getPluginManager().registerEvents(new Hyperion(), this);
        getServer().getPluginManager().registerEvents(new DingOnHit(), this);
        getServer().getPluginManager().registerEvents(new GiantsSword(), this);
        getServer().getPluginManager().registerEvents(new WitherShortBow(), this);
        getServer().getPluginManager().registerEvents(new SkyblockMenu(), this);

        getServer().getPluginManager().registerEvents(new CustomPlayer(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);


//        CustomPlayer alwayslg = new CustomPlayer(Bukkit.getPlayer("Alwayslg"));
//        CustomPlayer filipinC5 = new CustomPlayer(Bukkit.getPlayer("FilipinC5"));

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