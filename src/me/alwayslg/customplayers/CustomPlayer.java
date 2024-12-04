package me.alwayslg.customplayers;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomPlayer implements Listener {
    private Player player;
    private CustomScoreboard customScoreboard;
    private Rank rank;
    public CustomPlayer(){

    };
    public CustomPlayer(Player player){
        customScoreboard = new CustomScoreboard(player);
        customScoreboard.setDisplayName("§e§lSKYBLOCK §b§lREBORN");
//        String formattedDate = new DateTimeFormatter().f.format(LocalDate.now());
        customScoreboard.updateScore();
        this.rank=Rank.ADMIN;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public CustomScoreboard getCustomScoreboard() {
        return customScoreboard;
    }
    public void setCustomScoreboard(CustomScoreboard customScoreboard) {
        this.customScoreboard = customScoreboard;
    }
    private Rank getPlayerRank(Player player){
        return Rank.ADMIN;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new CustomPlayer(event.getPlayer());
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        Rank rank = getPlayerRank(player);
        event.setCancelled(true);
        Bukkit.broadcastMessage(String.format("%s%s%s: %s",rank.getChatPrefix(),player.getDisplayName(),rank.getChatColor(),message));
    }
}
