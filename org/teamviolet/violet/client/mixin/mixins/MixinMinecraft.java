//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft implements IMinecraft
{
    @Shadow
    private int rightClickDelayTimer;
    @Shadow
    public EntityPlayerSP player;
    @Shadow
    public WorldClient world;
    
    @Inject(method = { "runTick" }, at = { @At("HEAD") })
    public void runTickHead(final CallbackInfo ci) {
        if (this.player != null && this.world != null) {
            Violet.getViolet().getDispatcher().post((Object)new UpdateEvent());
        }
    }
    
    public void setRightClickDelayTimer(final int rightClickDelayTimer) {
        this.rightClickDelayTimer = rightClickDelayTimer;
    }
}
