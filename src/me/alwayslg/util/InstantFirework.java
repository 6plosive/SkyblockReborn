package me.alwayslg.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class InstantFirework {


    /*
     * InstantFirework class made by TehHypnoz.
     *
     * Credits to:
     *
     * - fromgate, for explaining that setting the ticksFlown field to the expectedLifespan field will create instant fireworks.
     * - Skionz, for the getNMSClass() method.
     *
     * Example usage:
     * FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(false).trail(true).with(Type.BALL).withColor(Color.ORANGE).withFade(Color.RED).build();
     * Location location = p.getLocation();
     * new InstantFirework(fireworkEffect, location);
     */


    public InstantFirework(FireworkEffect fe, Location loc) {
        Firework f = (Firework) loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(fe);
        f.setFireworkMeta(fm);
        try {
            Class<?> entityFireworkClass = getClass("net.minecraft.server.", "EntityFireworks");
            Class<?> craftFireworkClass = getClass("org.bukkit.craftbukkit.", "entity.CraftFirework");
            Object firework = craftFireworkClass.cast(f);
            Method handle = firework.getClass().getMethod("getHandle");
            Object entityFirework = handle.invoke(firework);
            Field expectedLifespan = entityFireworkClass.getDeclaredField("expectedLifespan");
            Field ticksFlown = entityFireworkClass.getDeclaredField("ticksFlown");
            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFirework, expectedLifespan.getInt(entityFirework) - 1);
            ticksFlown.setAccessible(false);
        }  catch (ClassNotFoundException e) {
            Bukkit.getLogger().severe("Class not found: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            Bukkit.getLogger().severe("Method not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            Bukkit.getLogger().severe("Illegal access: " + e.getMessage());
        } catch (InvocationTargetException e) {
            Bukkit.getLogger().severe("Invocation target exception: " + e.getMessage());
        } catch (NoSuchFieldException e) {
            Bukkit.getLogger().severe("Field not found: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Class<?> getClass(String prefix, String nmsClassString) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = prefix + version + nmsClassString;
        Class<?> nmsClass = Class.forName(name);
        return nmsClass;
    }

    public static Color randomColor() {
        return Color.fromRGB((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

}
