//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;

public class TotemPopEvent extends CancelableEvent
{
    private EntityPlayer player;
    
    public TotemPopEvent(final EntityPlayer player) {
        this.player = player;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public String getName() {
        return this.player.getName();
    }
    
    public Vec3d getPos() {
        return this.player.getPositionVector();
    }
}
