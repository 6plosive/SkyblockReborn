package me.alwayslg.commands;

import me.alwayslg.custommobs.BabyNecron;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.alwayslg.util.Utilities.playerWarn;

public class SpawnNecron implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            int amount = 1;
            if (args.length == 1) {
                amount = Integer.parseInt(args[0]);
            }
            if(amount>100){
                playerWarn(p,"Are you trying to crash my server? You stupid ni");
                amount=1;
            }
            for(int i=0;i<amount;i++) {
                BabyNecron babyNecron = new BabyNecron();
                babyNecron.spawn(p.getLocation(),true);
            }
        }
        return true;
    }
}
