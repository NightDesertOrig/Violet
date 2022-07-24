//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public enum SwingHand
{
    Mainhand(EnumHand.MAIN_HAND), 
    Offhand(EnumHand.OFF_HAND), 
    None((EnumHand)null);
    
    private final EnumHand hand;
    
    private SwingHand(final EnumHand hand) {
        this.hand = hand;
    }
    
    public void swing() {
        this.swing(false);
    }
    
    public void swing(final boolean packet) {
        if (this.hand != null) {
            if (packet) {
                Utils.sendPacket((Packet<?>)new CPacketAnimation(this.hand));
            }
            else {
                Utils.player().swingArm(this.hand);
            }
        }
    }
}
