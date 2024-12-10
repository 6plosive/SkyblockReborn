package me.alwayslg.customitems.weapons;

import me.alwayslg.customitems.Cooldown;
import me.alwayslg.customitems.CustomItem;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

import static me.alwayslg.customitems.CustomItemID.HYPERION;
import static me.alwayslg.custommobs.DamageHandler.dealMagicDamageNearbyEntities;
import static me.alwayslg.util.Utilities.playerWarn;

public class Hyperion extends CustomItem implements Listener {
    public Hyperion(){
        super(HYPERION);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) { //This is fucking perfect
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        // Check if the player right-clicked and if they are holding the sword
        if(item==null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if(meta==null) return;
        if(!isCustomItem(item)) return;
        CustomItem customItem = new CustomItem(item);
        if(event.getAction().toString().contains("RIGHT") && customItem.getID().equals(HYPERION.getID())) {
            // Check for cooldown
            if(Cooldown.hasCooldown(customItem.getUUID())){
                playerWarn(player,"This item is currently on cooldown!");
                return;
            }

            ArrayList<Material> passableBlocks = new ArrayList<>(Arrays.asList(
                    Material.LONG_GRASS,Material.RAILS,Material.ACTIVATOR_RAIL,Material.POWERED_RAIL,Material.DETECTOR_RAIL,Material.AIR,Material.LADDER,
                    Material.LAVA,Material.STATIONARY_LAVA,Material.WATER,Material.STATIONARY_WATER,Material.RED_ROSE,Material.YELLOW_FLOWER,Material.DEAD_BUSH,
                    Material.BANNER,Material.SIGN,Material.SIGN_POST,Material.TORCH,Material.SAPLING,Material.DOUBLE_PLANT,Material.STANDING_BANNER,Material.WALL_BANNER,
                    Material.BROWN_MUSHROOM,Material.RED_MUSHROOM,Material.REDSTONE_TORCH_ON,Material.REDSTONE_TORCH_OFF,Material.REDSTONE_WIRE,Material.WALL_SIGN,
                    Material.TRIPWIRE,Material.TRIPWIRE_HOOK,Material.WOOD_BUTTON,Material.STONE_BUTTON,Material.LEVER,Material.GOLD_PLATE,Material.IRON_PLATE,
                    Material.WOOD_PLATE,Material.STONE_PLATE
            ));
            Location tpLocation = player.getEyeLocation().clone();
            Location previousLocation = tpLocation.clone();
            Vector direction = tpLocation.getDirection().normalize().multiply(0.5);
            boolean hittedsomething=false;
            // If invalid location from the start, cancel tp entirely
            Block feetBlock = tpLocation.getBlock();
            Block headBlock = tpLocation.clone().add(0,1,0).getBlock();
            if(!passableBlocks.contains(feetBlock.getType())||!passableBlocks.contains(headBlock.getType())){
                playerWarn(player,"There are blocks in the way!");
                return;
            }
            // TP 10 Blocks ahead
            for(int i=0;i<20;i++){
//                player.sendMessage(String.format("%d:{%f,%f,%f}",i,tpLocation.getX(),tpLocation.getY(),tpLocation.getZ()));
                feetBlock = tpLocation.getBlock();
                headBlock = tpLocation.clone().add(0,1,0).getBlock();
//                player.sendMessage(String.format("%d:%s|%s",i,feetBlock.getType(),headBlock.getType()));
//                player.sendMessage(String.format("PASS?%b %b %b",passableBlocks.contains(feetBlock.getType()),passableBlocks.contains(headBlock.getType()),passableBlocks.contains(feetBlock.getType())&&passableBlocks.contains(headBlock.getType())));
//                if(feetBlock.isEmpty()&&headBlock.isEmpty()){
                if(passableBlocks.contains(feetBlock.getType())&&passableBlocks.contains(headBlock.getType())){
                    //Not hitting something
                    previousLocation = tpLocation.clone();
                    tpLocation.add(direction);
                }else{
                    //Hit something!
                    playerWarn(player,"There are blocks in the way!");
                    player.teleport(previousLocation.getBlock().getLocation().add(0.5,0,0.5).setDirection(direction));
                    hittedsomething=true;
                    break;
                }
            }
            if(!hittedsomething){
                player.teleport(previousLocation.getBlock().getLocation().add(0.5,0,0.5).setDirection(direction));
            }
            // Set cooldown to 1 because this event triggers twice when crosshair on block somehow...
            Cooldown.addCooldown(customItem.getUUID(),1);
            // Spawn huge explosion particles
            net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles packet = new net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles(
                    EnumParticle.EXPLOSION_LARGE, true, (float) previousLocation.getX(),
                    (float) previousLocation.getY(), (float) previousLocation.getZ(),
                    0, 0, 0, 7, 3
            );
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            // Damage in radius
            dealMagicDamageNearbyEntities(player.getLocation(),6,player);
        }
    }
}

