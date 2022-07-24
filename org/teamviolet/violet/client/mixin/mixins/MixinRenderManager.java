//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ RenderManager.class })
public class MixinRenderManager implements IRenderManager
{
    @Shadow
    private double renderPosX;
    @Shadow
    private double renderPosY;
    @Shadow
    private double renderPosZ;
    
    public double getRenderPosX() {
        return this.renderPosX;
    }
    
    public double getRenderPosY() {
        return this.renderPosY;
    }
    
    public double getRenderPosZ() {
        return this.renderPosZ;
    }
}
