package me.alwayslg.customplayers;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.alwayslg.SkyblockReborn;
import me.alwayslg.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomActionBar {
    private static CustomPlayer customPlayer;
    private static InterruptMessage interruptMessage;
    public CustomActionBar(CustomPlayer customPlayer) {
        CustomActionBar.customPlayer = customPlayer;
//        customPlayer.getStatsManager().getHealth();
//        customPlayer.getStatsManager().getMaxHealth();
        // main bukkit scheduler loop
        Bukkit.getScheduler().runTaskTimer(SkyblockReborn.getInstance(), () -> {
//            sendActionBar("");
            if(interruptMessage==null){
                String message = String.format("§c%s/%s❤", Utilities.numberFormatComma(customPlayer.getStatsManager().getHealth()), Utilities.numberFormatComma(customPlayer.getStatsManager().getMaxHealth()));
                sendActionBar(message);
            }
        }, 20L*1L /*<-- the initial delay */, 20L /*<-- the interval */);
    }

    public void addInterrupt(String message, int second) {
        // Since send action bar loop is run every second, there might be instance where the interrupt message
        // is shown right before the main loop is run. So we need to add a delay of 1 second to show the interrupt message
        // if variable "second" is 1, the message will be shown for range of 1-2 seconds
        interruptMessage = new InterruptMessage(message, second + 1);
        new BukkitRunnable(){
            @Override
            public void run() {
                sendActionBar(interruptMessage.message);
                interruptMessage.duration--;
                if(interruptMessage.duration<=0){
                    interruptMessage = null;
                    cancel();
                }
            }
        }.runTaskTimer(SkyblockReborn.getInstance(), 0L, 20L);
    }

    private void sendActionBar(String message) {
        ActionBarAPI.sendActionBar(customPlayer.getPlayer(), message);
    }
    private static class InterruptMessage {

        public String message;
        public int duration;
        public InterruptMessage(String message, int duration) {
            this.message = message;
            this.duration = duration;
        }
    }
}
