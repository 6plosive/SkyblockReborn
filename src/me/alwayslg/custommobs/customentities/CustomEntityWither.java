package me.alwayslg.custommobs.customentities;

import net.minecraft.server.v1_8_R3.*;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomEntityWither extends EntityWither {

    public CustomEntityWither(org.bukkit.World world){
//        super(world);
        super(((CraftWorld)world).getHandle());
//        List goalB = (List)getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
//        List goalC = (List)getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
//        List targetB = (List)getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
//        List targetC = (List)getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
//        this.goalSelector.a(0, new PathfinderGoalFloat(this));
//        this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, (double)1.0F, 40, 20.0F));
//        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, (double)1.0F));
//        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
//        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
//        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
//        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityInsentient.class, 0, false, false, bq));
//        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityInsentient.class, 0, false, false, witherTargetPredicate));

    }

    public static final Predicate<Entity> witherTargetPredicate = new Predicate<Entity>() {
        public boolean a(Entity entity) {
            return entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() != EnumMonsterType.UNDEAD && ((EntityLiving)entity).getBukkitEntity().getType() != EntityType.ARMOR_STAND;
        }
        public boolean apply(Entity object) {
            return this.a((Entity)object);
        }
    };
    private static Object getPrivateField(String fieldName, Class clazz, Object object){
        Field field;
        Object o = null;
        try{
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
    private static void setPrivateField(String fieldName, Class clazz, Object object, Object value){
        Field field;
        try{
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object,value);
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
//    private static final Predicate<Entity> witherTargetPredicate = new Predicate<Entity>() {
//        @Override
//        public boolean apply(Entity entity) {
//            return entity instanceof EntityLiving
//                    && ((EntityLiving)entity).getMonsterType()!= EnumMonsterType.UNDEAD
//                    && ((EntityLiving)entity).getBukkitEntity().getType()!= EntityType.ARMOR_STAND;
////                    && ((EntityLiving)entity).getMonsterType()
//        }
//    };

    // Time wasted: 12 Hours
    // I fucking hate how this is implemented. I WILL NOT FUCKING FIX IT! EVER AGAIN!
    // - Alwayslg, 7/12/2024 3:02 AM
    @Override
    protected void E() {
        try {
            int[] bn = (int[])getPrivateField("bn",EntityWither.class,this);
            int[] bo = (int[])getPrivateField("bo",EntityWither.class,this);
            int bp = (int)getPrivateField("bp",EntityWither.class,this);
            Predicate<Entity> bq = (Predicate<Entity>)getPrivateField("bq",EntityWither.class,this);
            // Use bn
            // ...

            if (this.cl() > 0) {
                int i = this.cl() - 1;
                if (i <= 0) {
                    ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), 7.0F, false);
                    this.world.getServer().getPluginManager().callEvent(event);
                    if (!event.isCancelled()) {
                        this.world.createExplosion(this, this.locX, this.locY + (double) this.getHeadHeight(), this.locZ, event.getRadius(), event.getFire(), this.world.getGameRules().getBoolean("mobGriefing"));
                    }

                    int viewDistance = ((WorldServer) this.world).getServer().getViewDistance() * 16;

                    for (EntityPlayer player : MinecraftServer.getServer().getPlayerList().players) {
                        double deltaX = this.locX - player.locX;
                        double deltaZ = this.locZ - player.locZ;
                        double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
                        if (this.world.spigotConfig.witherSpawnSoundRadius <= 0 || !(distanceSquared > (double) (this.world.spigotConfig.witherSpawnSoundRadius * this.world.spigotConfig.witherSpawnSoundRadius))) {
                            if (distanceSquared > (double) (viewDistance * viewDistance)) {
                                double deltaLength = Math.sqrt(distanceSquared);
                                double relativeX = player.locX + deltaX / deltaLength * (double) viewDistance;
                                double relativeZ = player.locZ + deltaZ / deltaLength * (double) viewDistance;
                                player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1013, new BlockPosition((int) relativeX, (int) this.locY, (int) relativeZ), 0, true));
                            } else {
                                player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1013, new BlockPosition((int) this.locX, (int) this.locY, (int) this.locZ), 0, true));
                            }
                        }
                    }
                }

                this.r(i);
                if (this.ticksLived % 10 == 0) {
                    this.heal(10.0F, EntityRegainHealthEvent.RegainReason.WITHER_SPAWN);
                }
            } else {
                super.E();

                for (int i = 1; i < 3; ++i) {
//                    if (this.ticksLived >= this.bn[i - 1]) {
                    // NEW
//                    if(i==1)Bukkit.broadcastMessage("tickslived"+this.ticksLived+" | bn[i-1]:"+bn[i - 1]);
//                    if (this.ticksLived >= 0) {
//                    if (this.ticksLived >= bn[i - 1]) {
                    if (this.ticksLived+1 >= bn[i - 1]) {
                    // NEW END
//                        Bukkit.broadcastMessage("huh,,");
//                        this.bn[i - 1] = this.ticksLived + 10 + this.random.nextInt(10);
                        // NEW
                        bn[i - 1] = this.ticksLived + 10 + this.random.nextInt(10);
                        setPrivateField("bn",EntityWither.class,this,bn);
                        // NEW END
                        if (this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) {
                            int k = i - 1;
//                            int l = this.bo[i - 1];
//                            this.bo[k] = this.bo[i - 1] + 1;
                            // NEW
                            int l = bo[i - 1];
                            bo[k] = bo[i - 1] + 1;
                            setPrivateField("bo",EntityWither.class,this,bo);
                            // NEW END

                            if (l > 15) {
                                float f = 10.0F;
                                float f1 = 5.0F;
                                double d0 = MathHelper.a(this.random, this.locX - (double) f, this.locX + (double) f);
                                double d1 = MathHelper.a(this.random, this.locY - (double) f1, this.locY + (double) f1);
                                double d2 = MathHelper.a(this.random, this.locZ - (double) f, this.locZ + (double) f);
//                                this.a(i + 1, d0, d1, d2, true);
                                // NEW
//                                EntityWither animal = new EntityWither(world);
                                Method soundMethod = EntityWither.class.getDeclaredMethod("a", int.class,double.class,double.class,double.class,boolean.class);
                                soundMethod.setAccessible(true); // bypass private access
                                soundMethod.invoke(this, i + 1, d0, d1, d2, true); // Output: Generic sound
                                // NEW END
//                                this.bo[i - 1] = 0;
                                //NEW
                                bo[i - 1] = 0;
                                setPrivateField("bo",EntityWither.class,this,bo);
                                //NEW END
                            }
                        }

                        int j = this.s(i);
//                        Bukkit.broadcastMessage("target j;"+j);
                        if (j > 0) {
                            Entity entity = this.world.a(j);
//                            if (entity != null && entity.isAlive() && this.h(entity) <= (double) 900.0F && this.hasLineOfSight(entity)) {
                            if (entity != null && entity.isAlive() && this.h(entity) <= (double) 900.0F && this.hasLineOfSight(entity) && entity.getBukkitEntity().getType() != EntityType.ARMOR_STAND) {
                                if (entity instanceof EntityHuman && ((EntityHuman) entity).abilities.isInvulnerable) {
                                    this.b(i, 0);
                                } else {
//                                    this.a(i + 1, (EntityLiving) entity);
                                    //NEW
//                                    EntityWither animal = new EntityWither(world);
                                    Method soundMethod = EntityWither.class.getDeclaredMethod("a", int.class,EntityLiving.class);
                                    soundMethod.setAccessible(true); // bypass private access
                                    soundMethod.invoke(this, i + 1, (EntityLiving) entity); // Output: Generic sound
                                    //NEW END
//                                    this.bn[i - 1] = this.ticksLived + 40 + this.random.nextInt(20);
//                                    this.bo[i - 1] = 0;
                                    //NEW
                                    bn[i - 1] = this.ticksLived + 40 + this.random.nextInt(20);
                                    bo[i - 1] = 0;
                                    setPrivateField("bn",EntityWither.class,this,bn);
                                    setPrivateField("bo",EntityWither.class,this,bo);
                                    //NEW END
                                }
                            } else {
                                this.b(i, 0);
                            }
                        } else {
//                            List list = this.world.a(EntityLiving.class, this.getBoundingBox().grow((double) 20.0F, (double) 8.0F, (double) 20.0F), Predicates.and(bq, IEntitySelector.d));
                            //NEW
                            List list = this.world.a(EntityLiving.class, this.getBoundingBox().grow((double) 20.0F, (double) 8.0F, (double) 20.0F), Predicates.and(witherTargetPredicate, IEntitySelector.d));
                            //NEW END
                            for (int i1 = 0; i1 < 10 && !list.isEmpty(); ++i1) {
                                EntityLiving entityliving = (EntityLiving) list.get(this.random.nextInt(list.size()));
                                if (entityliving != this && entityliving.isAlive() && this.hasLineOfSight(entityliving)) {
                                    if (entityliving instanceof EntityHuman) {
                                        if (!((EntityHuman) entityliving).abilities.isInvulnerable) {
                                            this.b(i, entityliving.getId());
                                        }
                                    } else {
                                        this.b(i, entityliving.getId());
                                    }
                                    break;
                                }

                                list.remove(entityliving);
                            }
                        }
                    }
                }
                //DBG
//                if(this.getGoalTarget()==null){
//                    Bukkit.broadcastMessage("getgoaltarget null");
//                }else{
//                    Bukkit.broadcastMessage(this.getGoalTarget().getBukkitEntity().getName());
//                }
                //DBG END
                if (this.getGoalTarget() != null) {
                    this.b(0, this.getGoalTarget().getId());
                } else {
                    this.b(0, 0);
                }

//                if (this.bp > 0) {
//                    --this.bp;
//                    if (this.bp == 0 && this.world.getGameRules().getBoolean("mobGriefing")) {
                //NEW
                if (bp > 0) {
                    --bp;
                    setPrivateField("bp",EntityWither.class,this,bp);
                    if (bp == 0 && this.world.getGameRules().getBoolean("mobGriefing")) {
                //NEW END
                        int var32 = MathHelper.floor(this.locY);
                        int j = MathHelper.floor(this.locX);
                        int j1 = MathHelper.floor(this.locZ);
                        boolean flag = false;

                        for (int k1 = -1; k1 <= 1; ++k1) {
                            for (int l1 = -1; l1 <= 1; ++l1) {
                                for (int i2 = 0; i2 <= 3; ++i2) {
                                    int j2 = j + k1;
                                    int k2 = var32 + i2;
                                    int l2 = j1 + l1;
                                    BlockPosition blockposition = new BlockPosition(j2, k2, l2);
                                    Block block = this.world.getType(blockposition).getBlock();
                                    if (block.getMaterial() != Material.AIR && a(block) && !CraftEventFactory.callEntityChangeBlockEvent(this, j2, k2, l2, Blocks.AIR, 0).isCancelled()) {
                                        flag = this.world.setAir(blockposition, true) || flag;
                                    }
                                }
                            }
                        }

                        if (flag) {
                            this.world.a((EntityHuman) null, 1012, new BlockPosition(this), 0);
                        }
                    }
                }

                if (this.ticksLived % 20 == 0) {
                    this.heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
                }
            }
            setPrivateField("bn",EntityWither.class,this,bn);
            setPrivateField("bo",EntityWither.class,this,bo);
            setPrivateField("bp",EntityWither.class,this,bp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }
}
