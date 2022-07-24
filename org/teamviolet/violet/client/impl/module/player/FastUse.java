//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class FastUse extends Module
{
    private final Value<Integer> delay;
    private final Value<Boolean> xpOnly;
    private int waited;
    
    public FastUse() {
        this.delay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)0).withBounds(0.0f, 4.0f, 0).build((Module)this);
        this.xpOnly = (Value<Boolean>)ValueFactory.booleanValue().withName("OnlyXp").withVal((Object)false).build((Module)this);
        this.waited = 0;
    }
    
    protected void onEnable() {
        this.waited = (int)this.delay.getValue();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (++this.waited > (int)this.delay.getValue() && (((boolean)this.xpOnly.getValue() && this.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) || !(boolean)this.xpOnly.getValue())) {
            ((IMinecraft)this.mc).setRightClickDelayTimer(0);
            this.waited = 0;
        }
    }
}
