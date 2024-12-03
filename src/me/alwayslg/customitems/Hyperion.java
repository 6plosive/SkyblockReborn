package me.alwayslg.customitems;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import static me.alwayslg.customitems.CustomItemID.HYPERION;
import static me.alwayslg.custommobs.DamageHandler.damageNearbyEntities;
import static me.alwayslg.util.Utilities.playerWarn;

public class Hyperion extends CustomItem implements Listener {
    public Hyperion(){
        super(HYPERION);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Location origLoc = player.getLocation().add(0, 1, 0); //added 1 on y
        Vector origDir = origLoc.getDirection();
        // Check if the player right-clicked and if they are holding the sword
        if(item==null && !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(HYPERION.getID())) {
//            Bukkit.broadcastMessage(player.getLocation().toString());
            for (int i = 0; i < 10; i++) {
                Vector nextLocation = origLoc.toVector().add(origDir.multiply(1));
                origLoc = nextLocation.toLocation(origLoc.getWorld()).setDirection(origDir);
                if (
                        !origLoc.getBlock().isEmpty() ||
                                !origLoc.add(0, 1, 0).getBlock().isEmpty() ||
                                !origLoc.add(0, -1, 0).getBlock().isEmpty()
                ){
                    playerWarn(player,"There are blocks in the way!");
                    break;
                }
                player.teleport(origLoc);
            }
            // spawn huge explosion particles
            net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles packet = new net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles(
                    EnumParticle.EXPLOSION_LARGE, true, (float) origLoc.getX(),
                    (float) origLoc.getY(), (float) origLoc.getZ(),
                    0, 0, 0, 7, 3
            );
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            // damage in radius
            damageNearbyEntities(player.getLocation(),6,player);
        }
    }
}
