//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.util.math.*;

@Module.Manifest(Module.Category.PLAYER)
public class CamTest extends Module
{
    public float spoofedYaw;
    public float spoofedPitch;
    public float prevSpoofedYaw;
    public float prevSpoofedPitch;
    private BlockPos p;
    private static CamTest instance;
    
    public CamTest() {
        this.spoofedYaw = 0.0f;
        this.spoofedPitch = 0.0f;
        this.prevSpoofedYaw = 0.0f;
        this.prevSpoofedPitch = 0.0f;
        this.p = null;
    }
    
    protected void onEnable() {
        if (this.p != null) {
            return;
        }
        this.p = new BlockPos((Entity)this.mc.player);
    }
    
    @Listener
    private void listen(final Render3DEvent event) {
        if (Utils.nullCheck() || this.p == null) {
            return;
        }
        final Vec3d vec = new Vec3d((Vec3i)this.p).add(0.5, 0.0, 0.5);
        final float[] angles = RotationUtil.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
        Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
    }
    
    protected void onDisable() {
        Violet.getViolet().getRotationUtil().resetRotations();
    }
    
    public void turn(final float yaw, final float pitch) {
        final float f = this.spoofedPitch;
        final float f2 = this.spoofedYaw;
        this.spoofedYaw += (float)(yaw * 0.15);
        this.spoofedPitch -= (float)(pitch * 0.15);
        this.spoofedPitch = MathHelper.clamp(this.spoofedPitch, -90.0f, 90.0f);
        this.prevSpoofedPitch += this.spoofedPitch - f;
        this.prevSpoofedYaw += this.spoofedYaw - f2;
    }
    
    public static CamTest getInstance() {
        if (CamTest.instance == null) {
            CamTest.instance = new CamTest();
        }
        return CamTest.instance;
    }
}
