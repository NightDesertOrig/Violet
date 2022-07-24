//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ Minecraft.class })
public interface IMinecraft
{
    @Accessor("rightClickDelayTimer")
    void setRightClickDelayTimer(final int p0);
}
