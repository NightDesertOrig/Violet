//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class SecretClose extends Module
{
    private final Value<Mode> mode;
    
    public SecretClose() {
        this.mode = (Value<Mode>)new ValueFactory().withName("Mode").withVal((Object)Mode.XCarry).build((Module)this);
    }
    
    @Listener
    public void listen(final PacketEvent.Write event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (event.getPacket() instanceof CPacketCloseWindow) {
            if (this.mode.getValue() == Mode.XCarry) {
                final CPacketCloseWindow packet = (CPacketCloseWindow)event.getPacket();
                if (((ICPacketCloseWindow)packet).getWindowId() == this.mc.player.inventoryContainer.windowId) {
                    event.cancel();
                }
            }
            else {
                event.cancel();
            }
        }
    }
    
    private enum Mode
    {
        XCarry, 
        Illegals;
    }
}
