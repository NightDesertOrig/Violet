//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;

public class Rotates
{
    private static Minecraft mc;
    private static float yaw;
    private static float pitch;
    private static boolean rotationsSet;
    
    public static void init() {
        Violet.getViolet().getDispatcher().register((Object)new Rotates());
    }
    
    @Listener
    private void updateEvent(final UpdateWalkingPlayerEvent.Pre event) {
        if (!Rotates.rotationsSet || Utils.nullCheck()) {
            return;
        }
        update(getYaw(), getPitch());
        reset();
    }
    
    public static float getYaw() {
        return Rotates.yaw;
    }
    
    public static float getPitch() {
        return Rotates.pitch;
    }
    
    public static boolean isRotationsSet() {
        return Rotates.rotationsSet;
    }
    
    public static void reset() {
        Rotates.yaw = Rotates.mc.player.rotationYaw;
        Rotates.pitch = Rotates.mc.player.rotationPitch;
        Rotates.rotationsSet = false;
    }
    
    public static void setRotations(final float yaw, final float pitch) {
        Rotates.yaw = yaw;
        Rotates.pitch = pitch;
        Rotates.rotationsSet = true;
    }
    
    public static boolean safeSetRotations(final float yaw, final float pitch) {
        if (Rotates.rotationsSet) {
            return false;
        }
        setRotations(yaw, pitch);
        return true;
    }
    
    public static void lookAtPos(final BlockPos pos) {
        final float[] angle = calcAngle(Rotates.mc.player.getPositionEyes(Rotates.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() + 0.5f), (double)(pos.getZ() + 0.5f)));
        setRotations(angle[0], angle[1]);
    }
    
    public static void lookAtVec3d(final Vec3d vec3d) {
        final float[] angle = calcAngle(Rotates.mc.player.getPositionEyes(Rotates.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        setRotations(angle[0], angle[1]);
    }
    
    public static void lookAtXYZ(final double x, final double y, final double z) {
        final Vec3d vec3d = new Vec3d(x, y, z);
        lookAtVec3d(vec3d);
    }
    
    public static float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difY = (to.y - from.y) * -1.0;
        final double difZ = to.z - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        final float yD = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0);
        float pD = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist)));
        if (pD > 90.0f) {
            pD = 90.0f;
        }
        else if (pD < -90.0f) {
            pD = -90.0f;
        }
        return new float[] { yD, pD };
    }
    
    public static void update(final float yaw, final float pitch) {
        final boolean flag = Rotates.mc.player.isSprinting();
        if (flag != ((IEntityPlayerSP)Rotates.mc.player).getServerSprintState()) {
            if (flag) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Rotates.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            else {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Rotates.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            ((IEntityPlayerSP)Rotates.mc.player).setServerSprintState(flag);
        }
        final boolean flag2 = Rotates.mc.player.isSneaking();
        if (flag2 != ((IEntityPlayerSP)Rotates.mc.player).getServerSneakState()) {
            if (flag2) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Rotates.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            else {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Rotates.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            ((IEntityPlayerSP)Rotates.mc.player).setServerSneakState(flag2);
        }
        if (Rotates.mc.player == Rotates.mc.getRenderViewEntity()) {
            final AxisAlignedBB axisalignedbb = Rotates.mc.player.getEntityBoundingBox();
            final double dX = Rotates.mc.player.posX - ((IEntityPlayerSP)Rotates.mc.player).getLastReportedPosX();
            final double dY = axisalignedbb.minY - ((IEntityPlayerSP)Rotates.mc.player).getLastReportedPosY();
            final double dZ = Rotates.mc.player.posZ - ((IEntityPlayerSP)Rotates.mc.player).getLastReportedPosZ();
            final double dYaw = yaw - ((IEntityPlayerSP)Rotates.mc.player).getLastReportedYaw();
            final double dPitch = pitch - ((IEntityPlayerSP)Rotates.mc.player).getLastReportedPitch();
            ((IEntityPlayerSP)Rotates.mc.player).setPositionUpdateTicks(((IEntityPlayerSP)Rotates.mc.player).getPositionUpdateTicks() + 1);
            boolean positionChanged = dX * dX + dY * dY + dZ * dZ > 9.0E-4 || ((IEntityPlayerSP)Rotates.mc.player).getPositionUpdateTicks() >= 20;
            final boolean rotationChanged = dYaw != 0.0 || dPitch != 0.0;
            if (Rotates.mc.player.isRiding()) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Rotates.mc.player.motionX, -999.0, Rotates.mc.player.motionZ, yaw, pitch, Rotates.mc.player.onGround));
                positionChanged = false;
            }
            else if (positionChanged && rotationChanged) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Rotates.mc.player.posX, axisalignedbb.minY, Rotates.mc.player.posZ, yaw, pitch, Rotates.mc.player.onGround));
            }
            else if (positionChanged) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Rotates.mc.player.posX, axisalignedbb.minY, Rotates.mc.player.posZ, Rotates.mc.player.onGround));
            }
            else if (rotationChanged) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, Rotates.mc.player.onGround));
            }
            else if (((IEntityPlayerSP)Rotates.mc.player).getPrevOnGround() != Rotates.mc.player.onGround) {
                Rotates.mc.player.connection.sendPacket((Packet)new CPacketPlayer(Rotates.mc.player.onGround));
            }
            if (positionChanged) {
                ((IEntityPlayerSP)Rotates.mc.player).setLastReportedPosX(Rotates.mc.player.posX);
                ((IEntityPlayerSP)Rotates.mc.player).setLastReportedPosY(axisalignedbb.minY);
                ((IEntityPlayerSP)Rotates.mc.player).setLastReportedPosZ(Rotates.mc.player.posZ);
                ((IEntityPlayerSP)Rotates.mc.player).setPositionUpdateTicks(0);
            }
            if (rotationChanged) {
                ((IEntityPlayerSP)Rotates.mc.player).setLastReportedYaw(yaw);
                ((IEntityPlayerSP)Rotates.mc.player).setLastReportedPitch(pitch);
            }
            ((IEntityPlayerSP)Rotates.mc.player).setPrevOnGround(Rotates.mc.player.onGround);
            ((IEntityPlayerSP)Rotates.mc.player).setAutoJumpEnabled(Rotates.mc.gameSettings.autoJump);
        }
    }
    
    static {
        Rotates.mc = Minecraft.getMinecraft();
        Rotates.rotationsSet = false;
    }
}
