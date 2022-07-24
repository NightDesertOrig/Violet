//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class SelfXP extends Module
{
    @Listener
    public void listen(final PacketEvent.Write event) {
        if (!(event.getPacket() instanceof CPacketPlayer)) {
            return;
        }
        if (InventoryUtil.getServerItem() == Items.EXPERIENCE_BOTTLE) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)event.getPacket();
            ((ICPacketPlayer)cPacketPlayer).setPitch(180.0f);
        }
    }
}
