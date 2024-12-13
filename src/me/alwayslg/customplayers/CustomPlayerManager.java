package me.alwayslg.customplayers;

import me.alwayslg.SkyblockReborn;
import me.alwayslg.custommobs.CustomMob;
import me.alwayslg.custommobs.HealthBar;
import me.alwayslg.custommobs.HealthBarHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static me.alwayslg.SkyblockReborn.DB_URL;

public class CustomPlayerManager {
    private static HashMap<UUID, CustomPlayer> customPlayers = new HashMap<>();
    public CustomPlayerManager(){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(SkyblockReborn.getInstance(), () -> {
            try{
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(DB_URL);
//                setPurseToDB(conn, player.getUniqueId(), purse);
                for(CustomPlayer customPlayer : customPlayers.values()){
                    long purse = customPlayer.getPurse();
                    setPurseToDB(conn, customPlayer.getPlayer().getUniqueId(), purse);
                    System.out.println("Updated db:"+customPlayer.getPlayer().getName()+":"+purse);
                }
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }, 20L*1L /*<-- the initial delay */, 60L * 20L /*<-- the interval */);
    }
    public static void addPlayer(CustomPlayer player){
        customPlayers.put(player.getPlayer().getUniqueId(), player);
    }
    public static void removePlayer(UUID playerUUID){
        if(customPlayers.get(playerUUID)!=null){
            CustomPlayer customPlayer = customPlayers.get(playerUUID);
            //Remove from map
            customPlayers.remove(playerUUID);
        }
    }
    public static CustomPlayer getCustomPlayer(UUID playerUUID){
        return customPlayers.get(playerUUID);
    }
    public static Rank getPlayerRank(UUID playerUUID){
        CustomPlayer customPlayer = customPlayers.get(playerUUID);
        return customPlayer.getRank();
    }
    public static long getPlayerPurse(UUID playerUUID){
        CustomPlayer customPlayer = customPlayers.get(playerUUID);
        return customPlayer.getPurse();
    }

    public static void setRankToDB(Connection connection, UUID playerUUID, Rank rank){
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE users SET rank = ? where uuid = ?")) {
            stmt.setString(1, rank.toString());
            stmt.setString(2, playerUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setPurseToDB(Connection conn, UUID playerUUID, long purse){
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE users SET purse = ? where uuid = ?")) {
            stmt.setLong(1, purse);
            stmt.setString(2, playerUUID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
