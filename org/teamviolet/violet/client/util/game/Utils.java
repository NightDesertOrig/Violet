//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;

public class Utils
{
    public static final float VANILLA_STEP_HEIGHT = 0.625f;
    public static final Minecraft mc;
    
    public static boolean nullCheck() {
        return Utils.mc.player == null || Utils.mc.world == null;
    }
    
    public static EntityPlayerSP player() {
        return Utils.mc.player;
    }
    
    public static WorldClient world() {
        return Utils.mc.world;
    }
    
    public static List<EntityPlayer> playerEntities() {
        return (List<EntityPlayer>)world().playerEntities;
    }
    
    public static List<Entity> loadedEntityList() {
        return (List<Entity>)world().loadedEntityList;
    }
    
    public static double getHealthPoints(final EntityLivingBase entity) {
        return entity.getHealth() + entity.getAbsorptionAmount();
    }
    
    public static void sendPacket(final Packet<?> packet) {
        player().connection.sendPacket((Packet)packet);
    }
    
    public static CPacketUseEntity attackEntityPacket(final int id) {
        final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
        ((ICPacketUseEntity)cPacketUseEntity).setEntityId(id);
        ((ICPacketUseEntity)cPacketUseEntity).setAction(CPacketUseEntity.Action.ATTACK);
        return cPacketUseEntity;
    }
    
    private Utils() {
        throw new UnsupportedOperationException();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
