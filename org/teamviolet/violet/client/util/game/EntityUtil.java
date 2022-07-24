//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.math.*;

public class EntityUtil
{
    private static final Minecraft mc;
    
    public static BlockPos getEntityPos(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY + 0.25), Math.floor(entity.posZ));
    }
    
    public static float getHealth(final EntityLivingBase entity) {
        return entity.getHealth() + entity.getAbsorptionAmount();
    }
    
    public static ChatFormatting getPlayerHPColor(final float hp) {
        if (hp > 18.0f) {
            return ChatFormatting.GREEN;
        }
        if (hp > 16.0f) {
            return ChatFormatting.DARK_GREEN;
        }
        if (hp > 12.0f) {
            return ChatFormatting.YELLOW;
        }
        if (hp > 16.0f) {
            return ChatFormatting.RED;
        }
        return ChatFormatting.DARK_RED;
    }
    
    public static boolean isPlayerMoving() {
        return EntityUtil.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil.mc.gameSettings.keyBindRight.isKeyDown();
    }
    
    public static float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difY = (to.y - from.y) * -1.0;
        final double difZ = to.z - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    public static Vec2f getInstantLookVec(final Vec3d looking, final Vec3d target) {
        final double difX = target.x - looking.x;
        final double difY = (target.y - looking.y) * -1.0;
        final double difZ = target.z - looking.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new Vec2f((float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0));
    }
    
    public static Vec2f getStepLookVec(final Vec3d target, final float maxDiff) {
        return getStepLookVec(target, maxDiff, maxDiff);
    }
    
    public static Vec2f getStepLookVec(final Vec3d target, final float maxPitchDiff, final float maxYawDiff) {
        return getStepLookVec(getEyePos((Entity)Utils.player()), RotationUtil.isSpoofing() ? RotationUtil.getRotation() : new Vec2f(Utils.player().rotationPitch, Utils.player().rotationYawHead), target, maxPitchDiff, maxYawDiff);
    }
    
    public static Vec2f getStepLookVec(final Vec3d looking, final Vec2f currentLook, final Vec3d target, final float maxPitchDiff, final float maxYawDiff) {
        final Vec2f instantLookVec = getInstantLookVec(looking, target);
        final float pitch = updateRotation(currentLook.x, instantLookVec.x, maxPitchDiff);
        final float yaw = updateRotation(currentLook.y, instantLookVec.y, maxYawDiff);
        return new Vec2f(pitch, yaw);
    }
    
    private static float updateRotation(final float current, final float target, final float max) {
        float f = MathHelper.wrapDegrees(target - current);
        if (f > max) {
            f = max;
        }
        if (f < -max) {
            f = -max;
        }
        return current + f;
    }
    
    public static Vec3d getEyePos(final Entity entity) {
        return new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
    }
    
    public static Vec3d getCenterPos(final Entity entity) {
        return new Vec3d(entity.posX, entity.posY + entity.height / 2.0, entity.posZ);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float ticks) {
        return getInterpolatedPos(entity, ticks).subtract(Minecraft.getMinecraft().getRenderManager().viewerPosX, Minecraft.getMinecraft().getRenderManager().viewerPosY, Minecraft.getMinecraft().getRenderManager().viewerPosZ);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final double ticks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getLastTickPos(entity, ticks, ticks, ticks));
    }
    
    public static Vec3d getLastTickPos(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
