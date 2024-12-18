package me.alwayslg.customplayers;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.alwayslg.SkyblockReborn;
import me.alwayslg.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomActionBar {
    private final CustomPlayer customPlayer;
    private InterruptMessage interruptMessage;
    private int counter = 0;
    private int oldHealth = 0;
    private int oldMaxHealth = 0;
    public CustomActionBar(CustomPlayer customPlayer) {
        this.customPlayer = customPlayer;
        // main bukkit scheduler loop
        Bukkit.getScheduler().runTaskTimer(SkyblockReborn.getInstance(), () -> {
//            sendActionBar("");
            customPlayer.getStatsManager().updateMaxHealth();
            customPlayer.getStatsManager().updateHeart();
            boolean shouldUpdate = oldHealth != customPlayer.getStatsManager().getHealth() || oldMaxHealth != customPlayer.getStatsManager().getMaxHealth();
            //interrupt message takes priority
            if(interruptMessage!=null) {
                // if interrupt is not divisible by 20 at first, it will not run the first second
                // add check for first run
                if(counter!=-1){
                    sendActionBar(interruptMessage.message);
                }
                else if(interruptMessage.duration%20==0&&interruptMessage.duration>0){// run every second
                    sendActionBar(interruptMessage.message);
                }
                interruptMessage.duration--;
                if (interruptMessage.duration <= 0) {
                    interruptMessage = null;
                }
                counter=-1;
            } else {
                if(shouldUpdate) {
                    String message = String.format("§c%s/%s❤", Utilities.numberFormatComma(customPlayer.getStatsManager().getHealth()), Utilities.numberFormatComma(customPlayer.getStatsManager().getMaxHealth()));
                    sendActionBar(message);
                    counter = 0;
                }else{
                    counter++;
                    if(counter%20==0){
                        String message = String.format("§c%s/%s❤", Utilities.numberFormatComma(customPlayer.getStatsManager().getHealth()), Utilities.numberFormatComma(customPlayer.getStatsManager().getMaxHealth()));
                        sendActionBar(message);
                    }
                }
            }
//            customPlayer.getPlayer().sendMessage("Counter: "+counter);
            oldHealth = customPlayer.getStatsManager().getHealth();
            oldMaxHealth = customPlayer.getStatsManager().getMaxHealth();
        }, 20L*0L /*<-- the initial delay */, 1L /*<-- the interval */);
    }

    public void addInterrupt(String message, int tick) {
        // Since send action bar loop is run every second, there might be instance where the interrupt message
        // is shown right before the main loop is run. So we need to add a delay of 1 second to show the interrupt message
        // if variable "second" is 1, the message will be shown for range of 1-2 seconds
        interruptMessage = new InterruptMessage(message, tick);
//        new BukkitRunnable(){
//            @Override
//            public void run() {
//                sendActionBar(interruptMessage.message);
//                interruptMessage.duration--;
//                if(interruptMessage.duration<=0){
//                    interruptMessage = null;
//                    cancel();
//                }
//            }
//        }.runTaskTimer(SkyblockReborn.getInstance(), 0L, 20L);
    }

    private void sendActionBar(String message) {
        ActionBarAPI.sendActionBar(customPlayer.getPlayer(), message);
    }
    private static class InterruptMessage {

        public String message;
        public int duration;
        public InterruptMessage(String message, int durationTicks) {
            this.message = message;
            this.duration = durationTicks;
        }
    }
}
