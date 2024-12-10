package me.alwayslg.ui;

import me.alwayslg.util.Utilities;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public enum UIItemStack {
    DARK_GRAY_BACKGROUND(Material.STAINED_GLASS_PANE, (short) 15, " ", (byte) 1),
    RED_BACKGROUND(Material.STAINED_GLASS_PANE, (short) 14, " ", (byte) 1),
    GREEN_BACKGROUND(Material.STAINED_GLASS_PANE, (short) 5, " ", (byte) 1),
    CLOSE_BARRIER(Material.BARRIER, (short) 0, "§cClose", (byte) 0),
    ANVIL_LEFT_RED(Material.STAINED_GLASS_PANE, (short) 14, "§6Item to Upgrade", (byte) 0,Arrays.asList("§7The item you want to upgrade should", "§7be placed in the slot on this side.")),
    ANVIL_RIGHT_RED(Material.STAINED_GLASS_PANE, (short) 14,"§6Item to Sacrifice", (byte) 0,Arrays.asList("§7The item you are sacrificing in order", "§7to upgrade the item on the left", "§7should be placed in the slot on this", "§7side.")),
    ANVIL_LEFT_GREEN(Material.STAINED_GLASS_PANE, (short) 5, "§6Item to Upgrade", (byte) 0,Arrays.asList("§7The item you want to upgrade should", "§7be placed in the slot on this side.")),
    ANVIL_RIGHT_GREEN(Material.STAINED_GLASS_PANE, (short) 5,"§6Item to Sacrifice", (byte) 0,Arrays.asList("§7The item you are sacrificing in order", "§7to upgrade the item on the left", "§7should be placed in the slot on this", "§7side.")),
    ANVIL_RESULT_BARRIER(Material.BARRIER, (short) 0, "§cAnvil", (byte) 0, Arrays.asList("§7Place a target item in the left slot", "§7and a sacrifice item in the right slot", "§7to combine them!")),
    ANVIL_BUTTON(Material.ANVIL, (short) 0, "§aCombine Items", (byte) 0,Arrays.asList("§7Combine the items in the slots to the", "§7left and right below."));

    private ItemStack item;
    private UIItemStack(Material material, short damage, String name, byte hideTooltip, List<String> lore){
        item = new ItemStack(material, 1, damage);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if(hideTooltip==1){
            item = Utilities.setNBTTags(item, "HideTooltip", new NBTTagByte(hideTooltip));
        }
    }
    private UIItemStack(Material material, short damage, String name, byte hideTooltip){
        this(material, damage, name, hideTooltip, null);
    }
    public ItemStack getItem(){
        return item;
    }
}
