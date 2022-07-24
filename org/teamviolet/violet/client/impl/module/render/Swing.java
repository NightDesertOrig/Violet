//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class Swing extends Module
{
    private final Value<Hand> mine;
    public final Value<Speed> speed;
    public final Value<Integer> factor;
    private static Swing instance;
    
    public Swing() {
        this.mine = (Value<Hand>)new ValueFactory().withName("Hand").withVal((Object)Hand.Mainhand).build((Module)this);
        this.speed = (Value<Speed>)new ValueFactory().withName("Speed").withVal((Object)Speed.Fast).build((Module)this);
        this.factor = (Value<Integer>)new ValueFactory().withName("Factor").withVal((Object)1).withBounds(0.0f, 5.0f, 1).build((Module)this);
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (((Hand)this.mine.getValue()).equals(Hand.Offhand)) {
            this.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        else {
            this.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
    }
    
    public static Swing getInstance() {
        if (Swing.instance == null) {
            Swing.instance = new Swing();
        }
        return Swing.instance;
    }
    
    private enum Hand
    {
        Offhand, 
        Mainhand;
    }
    
    public enum Speed
    {
        Fast, 
        Slow;
    }
}
