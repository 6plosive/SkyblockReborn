package me.alwayslg.commands;

import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
import me.alwayslg.customplayers.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.alwayslg.util.Utilities.playerSuccess;
import static me.alwayslg.util.Utilities.playerWarn;

public class SetRank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            Rank rank;
            if (args.length != 2) {
                ArrayList<String> rankValues = new ArrayList<>();
                for (Rank rankValue : Rank.values()) {
                    rankValues.add(rankValue.toString());
                }
                playerWarn(p, "Usage: /setrank <player> " + rankValues);
            } else {
                p = Bukkit.getPlayer(args[0]);
                rank = Rank.valueOf(args[1].toUpperCase());
                CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(p.getUniqueId());
                customPlayer.setRank(rank);
                playerSuccess(p, "Successfully set "+p.getName()+"'s rank to "+rank.getChatPrefix());
            }
        }
        return true;
    }
}
