package me.alwayslg.custommobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class BabyNecron extends CustomMob implements Listener {
    public BabyNecron(){
//        setEntityClass(org.bukkit.entity.Wither.class);
//        setLevel(69);
//
//        setFullHealth(2000);
//        setName("Baby Necron");
        super(CustomMobID.BABY_NECRON);
    }

}
