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

public class CustomScoreboard {

    private final ScoreboardManager manager;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private Collection<String> entries;

    public CustomScoreboard(Player player) {
        manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("SkyblockReborn", player.getDisplayName());
//        objective.setDisplayName(ChatColor.GOLD + "Custom Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(scoreboard);
    }

    public Collection<String> getEntries() {
        return entries;
    }
    public void setEntries(Collection<String> entries) {
        this.entries = entries;
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
        tempEntries.add("§fPurse: §68,412,415,131");
        //Bits
        tempEntries.add("§fBits: §b941,520");
        //Gap
        tempEntries.add("   ");
        //alwayslg.com
        tempEntries.add("§emc.alwayslg.com");

        setEntries(tempEntries);

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

}