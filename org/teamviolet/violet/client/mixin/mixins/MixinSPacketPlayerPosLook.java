//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ SPacketPlayerPosLook.class })
public class MixinSPacketPlayerPosLook implements ISPacketPlayerPosLook
{
    @Shadow
    private float yaw;
    @Shadow
    private float pitch;
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
}
