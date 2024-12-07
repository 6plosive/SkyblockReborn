package me.alwayslg.customitems;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.HashMap;

import static me.alwayslg.customitems.CustomItemID.JER_JER_SHORTBOW;
import static me.alwayslg.customitems.CustomItemID.WITHER_SHORTBOW;
import static me.alwayslg.custommobs.DamageHandler.dealRealDamageNearbyEntities;
import static me.alwayslg.util.Utilities.playCustomSoundToNearbyPlayers;
import static me.alwayslg.util.Utilities.playerWarn;

public class WitherShortBow extends CustomItem implements Listener {
    public WitherShortBow(){
        super(CustomItemID.WITHER_SHORTBOW);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if((event.getAction().toString().contains("RIGHT") || event.getAction().toString().contains("LEFT")) && customItem.getID().equals(WITHER_SHORTBOW.getID())) {
            // Check for cooldown
            event.setCancelled(true);
            if(Cooldown.hasCooldown(customItem.getUUID())){
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }


            Arrow arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getLocation().getDirection(), 4.0f, 1.0f);
            arrow.setMetadata("damage",new FixedMetadataValue(SkyblockReborn.getInstance(),customItem.getDamage()));

            arrow.setShooter(player);
            arrow.setMetadata("explosionLevel", new FixedMetadataValue(SkyblockReborn.getInstance(), "1"));



            Cooldown.addCooldown(customItem.getUUID(),5);// 0.5 seconds
        }
    }
    @EventHandler
    public void arrowhit(ProjectileHitEvent event) {// no bro it detect the metatag
        if(!(event.getEntity().getShooter() instanceof Player)) return;

        Player p= (Player) event.getEntity().getShooter();
        int explosionLevel = 0;
        if (event.getEntity().hasMetadata("explosionLevel")) {
            // Get the metadata values
            for (MetadataValue value : event.getEntity().getMetadata("explosionLevel")) {
                explosionLevel = value.asInt(); // Get the integer value
            }
        }
        if(event.getEntity() !=null && explosionLevel==1){
            net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles packet = new net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles(
                    EnumParticle.EXPLOSION_LARGE, true, (float) event.getEntity().getLocation().getX(),
                    (float) event.getEntity().getLocation().getY(), (float) event.getEntity().getLocation().getZ(),
                    0, 0, 0, 1, 1
            );
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
            playCustomSoundToNearbyPlayers(event.getEntity().getLocation(),20, Sound.EXPLODE);
            dealRealDamageNearbyEntities(event.getEntity().getLocation(), 3, p);


            p.sendMessage("hello this is test -- " +explosionLevel);
        }

    }
}
