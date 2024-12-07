package me.alwayslg.customplayers;

import me.alwayslg.custommobs.CustomMob;
import me.alwayslg.custommobs.HealthBarHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CustomPlayerManager {
    private static HashMap<UUID, CustomPlayer> customPlayers = new HashMap<>();
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
    public static Rank getPlayerRank(UUID playerUUID){
        CustomPlayer customPlayer = customPlayers.get(playerUUID);
        if(customPlayer==null)return Rank.NON;
        return customPlayer.getRank();
    }
}
