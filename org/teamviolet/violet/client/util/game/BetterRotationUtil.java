//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.*;

public class BetterRotationUtil
{
    private float targetYaw;
    private float targetPitch;
    private double playerX;
    private double playerY;
    private double playerZ;
    private boolean rotationsSet;
    private boolean changed;
    
    public BetterRotationUtil() {
        this.targetYaw = 0.0f;
        this.targetPitch = 0.0f;
        this.playerX = 0.0;
        this.playerY = 0.0;
        this.playerZ = 0.0;
        this.rotationsSet = false;
        this.changed = false;
        Violet.getViolet().getDispatcher().register((Object)this);
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.changed && this.rotationsSet) {
            this.changed = false;
            this.sendRotationPacket(this.targetYaw, this.targetPitch);
        }
    }
    
    @Listener
    public void listen(final PacketEvent.Write event) {
        if (this.rotationsSet && event.getPacket() instanceof CPacketPlayer) {
            final ICPacketPlayer icPacketPlayer = (ICPacketPlayer)event.getPacket();
            icPacketPlayer.setRotating(true);
            icPacketPlayer.setYaw(this.targetYaw);
            icPacketPlayer.setPitch(this.targetPitch);
        }
    }
    
    private void sendRotationPacket(final float yaw, final float pitch) {
        Utils.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, Utils.mc.player.onGround));
    }
    
    public void setRotations(final float yaw, final float pitch) {
        this.rotationsSet = true;
        if (yaw != this.targetYaw || pitch != this.targetPitch || this.playerX != Utils.mc.player.posX || this.playerY != Utils.mc.player.posY || this.playerZ != Utils.mc.player.posZ) {
            this.changed = true;
            this.targetYaw = yaw;
            this.targetPitch = pitch;
            this.playerX = Utils.mc.player.posX;
            this.playerY = Utils.mc.player.posY;
            this.playerZ = Utils.mc.player.posZ;
        }
    }
    
    public void resetRotations() {
        this.rotationsSet = false;
    }
}
