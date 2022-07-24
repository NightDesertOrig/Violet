//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ GuiIngame.class })
public abstract class MixinGuiIngame implements IGuiIngame
{
    public void invokeRenderStats(final ScaledResolution res) {
        this.renderPlayerStats(res);
    }
    
    @Shadow
    protected abstract void renderPlayerStats(final ScaledResolution p0);
}
