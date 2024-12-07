package me.alwayslg.customplayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import static me.alwayslg.util.Utilities.numberFormatComma;

public class CustomScoreboard {

    private final ScoreboardManager manager;
    private final Scoreboard scoreboard;
    private Collection<String> entries;
    private Objective objective;
    private Player player;
    private int purse;

    public CustomScoreboard(Player player) {
        manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
        entries=new ArrayList<>();
        objective = scoreboard.registerNewObjective("SkyblockReborn", player.getDisplayName());
//        objective.setDisplayName(ChatColor.GOLD + "Custom Scoreboard");
        setDisplayName("§e§lSKYBLOCK §b§lREBORN");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        purse = 0;
        this.player = player;

        player.setScoreboard(scoreboard);
    }

    private Collection<String> getEntries() {
        return entries;
    }
    private void setEntries(Collection<String> entries) {
        this.entries = entries;
    }

    public void setPurse(int purse){
        this.purse = purse;
        updateScore();
    }

    public void updateScore() {
        Collection<String> tempEntries = new ArrayList<>();
        //Date
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String formattedDate = date.format(formatter);
        tempEntries.add("§7"+formattedDate+" §8m9BP");
        //Gap
        tempEntries.add(" ");
        //In game date
//        ChatColor.COLOR_CHAR
        tempEntries.add(" §fLate Summer 6th");
        //In game time
        tempEntries.add(" §79:45pm §b☽");
        //In game location
        tempEntries.add(" §7⏣ §bVillage");
        //Gap
        tempEntries.add("  ");
        //Purse
        tempEntries.add("§fPurse: §6"+numberFormatComma(purse));
        //Bits
        tempEntries.add("§fBits: §b941,520");
        //Gap
        tempEntries.add("   ");
        //alwayslg.com
        tempEntries.add("§emc.alwayslg.com");

        setEntries(tempEntries);
        resetObjective(player);
        // Set scoreboard with entries
        int i = getEntries().size();
        for(String entry:getEntries()){
            setScore(entry,i);
            i--;
        }
    }
    private void setScore(String entry, int score){
        Score scoreEntry = objective.getScore(entry);
        scoreEntry.setScore(score);
    }
    public void setDisplayName(String s){
        objective.setDisplayName(s);
    }
    private void resetObjective(Player player){
//        if(entries != null && !entries.isEmpty()) {
//            for (String entry : entries) {
//                scoreboard.resetScores(entry);
//            }
//        }
        objective.unregister();
        objective = scoreboard.registerNewObjective("SkyblockReborn", player.getDisplayName());
        setDisplayName("§e§lSKYBLOCK §b§lREBORN");
//        objective.setDisplayName(ChatColor.GOLD + "Custom Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

}