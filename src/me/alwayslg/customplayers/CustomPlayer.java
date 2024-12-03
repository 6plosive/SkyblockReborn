package me.alwayslg.customplayers;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomPlayer implements Listener {
    private Player player;
    private CustomScoreboard customScoreboard;
    public CustomPlayer(){

    };
    public CustomPlayer(Player player){
        customScoreboard = new CustomScoreboard(player);
        customScoreboard.setDisplayName("§e§lSKYBLOCK §b§lREBORN");
//        String formattedDate = new DateTimeFormatter().f.format(LocalDate.now());
        customScoreboard.updateScore();
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
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new CustomPlayer(event.getPlayer());
    }
}
