package me.alwayslg.custommobs;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import static me.alwayslg.util.Utilities.getBoundingBox;

public class HealthBar {
    private ArmorStand e;
    private String text;
    private LivingEntity target;
    private boolean shouldTextUpdate;
    public HealthBar(){
        shouldTextUpdate=false;
    }
    public Entity spawn(LivingEntity target){
        this.target = target;
        AxisAlignedBB targetBoundingBox = getBoundingBox(target);
        double targetHeight = targetBoundingBox.e-targetBoundingBox.b;
        e = target.getWorld().spawn(target.getLocation().add(0,targetHeight,0), ArmorStand.class);
        e.setMarker(true);
        e.setCustomName(text);
        e.setCustomNameVisible(true);
        e.setSmall(true);
        e.setGravity(false);
        e.setVisible(false);
        e.setBasePlate(false);

        return e;
    }
    public void kill(){
        e.remove();
    }
    public void updatePerTick(){
        Location targetlocation = target.getLocation();
        AxisAlignedBB targetBoundingBox = getBoundingBox(target);
        double targetHeight = targetBoundingBox.e-targetBoundingBox.b;
        targetlocation.add(0,targetHeight,0);
        e.teleport(targetlocation);
        if(shouldTextUpdate){
            e.setCustomName(text);
            shouldTextUpdate=false;
        }
    }
    public void setText(String s){
        text=s;
        shouldTextUpdate=true;
    }
    public String getText(){
        return text;
    }

    public Entity getTarget() {
        return target;
    }
}
