//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.server.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class Velocity extends Module
{
    @Listener
    public void listen(final PacketEvent.Read event) {
        if (event.getPacket() instanceof SPacketExplosion) {
            event.cancel();
        }
    }
}
