package me.alwayslg.customplayers;

import me.alwayslg.customitems.unique.SkyblockMenu;
import me.alwayslg.customplayers.stats.StatsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.*;
import java.util.UUID;

import static me.alwayslg.SkyblockReborn.DB_URL;

public class CustomPlayer implements Listener {
    private Player player;
    private CustomScoreboard customScoreboard;
    private Rank rank;
    private long purse;
    private StatsManager statsManager;
    private CustomActionBar customActionBar;
    public CustomPlayer(){}
    public CustomPlayer(Player player) {
        this.player = player;

        // Add for player to customplayer translation
        CustomPlayerManager.addPlayer(this);

        // Init Custom scoreboard and Update purse amount
        this.customScoreboard = new CustomScoreboard(player);

        // Get purse, rank (more in future) from sqlite db
        try {
            String playerName = player.getName();
            UUID playerUUID = player.getUniqueId();

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            // Create row for user if this is his first time join
            createUserInDBIfNotExists(conn, playerUUID, playerName);
            // Get his info
            getUserFromDB(conn, playerUUID);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // Get Stats
        this.statsManager = new StatsManager(this);
        // Set Action bar
        this.customActionBar =  new CustomActionBar(this);
        // Set Skyblock Menu on slot 9
        player.getInventory().setItem(8,new SkyblockMenu());
        // Set saturation to max
        player.setSaturation(20);
    }

    public Player getPlayer() {
        return player;
    }

    public CustomScoreboard getCustomScoreboard() {
        return customScoreboard;
    }

    public Rank getRank(){
        return this.rank;
    }
    public void setRank(Rank rank){
        this.rank=rank;
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            CustomPlayerManager.setRankToDB(conn, player.getUniqueId(), rank);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long getPurse(){
        return this.purse;
    }
    public void setPurse(long purse){
        this.purse = purse;
//        this.customScoreboard.setPurse(purse);
        customScoreboard.updateScore();
    }
    public StatsManager getStatsManager() {
        return statsManager;
    }
    public CustomActionBar getCustomActionBar() {
        return customActionBar;
    }

    private void createUserInDBIfNotExists(Connection connection, UUID playerUUID, String username) throws SQLException {
        try (PreparedStatement select = connection.prepareStatement("SELECT * FROM users WHERE uuid = ?");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO users (uuid, username) values (?,?)")) {

            select.setString(1, playerUUID.toString());
            ResultSet rs = select.executeQuery();

            if (!rs.next()) {
                insert.setString(1, playerUUID.toString());
                insert.setString(2, username);
                insert.executeUpdate();
            }
        }
    }
    private void getUserFromDB(Connection connection, UUID playerUUID) throws SQLException {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE uuid = ?")) {
            stmt.setString(1, playerUUID.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("username");
//                this.purse = rs.getInt("purse");
                setPurse(rs.getLong("purse"));
                this.rank = Rank.valueOf(rs.getString("rank"));
                System.out.println(name + "'s purse: " + purse + " rank:" + rank);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new CustomPlayer(event.getPlayer());
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        CustomPlayerManager.removePlayer(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onHungerDeplete(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

}
