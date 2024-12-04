package me.alwayslg.custommobs;

import me.alwayslg.SkyblockReborn;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.ThreadLocalRandom;

import static me.alwayslg.util.Utilities.getBoundingBox;

public class DamageIndicator {
    public static void spawn(LivingEntity target, int damage){
        ArmorStand display;
//        this.target = target;
//        AxisAlignedBB targetBoundingBox = getBoundingBox(target);
//        double targetHeight = targetBoundingBox.e-targetBoundingBox.b;
        int randomAngle = ThreadLocalRandom.current().nextInt(360);
        double radians = Math.toRadians(randomAngle);
        double xOffset = Math.cos(radians);
        double zOffset = Math.sin(radians);

        String text = String.format("§7%d",damage);
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
}
