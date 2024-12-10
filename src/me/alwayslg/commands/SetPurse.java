package me.alwayslg.commands;

import me.alwayslg.customplayers.CustomPlayer;
import me.alwayslg.customplayers.CustomPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.alwayslg.util.Utilities.playerSuccess;
import static me.alwayslg.util.Utilities.playerWarn;

public class SetPurse implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            long purse;
            if (args.length != 2) {
                playerWarn(p, "Usage: /setpurse <player> <amount>");
            }else{
                p = Bukkit.getPlayer(args[0]);
                purse = Long.parseLong(args[1]);
                CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayer(p.getUniqueId());
                customPlayer.setPurse(purse);
                playerSuccess(p,"Successfully set "+p.getName()+"'s purse to "+purse);
            }
        }
        return true;
    }
}
