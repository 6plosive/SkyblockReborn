package me.alwayslg.commands;

import me.alwayslg.ui.AnvilUI;
import me.alwayslg.util.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Anvil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            AnvilUI.open(p);
        }
        return true;
    }
}
