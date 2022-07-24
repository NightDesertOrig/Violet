//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.movement;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.MOVEMENT)
public class Warp extends Module
{
    private final Value<Integer> tickCount;
    private final Value<Integer> downtime;
    private final Value<Double> multiplier;
    private final Value<Boolean> multiplyY;
    private int ticked;
    private boolean downtiming;
    
    public Warp() {
        this.tickCount = (Value<Integer>)ValueFactory.intValue().withName("Ticks").withVal((Object)5).withBounds(1.0f, 30.0f, 0).build((Module)this);
        this.downtime = (Value<Integer>)ValueFactory.intValue().withName("Downtime").withVal((Object)20).withBounds(1.0f, 30.0f, 0).build((Module)this);
        this.multiplier = (Value<Double>)ValueFactory.doubleValue().withName("Multiplier").withVal((Object)1.5).withBounds(1.1f, 3.0f, 1).build((Module)this);
        this.multiplyY = (Value<Boolean>)ValueFactory.booleanValue().withName("Multiply Y").withVal((Object)true).build((Module)this);
        this.ticked = 0;
        this.downtiming = false;
    }
    
    protected void onEnable() {
        this.ticked = 0;
    }
    
    @Listener
    public void listen(final PlayerMoveEvent event) {
        if (!this.downtiming) {
            if (this.ticked++ >= (int)this.tickCount.getValue()) {
                this.ticked = 0;
                this.downtiming = true;
            }
            else {
                event.setX(event.getX() * (double)this.multiplier.getValue());
                if (this.multiplyY.getValue()) {
                    event.setY(event.getY() * (double)this.multiplier.getValue());
                }
                event.setZ(event.getZ() * (double)this.multiplier.getValue());
            }
        }
        else if (this.ticked++ >= (int)this.downtime.getValue()) {
            this.ticked = 0;
            this.downtiming = false;
        }
    }
}
