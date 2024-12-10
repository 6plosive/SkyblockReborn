package me.alwayslg.commands;

import me.alwayslg.ui.AdminItemUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetItems implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){//esgesgesg
            Player p = (Player) sender;
            p.openInventory(AdminItemUI.getInventory());
//            System.out.println("Player open getitems??!??");
        }
        return true;
    }
}
