//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.movement;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.MOVEMENT)
public class Step extends Module
{
    private final Value<Mode> mode;
    private final Value<Float> height;
    
    public Step() {
        this.mode = (Value<Mode>)new ValueFactory().withName("BackgroundMode").withVal((Object)Mode.VANILLA).build((Module)this);
        this.height = (Value<Float>)ValueFactory.floatValue().withName("Height").withVal((Object)2.0f).withBounds(0.0f, 4.0f, 1).build((Module)this);
    }
    
    protected void onDisable() {
        Utils.player().stepHeight = 0.625f;
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        Utils.player().stepHeight = (float)((this.mode.getValue() == Mode.VANILLA) ? this.height.getValue() : 0.625f);
    }
    
    public enum Mode
    {
        VANILLA, 
        PACKET;
    }
}
