//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ CPacketUseEntity.class })
public class MixinCPacketUseEntity implements ICPacketUseEntity
{
    @Shadow
    private int entityId;
    @Shadow
    private CPacketUseEntity.Action action;
    
    public void setEntityId(final int id) {
        this.entityId = id;
    }
    
    public void setAction(final CPacketUseEntity.Action action) {
        this.action = action;
    }
}
