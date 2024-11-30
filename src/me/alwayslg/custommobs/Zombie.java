package me.alwayslg.custommobs;

import org.bukkit.entity.EntityType;

public class Zombie extends CustomMob {
    public Zombie(){
        setEntityClass(org.bukkit.entity.Zombie.class);
        setLevel(1);
        setFullHealth(100);
        setName("Graveyard Zombie");
    }
}
