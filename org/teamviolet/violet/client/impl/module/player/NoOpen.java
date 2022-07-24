//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class NoOpen extends Module
{
    @Listener
    private void listen(final PacketEvent.Write event) {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
            if (this.mc.world.getBlockState(packet.getPos()).getBlock() == Blocks.ENDER_CHEST || this.mc.world.getBlockState(packet.getPos()).getBlock() == Blocks.CHEST || this.mc.world.getBlockState(packet.getPos()).getBlock() == Blocks.TRAPPED_CHEST || this.mc.world.getBlockState(packet.getPos()).getBlock() == Blocks.DISPENSER || this.mc.world.getBlockState(packet.getPos()).getBlock() == Blocks.DROPPER) {
                event.cancel();
            }
        }
    }
}
