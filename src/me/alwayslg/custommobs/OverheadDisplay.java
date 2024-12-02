package me.alwayslg.custommobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class OverheadDisplay {
    private ArmorStand e;
    private String text;
    private Entity target;
    private boolean shouldTextUpdate;
    public OverheadDisplay(){
        shouldTextUpdate=false;
    }
    public Entity spawn(Entity target){
        this.target = target;
        e = target.getWorld().spawn(target.getLocation().add(0,2,0), ArmorStand.class);
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
//        Bukkit.broadcastMessage("Target Y: "+targetlocation.getY());
        targetlocation.add(0,2,0);
//        Bukkit.broadcastMessage("TP location Y: "+targetlocation.getY());
        e.teleport(targetlocation);
//        Bukkit.broadcastMessage("display location Y: "+e.getLocation().getY());
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
