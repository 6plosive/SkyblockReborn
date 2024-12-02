package me.alwayslg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

public class RideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player rider = (Player) sender;

            if (args.length != 1) {
                rider.sendMessage("Usage: /ride <player>");
                return false;
            }

            Player target = rider.getServer().getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                rider.sendMessage("Player not found!");
                return true;
            }

            // Make the target the rider
            target.setPassenger(rider);
            rider.sendMessage("You are now riding " + target.getName() + "!");
            return true;
        }

        return false;
    }
}