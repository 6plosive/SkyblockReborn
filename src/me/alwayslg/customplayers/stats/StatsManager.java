package me.alwayslg.customplayers.stats;

import me.alwayslg.customplayers.CustomPlayer;

public class StatsManager {
    CustomPlayer customPlayer;
    public StatsManager(CustomPlayer customPlayer){
        this.customPlayer = customPlayer;
    }
    public int getCritChance(){
        return 30;
    }
    public int getCritDamage(){
        return 50;
    }

}
