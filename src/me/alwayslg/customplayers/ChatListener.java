package me.alwayslg.customplayers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.alwayslg.customplayers.CustomPlayerManager.getPlayerRank;

public class ChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        Rank rank = getPlayerRank(player.getUniqueId());
        event.setCancelled(true);
        Bukkit.broadcastMessage(String.format("%s%s%s: %s",rank.getChatPrefix(),player.getName(),rank.getChatColor(),message));
    }
}
