package me.alwayslg.customplayers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;

import static me.alwayslg.SkyblockReborn.DB_URL;

public class CustomPlayer implements Listener {
    private Player player;
    private CustomScoreboard customScoreboard;
    private Rank rank;
    private int purse;
    public CustomPlayer(){

    };
    public CustomPlayer(Player player) {
        this.player = player;
        // Get purse, rank (more in future) from sqlite db
        try {
            String playerName = player.getName();
            String playerUUID = player.getUniqueId().toString();

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);

            createUserInDBIfNotExists(conn, playerUUID, playerName);

            getUserFromDB(conn, playerUUID);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Init Custom scoreboard and Update purse amount
        this.customScoreboard = new CustomScoreboard(player);
        this.customScoreboard.setPurse(purse);
        this.customScoreboard.updateScore();

        // Add for player to customplayer translation
        CustomPlayerManager.addPlayer(this);
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
    public Rank getRank(){
        return rank;
    }
    private void createUserInDBIfNotExists(Connection connection, String playerUUID, String username) throws SQLException {
        try (PreparedStatement select = connection.prepareStatement("SELECT * FROM users WHERE uuid = ?");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO users (uuid, username) values (?,?)")) {

            select.setString(1, playerUUID);
            ResultSet rs = select.executeQuery();

            if (!rs.next()) {
                insert.setString(1, playerUUID);
                insert.setString(2, username);
                insert.executeUpdate();
            }
        }
    }
    private void getUserFromDB(Connection connection, String playerUUID) throws SQLException {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE uuid = ?")) {
            stmt.setString(1, playerUUID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("username");
                this.purse = rs.getInt("purse");
                this.rank = Rank.valueOf(rs.getString("rank"));
                player.sendMessage(name + "'s purse: " + purse + " rank:" + rank);
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new CustomPlayer(event.getPlayer());
    }

}
