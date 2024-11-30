package me.alwayslg.commands;

import me.alwayslg.customitems.GUI;
import me.alwayslg.custommobs.Zombie;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnZombie implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Zombie zombie = new Zombie();
            zombie.spawn(p.getLocation());
//            System.out.println("Player open getitems??!??");
        }
        return true;
    }
}
