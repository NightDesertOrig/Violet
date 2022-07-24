//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.movement;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.MOVEMENT)
public class Sprint extends Module
{
    @Listener
    public void listen(final UpdateEvent event) {
        if (!Utils.player().isSprinting()) {
            Utils.player().setSprinting(true);
        }
    }
}
