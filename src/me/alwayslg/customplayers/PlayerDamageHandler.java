package me.alwayslg.customplayers;

import me.alwayslg.custommobs.CustomMob;
import me.alwayslg.custommobs.CustomMobManager;
import me.alwayslg.util.Utilities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageHandler implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(player.getUniqueId());
            if (customPlayer == null) {
                return;
            }
            if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                // custom fall damage
//                event.setCancelled(true);
                double originalFallDamage = event.getDamage();
                customPlayer.getStatsManager().dealFallDamage((int) originalFallDamage);
                event.setDamage(0);
            }else if(event.getCause() == EntityDamageEvent.DamageCause.VOID){
                // Broadcast death message
                customPlayer.getPlayer().sendMessage(" §c☠ §7You fell into the void.");
                String playerNameColor = customPlayer.getRank().getChatPrefix().substring(0,2);
                Utilities.broadcastMessageExcept(String.format(" §c☠ %s%s §7fell into the void.",playerNameColor,customPlayer.getPlayer().getName()), customPlayer.getPlayer());
                // immediate death
                customPlayer.respawn();

                event.setDamage(0);
            }
        }
    }
    @EventHandler
    public void onPlayerDamageByCustomMob(EntityDamageByEntityEvent event){
//        Entity damager = event.getDamager();
//        Entity damaged = event.getEntity();
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(player.getUniqueId());
            if(customPlayer==null){
                return;
            }
            // if the damager is a custom mob
            if(isMob(event.getDamager())){
                LivingEntity mob = (LivingEntity) event.getDamager();
                CustomMob customMob = CustomMobManager.getCustomMob(mob.getUniqueId());
                if(customMob==null){
                    return;
                }
                assert customMob != null;
                event.setDamage(0);
                customPlayer.getStatsManager().dealDamage(customMob.getDamage(),customMob.getCustomMobID());
            }
//            if(event.getDamager() instanceof Player){
//                Player damager = (Player) event.getDamager();
//                CustomPlayer customDamager = CustomPlayerManager.getCustomPlayer(damager.getUniqueId());
//                if(customDamager==null){
//                    return;
//                }
//                // Check if the player is in the same party
//                if(customPlayer.getParty()!=null && customDamager.getParty()!=null){
//                    if(customPlayer.getParty().equals(customDamager.getParty())){
//                        // Cancel friendly fire
//                        event.setCancelled(true);
//                    }
//                }
//            }
        }
    }

    public static boolean isMob(Entity entity) {
        return entity instanceof LivingEntity && !(entity instanceof Player);
    }

}
