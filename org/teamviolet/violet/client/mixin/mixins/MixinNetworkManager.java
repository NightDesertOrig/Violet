//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.*;
import org.spongepowered.asm.mixin.injection.*;
import io.netty.channel.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    public void sendPacketHead(final Packet<?> packet, final CallbackInfo ci) {
        final PacketEvent.Write packetWriteEvent = new PacketEvent.Write((Packet)packet);
        Violet.getViolet().getDispatcher().post((Object)packetWriteEvent);
        if (packetWriteEvent.isCanceled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "channelRead0*" }, at = { @At("HEAD") }, cancellable = true)
    public void channelRead0Head(final ChannelHandlerContext channelHandlerContext, final Packet<?> packet, final CallbackInfo ci) {
        final PacketEvent.Read packetReadEvent = new PacketEvent.Read((Packet)packet);
        Violet.getViolet().getDispatcher().post((Object)packetReadEvent);
        if (packetReadEvent.isCanceled()) {
            ci.cancel();
        }
    }
}
