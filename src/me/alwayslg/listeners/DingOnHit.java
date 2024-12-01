package me.alwayslg.listeners;
import me.alwayslg.SkyblockReborn;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;


public class DingOnHit implements Listener{
    // Constructor to register the listener
    public DingOnHit() {
        Bukkit.getPluginManager().registerEvents(this, SkyblockReborn.getInstance());
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        // Check if the damager is a projectile (like an arrow)
        if (damager instanceof Arrow) {
            Arrow arrow = (Arrow) damager;
            ProjectileSource shooter = arrow.getShooter(); // Get the shooter

            // Check if the shooter is a player
            if (shooter instanceof Player) {
                Player player = (Player) shooter;

                // Play the sound to the player
                arrow.remove();
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 0.8f);

                // Optionally, you can add more logic here (e.g., sending a message)
            }
        }
    }

}
