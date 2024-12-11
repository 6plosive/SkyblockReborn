package me.alwayslg.commands;

import me.alwayslg.customitems.CustomItem;
import me.alwayslg.customitems.weapons.DiamondSword;
import me.alwayslg.custommobs.customentities.CustomEntityWither;
import me.alwayslg.custommobs.customentities.CustomEntityZombie;
import me.alwayslg.custommobs.customentities.EntityTypes;
import me.alwayslg.ui.GUI;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand implements CommandExecutor {
    private static Entity customEntityWither;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location loc = p.getLocation();
            World world = loc.getWorld();
            int mode = 1;
            if (args.length == 1) {
                mode = Integer.parseInt(args[0]);
            }
//            CustomEntityWither superZombie = new CustomEntityWither(((CraftWorld)world).getHandle());
//            CustomEntityWither superZombie = new CustomEntityWither(world);
//            superZombie.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
//            ((CraftWorld)loc.getWorld()).getHandle().addEntity(superZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
//            EntityTypes.spawnEntity(new CustomEntityWither(Bukkit.getWorld("world")), new Location(Bukkit.getWorld("world"), loc.getX(), loc.getY(), loc.getZ()));
            if(mode==1) {
                customEntityWither = new CustomEntityWither(world);
//                org.bukkit.entity.Entity bukkitWither = customEntityWither.getBukkitEntity();
                EntityTypes.spawnEntity(customEntityWither, loc);
//                world.spawn(loc,bukkitWither.getClass());
            }else if(mode==2){
                EntityTypes.spawnEntity(new CustomEntityZombie(Bukkit.getWorld("world")), loc);
            }else if(mode==3){
//                sender.sendMessage(customEntityWither.getGoalTarget().getBukkitEntity().getName());
            }else if(mode==4){
                GUI gui = new GUI("Sample");
                for(int i=0;i<50;i++)

                    gui.addItem(new DiamondSword());
                gui.open(p);
            }else if(mode==5){
                ItemStack item = p.getItemInHand();
                CustomItem customItem = new CustomItem(item);
                customItem.setHotPotatoCount(customItem.getHotPotatoCount()+1);
                p.setItemInHand(customItem);
            }
        }
        return true;
    }

}
