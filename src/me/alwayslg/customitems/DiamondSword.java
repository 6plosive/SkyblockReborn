package me.alwayslg.customitems;

import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class DiamondSword extends CustomItem implements Listener {
    public DiamondSword() {
        super(CustomItemID.DIAMOND_SWORD);
    }

    private boolean checkIfPassable(Location location) {
        Location fuckingCheck = location.clone();
        if (fuckingCheck.getBlock().isEmpty() ||
                fuckingCheck.getBlock().isLiquid() ||
                fuckingCheck.getBlock().getType() == Material.GRASS ||
                fuckingCheck.getBlock().getType() == Material.LONG_GRASS


        ) {
            return true;
        } else return false;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Location origLoc = player.getLocation().add(0, 1, 0).clone(); //added 1 on y
        Vector origDir = origLoc.getDirection();
        // Check if the player right-clicked and if they are holding the sword
        if (item == null && !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        if (!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if (event.getAction().toString().contains("RIGHT") && customItem.getID().equals(CustomItemID.DIAMOND_SWORD.getID())) {
            Bukkit.broadcastMessage(player.getLocation().toString());
            for (int i = 0; i < 8; i++) {
                Vector nextLocation = origLoc.toVector().add(origDir.multiply(1));
                origLoc = nextLocation.toLocation(origLoc.getWorld()).setDirection(origDir);
                Location fuckingCheck = origLoc.clone();
                if (!checkIfPassable(fuckingCheck)
                    //|| !checkIfPassable(fuckingCheck.add(0, 1, 0))
                    //   || !checkIfPassable(fuckingCheck.add(0, -1, 0))
                    //  || !checkIfPassable(fuckingCheck.add(0, 1.62, 0))

                    //||!origLoc.add(0,1.5,0).getBlock().isEmpty()
                ) {
                    //if(!checkIfPassable(origLoc.clone().add(0,-1,0))) origLoc.add(0,1,0);
                    player.sendMessage("BLOCK IN WAY NIGGER");
                    break;
                }

                if (!checkIfPassable(origLoc)) {
                    player.teleport(origLoc.add(0,1,0));
                }else {
                    player.teleport(origLoc);
                }
            }


            /*if (!checkIfPassable(player.getLocation())) {
                player.teleport(player.getLocation().add(0, 1, 0));
                player.sendMessage("DEBUG 2 FAWIJEIFWAJIKOFawikAEGIMBOIJOGOIMJ");

            }*/


        }
    }
}
