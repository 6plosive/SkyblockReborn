package me.alwayslg.customitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Boomerang extends CustomItem{
    public Boomerang(){
        setMaterial(Material.BONE);
        setRarity(Rarity.LEGENDARY);
        setItemType(ItemType.SHORTBOW);

        setName("Niggerang");
        setDamage(69);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getAction().toString().contains("RIGHT") && item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().contains("Niggerang")) {



            }
            }
    }
}
