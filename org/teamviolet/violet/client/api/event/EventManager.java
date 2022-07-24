//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event;

import net.minecraft.client.*;
import org.teamviolet.violet.client.*;
import net.minecraft.client.gui.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.module.hud.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.text.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraftforge.common.*;

public class EventManager
{
    private final Minecraft mc;
    
    public EventManager() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void listen(final RenderGameOverlayEvent.Text event) {
        Violet.getViolet().getDispatcher().post(new Render2DEvent(event.getPartialTicks(), new ScaledResolution(this.mc)));
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            if (module instanceof HudModule && module.isEnabled() && this.mc.currentScreen == null) {
                ((HudModule)module).draw();
            }
        }
    }
    
    @SubscribeEvent
    public void listen(final RenderWorldLastEvent event) {
        Violet.getViolet().getDispatcher().post(new Render3DEvent.Pre(event.getPartialTicks()));
        Violet.getViolet().getDispatcher().post(new Render3DEvent(event.getPartialTicks()));
    }
    
    @SubscribeEvent
    public void listen(final ClientChatReceivedEvent event) {
        final ChatEvent.Incoming incoming = new ChatEvent.Incoming(event.getMessage().getFormattedText());
        Violet.getViolet().getDispatcher().post(incoming);
        if (incoming.isCanceled()) {
            event.setCanceled(true);
        }
        else {
            event.setMessage((ITextComponent)new TextComponentString(incoming.getMessage()));
        }
    }
    
    @Listener
    public void listen(final PacketEvent.Write event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)event.getPacket();
            final ChatEvent.Outgoing outgoing = new ChatEvent.Outgoing(cPacketChatMessage.getMessage());
            Violet.getViolet().getDispatcher().post(outgoing);
            if (outgoing.isCanceled()) {
                event.cancel();
            }
            else {
                ((ICPacketChatMessage)cPacketChatMessage).setMessage(outgoing.getMessage());
            }
        }
    }
    
    @Listener
    public void listen(final PacketEvent.Read event) {
        if (event.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35 && packet.getEntity((World)this.mc.world) instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)packet.getEntity((World)this.mc.world);
                Violet.getViolet().getDispatcher().post(new TotemPopEvent(player));
            }
        }
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        Violet.getViolet().getDispatcher().register(this);
    }
}
