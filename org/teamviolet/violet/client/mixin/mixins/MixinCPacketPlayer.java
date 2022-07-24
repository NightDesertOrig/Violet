//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ CPacketPlayer.class })
public class MixinCPacketPlayer implements ICPacketPlayer
{
    @Shadow
    protected boolean rotating;
    @Shadow
    protected float yaw;
    @Shadow
    protected float pitch;
    
    public void setRotating(final boolean rotating) {
        this.rotating = rotating;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
}
