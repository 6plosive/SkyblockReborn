package me.alwayslg.custommobs;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.ThreadLocalRandom;

import static me.alwayslg.util.Utilities.getBoundingBox;
import static me.alwayslg.util.Utilities.numberFormatComma;

public class DamageIndicator {
    public static void spawn(LivingEntity target, int damage, boolean isCrit){
        ArmorStand display;
//        this.target = target;
//        AxisAlignedBB targetBoundingBox = getBoundingBox(target);
//        double targetHeight = targetBoundingBox.e-targetBoundingBox.b;
        int randomAngle = ThreadLocalRandom.current().nextInt(360);
        double radians = Math.toRadians(randomAngle);
        double xOffset = Math.cos(radians);
        double zOffset = Math.sin(radians);

        String text = String.format("§7%s",numberFormatComma(damage));
        if(isCrit){
            text = String.format("✧%s✧",numberFormatComma(damage));
            // add rainbow effect
            String rainbowTable = "ffe6cc";
            String textAfterRainbow = "";
            for(int i=0;i<text.length();i++){
                textAfterRainbow = textAfterRainbow + '§' + rainbowTable.charAt(i%6) + text.charAt(i);
            }
            text=textAfterRainbow;
        }
        display = target.getWorld().spawn(target.getLocation().add(xOffset,1,zOffset), ArmorStand.class);
        display.setMarker(true);
        display.setCustomName(text);
        display.setCustomNameVisible(true);
        display.setSmall(true);
        display.setGravity(false);
        display.setVisible(false);
        display.setBasePlate(false);

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(SkyblockReborn.getPlugin(SkyblockReborn.class), () -> {
            display.remove();
        }, 20); // 20 ticks = 1 second
    }
    public static void spawn(LivingEntity target, int damage){
        spawn(target, damage, false);
    }
}
