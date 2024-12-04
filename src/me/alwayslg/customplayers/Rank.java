package me.alwayslg.customplayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Rank {
    NON("",ChatColor.GRAY),
    VIP("§a[VIP] ",ChatColor.WHITE),
    VIPPLUS("§a[VIP§e+] ",ChatColor.WHITE),
    MVP("§b[MVP] ",ChatColor.WHITE),
    MVPPLUS("§b[MVP§c+] ",ChatColor.WHITE),
    MVPPLUSPLUS("§b[MVP§c++] ",ChatColor.WHITE),
    YOUTUBE("§c[§fYOUTUBE§c] ",ChatColor.WHITE),
    ADMIN("§c[ADMIN] ",ChatColor.WHITE),
    OWNER("§c[OWNER] ",ChatColor.WHITE);
    private String chatPrefix;
    private ChatColor chatColor;
    private Rank(String chatPrefix,ChatColor chatColor){
        this.chatPrefix=chatPrefix;
        this.chatColor=chatColor;
    }
//    public void sendMessageWithRank(Player player){
//        Bukkit.broadcastMessage(String.format("%s%s%s: %s",chatPrefix,player.getDisplayName(),chatColor));
//    }
    public String getChatPrefix() {
        return chatPrefix;
    }
    public ChatColor getChatColor() {
        return chatColor;
    }
}
