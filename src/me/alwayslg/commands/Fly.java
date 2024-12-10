package me.alwayslg.commands;

import me.alwayslg.customitems.GUI;
import me.alwayslg.util.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            boolean shouldFly = !p.getAllowFlight();
            if(shouldFly) Utilities.playerSuccess(p,"Set Allow Flight to true!");
            else Utilities.playerWarn(p, "Set Allow Flight to false!");
            p.setAllowFlight(shouldFly);
        }
        return true;
    }
}
