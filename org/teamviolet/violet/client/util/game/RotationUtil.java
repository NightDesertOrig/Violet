//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.util.math.*;

public class RotationUtil
{
    private static boolean spoofing;
    private static float yaw;
    private static float pitch;
    
    public static void setRotation(final float newPitch, final float newYaw) {
        if (Rotations.getInstance().betterRotate.getValue()) {
            Rotates.update(newYaw, newPitch);
            return;
        }
        RotationUtil.pitch = newPitch;
        RotationUtil.yaw = newYaw;
        while (RotationUtil.pitch > 180.0f) {
            RotationUtil.pitch -= 360.0f;
        }
        while (RotationUtil.pitch < -180.0f) {
            RotationUtil.pitch += 360.0f;
        }
        while (RotationUtil.yaw > 180.0f) {
            RotationUtil.yaw -= 360.0f;
        }
        while (RotationUtil.yaw < -180.0f) {
            RotationUtil.yaw += 360.0f;
        }
        RotationUtil.spoofing = true;
    }
    
    public static void resetRotation() {
        RotationUtil.yaw = (RotationUtil.pitch = 0.0f);
        RotationUtil.spoofing = false;
    }
    
    public static Vec2f getRotation() {
        return new Vec2f(RotationUtil.pitch, RotationUtil.yaw);
    }
    
    public static boolean isSpoofing() {
        return RotationUtil.spoofing;
    }
    
    @Listener
    public static void listen(final PacketEvent.Write event) {
        if (Rotations.getInstance().betterRotate.getValue()) {
            return;
        }
        if (RotationUtil.spoofing && event.getPacket() instanceof CPacketPlayer) {
            final ICPacketPlayer icPacketPlayer = (ICPacketPlayer)event.getPacket();
            icPacketPlayer.setRotating(true);
            icPacketPlayer.setYaw(RotationUtil.yaw);
            icPacketPlayer.setPitch(RotationUtil.pitch);
        }
    }
    
    public static float[] calculateAngle(final Vec3d from, final Vec3d to) {
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
        return new float[] { pD, yD };
    }
    
    public static float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difY = (to.y - from.y) * -1.0;
        final double difZ = to.z - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    private RotationUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        RotationUtil.spoofing = false;
        RotationUtil.yaw = 0.0f;
        RotationUtil.pitch = 0.0f;
    }
}
