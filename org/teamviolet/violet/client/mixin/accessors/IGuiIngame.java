//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ GuiIngame.class })
public interface IGuiIngame
{
    @Invoker("renderPlayerStats")
    void invokeRenderStats(final ScaledResolution p0);
}
