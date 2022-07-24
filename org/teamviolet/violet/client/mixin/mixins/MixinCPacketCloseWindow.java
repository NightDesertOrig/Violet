//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ CPacketCloseWindow.class })
public class MixinCPacketCloseWindow implements ICPacketCloseWindow
{
    @Shadow
    private int windowId;
    
    public int getWindowId() {
        return this.windowId;
    }
}
