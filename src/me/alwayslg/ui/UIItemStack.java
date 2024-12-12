package me.alwayslg.ui;

import me.alwayslg.util.CustomHeads;
import me.alwayslg.util.Utilities;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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
    ANVIL_BUTTON_OFF(Material.ANVIL, (short) 0, "§aCombine Items", (byte) 0,Arrays.asList("§7Combine the items in the slots to the", "§7left and right below.")),
    ANVIL_BUTTON_ON(Material.ANVIL, (short) 0, "§aCombine Items", (byte) 0,Arrays.asList("§7Combine the items in the slots to the", "§7left and right below.","", "§eClick to combine!"), true),
    ANVIL_BUTTON_CLAIM(Material.SIGN, (short) 0, "§aAnvil", (byte) 0,Arrays.asList("§7Claim the result item above!")),

    SKYBLOCK_MENU_SKILLS(Material.DIAMOND_SWORD, (short) 0, "§aYour Skills", (byte) 0,Arrays.asList("§7View your Skill progression and", "§7rewards.", "", "§631.8 Skill Avg. §8(non-cosmetic)", "", "§eClick to view!")),
    SKYBLOCK_MENU_COLLECTIONS(Material.PAINTING, (short) 0, "§aCollections", (byte) 0,Arrays.asList("§7View all of the items available in", "§7SkyBlock. Collect more of an item to", "§7unlock rewards on your way to", "§7becoming a master of SkyBlock!", "", "§7Collections Unlocked: §e88.3§6%", "§2§l§m                       §f§l§m  §r §e68§6/§e77", "", "§eClick to view!")),
    SKYBLOCK_MENU_RECIPE(Material.BOOK, (short) 0, "§aRecipe Book", (byte) 0,Arrays.asList("§7Through your adventure, you will", "§7unlock recipes for all kinds of", "§7special items! You can view how to", "§7craft these items here.", "", "§7Recipe Book Unlocked: §e78§6%", "§2§l§m                    §f§l§m     §r §e655§6/§e840", "", "§eClick to view!")),
    SKYBLOCK_MENU_LEVEL_PROGRESS(CustomHeads.SKYBLOCK_LEVEL_PROGRESS.getItem(), "§aSkyBlock Leveling", 0, Arrays.asList("§7Your SkyBlock Level: §8[§a136§8]", "", "§7§7Determine how far you've", "§7progressed in SkyBlock and earn", "§7rewards from completing unique", "§7tasks.", "", "§7Progress to Level 137:", "§3§l§m     §f§l§m                    §r §b20§3/§b100 §bXP", "", "§eClick to view!")),
    SKYBLOCK_MENU_QUEST(Material.BOOK_AND_QUILL, (short) 0, "§aQuest Log", (byte) 0,Arrays.asList("§7View your active quests, progress,", "§7and rewards.", "", "§eClick to view!")),
    SKYBLOCK_MENU_CALENDER(Material.WATCH , (short) 0, "§aCalendar and Events", (byte) 0,Arrays.asList("§7View the SkyBlock Calendar, upcoming", "§7events, and event rewards!", "", "§7Date: §a22nd Late Spring 390", "", "§7Active Event: §d45th Hoppity's Hunt", "§7Ends in: §e3h 8m 15s", "", "§7Next Event: §b302nd Election Over!", "§7Starting in: §e1h 28m 16s", "", "§eClick to view!")),
    SKYBLOCK_MENU_STORAGE(Material.CHEST, (short) 0, "§aStorage", (byte) 0,Arrays.asList("§7Store global items that you want to", "§7access at any time from anywhere", "§7here.", " ", "§eClick to view!")),
    SKYBLOCK_MENU_BAGS(CustomHeads.ACCESSORY_BAG.getItem(), "§aYour Bags", 254, Arrays.asList("§7§7Different bags allow you to store", "§7many different items inside!", "", "§eClick to open!")),
    SKYBLOCK_MENU_PETS(Material.BONE, (short) 0, "§aPets", (byte) 0,Arrays.asList("§7§7View and manage all of your Pets.", "", "§7§7Level up your pets faster by gaining", "§7XP in their favorite skill!", "", "§7§7Selected pet: §6Wither Skeleton", "", "§b§lMAX LEVEL", "§8▸ 35,729,148 XP", "", "§eClick to view!")),
    SKYBLOCK_MENU_CRAFTING_TABLE(Material.WORKBENCH, (short) 0, "§aCrafting Table", (byte) 0,Arrays.asList("§7Opens the crafting grid.", "", "§eClick to open!")),
    SKYBLOCK_MENU_WARDROBE(Material.LEATHER_CHESTPLATE, (short) 0, "§aWardrobe", (byte) 0,Arrays.asList("§7Store armor sets and quickly swap", "§7between them!", "", "§eClick to view!"),false,Color.fromRGB(8339378)),
    SKYBLOCK_MENU_PERSONAL_BANK(CustomHeads.PERSONAL_BANK.getItem(), "§aPersonal Bank", 0, Arrays.asList("§7Contact your Banker from anywhere.", "§7Cooldown: §e5 minutes", "", "§7Banker Status:", "§aAvailable", "", "§7Interest in: §b3 Hours", "§7Co-op Projection: §6390,000 coins §b(0.126%)", "§7Last Co-op Interest: §6390,000 coins", "§7Solo Projection: §678,000 coins §b(0.157%)", "§7Last Solo Interest: §678,000 coins", "", "§eClick to open!")),
    SKYBLOCK_MENU_FAST_TRAVEL(CustomHeads.FAST_TRAVEL.getItem(), "§bFast Travel", 0, Arrays.asList("§7Teleport to islands you've already", "§7visited.", "", "§eClick to pick location!")),
    SKYBLOCK_MENU_SWITCH_PROFILES(Material.NAME_TAG, (short) 0, "§aProfile Management", (byte) 0,Arrays.asList("§7You can have multiple SkyBlock", "§7profiles at the same time.", "", "§7Each profile has its own island,", "§7inventory, quest log...", "", "§7Profiles: §e3§6/§e3", "§7Playing on: §aRaspberry", "", "§bPlay with friends using /coopadd <name>!", "", "§eClick to manage!")),
    SKYBLOCK_MENU_SETTINGS(Material.REDSTONE_TORCH_ON, (short) 0, "§aSettings", (byte) 0,Arrays.asList("§7View and edit your SkyBlock settings.", "", "§eClick to view!")),
    SKYBLOCK_MENU_BOOSTER_COOKIE(Material.COOKIE, (short) 0, "§6Booster Cookie", (byte) 0,Arrays.asList("§7§7Obtain the §dCookie Buff §7from Booster", "§7Cookies in the hub's community shop.", "", "§7Status: §cNot active!", "§7Bits Available: §b0", "", "§eClick to get all the info!"));

    private ItemStack item;
    private UIItemStack(ItemStack item, String name, int hideFlags, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if(hideFlags!=0){
            item = Utilities.setNBTTags(item, "HideTooltip", new NBTTagInt(hideFlags));
        }
        this.item = item;
    }
    private UIItemStack(Material material, short damage, String name, byte hideTooltip, List<String> lore, boolean enchanted, Color leatherColor){
        item = new ItemStack(material, 1, damage);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.setColor(leatherColor);
        item.setItemMeta(meta);
        if(hideTooltip==1){
            item = Utilities.setNBTTags(item, "HideTooltip", new NBTTagByte(hideTooltip));
        }
        if(enchanted){
            item = Utilities.setNBTTags(item, "ench", new NBTTagList());
        }
    }
    private UIItemStack(Material material, short damage, String name, byte hideTooltip, List<String> lore, boolean enchanted){
        item = new ItemStack(material, 1, damage);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if(hideTooltip==1){
            item = Utilities.setNBTTags(item, "HideTooltip", new NBTTagByte(hideTooltip));
        }
        if(enchanted){
            item = Utilities.setNBTTags(item, "ench", new NBTTagList());
        }
    }
    private UIItemStack(Material material, short damage, String name, byte hideTooltip, List<String> lore){
        this(material, damage, name, hideTooltip, lore, false);
    }
    private UIItemStack(Material material, short damage, String name, byte hideTooltip){
        this(material, damage, name, hideTooltip, null);
    }
    public ItemStack getItem(){
        return item;
    }
}
