package me.alwayslg.customitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class AspectOfTheEnd extends CustomItem implements Listener {

    public AspectOfTheEnd() {
        super(CustomItemID.ASPECT_OF_THE_END);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Check if the player right-clicked and if they are holding the sword
        if(item==null && !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(CustomItemID.ASPECT_OF_THE_END.getID())) {
            player.sendMessage(player.getLocation().toString());
            for (int i = 0; i < 8; i++) {
                Vector nextLocation = origLoc.toVector().add(origDir.multiply(1));
                origLoc = nextLocation.toLocation(origLoc.getWorld()).setDirection(origDir);
                Location fuckingCheck = origLoc.clone();
                if (!checkIfPassable(origLoc)
                        || !checkIfPassable(fuckingCheck.add(0, 1, 0))
                        || !checkIfPassable(fuckingCheck.add(0, -1, 0))
                        || !checkIfPassable(fuckingCheck.add(0, 1.62, 0))

                                //||!origLoc.add(0,1.5,0).getBlock().isEmpty()
                ){
                    //if(!checkIfPassable(origLoc.clone().add(0,-1,0))) origLoc.add(0,1,0);
                    player.sendMessage("BLOCK IN WAY NIGGER");
                    break;
                }

                player.teleport(origLoc);
            }
        }
    }
}
