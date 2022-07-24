//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.network.*;

public class PacketEvent extends CancelableEvent
{
    private final Packet<?> packet;
    
    private PacketEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public Packet<?> getPacket() {
        return this.packet;
    }
    
    public static class Read extends PacketEvent
    {
        public Read(final Packet<?> packet) {
            super(packet, null);
        }
    }
    
    public static class Write extends PacketEvent
    {
        public Write(final Packet<?> packet) {
            super(packet, null);
        }
    }
}
