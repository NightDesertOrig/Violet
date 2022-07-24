//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.lwjgl.input.*;
import org.teamviolet.violet.client.util.account.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.PLAYER)
public class MiddleClickFriend extends Module
{
    private boolean lastDown;
    
    protected void onEnable() {
        this.lastDown = false;
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        final boolean isDown = Mouse.isButtonDown(2);
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.entityHit != null && isDown && !this.lastDown) {
            final boolean friendNow = FriendUtil.toggleFriend(this.mc.objectMouseOver.entityHit.getUniqueID());
            if (friendNow) {
                InfoUtil.chatInfo("Added friend: '" + this.mc.objectMouseOver.entityHit.getName() + "'.");
            }
            else {
                InfoUtil.chatInfo("Removed friend: '" + this.mc.objectMouseOver.entityHit.getName() + "'.");
            }
        }
        this.lastDown = isDown;
    }
}
