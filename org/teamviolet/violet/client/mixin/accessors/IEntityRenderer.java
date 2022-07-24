//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityRenderer.class })
public interface IEntityRenderer
{
    @Invoker("setupCameraTransform")
    void invokeSetupCameraTransform(final float p0, final int p1);
    
    @Invoker("renderHand")
    void invokeRenderHand(final float p0, final int p1);
    
    @Accessor("lightmapUpdateNeeded")
    void setLightmapUpdateNeeded(final boolean p0);
}
