//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.effect.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class Lightning extends Module
{
    @Listener
    private void listen(final UpdateEvent event) {
        for (final EntityPlayer player : this.mc.world.playerEntities) {
            if (player != null) {
                if (player.getHealth() > 0.0f) {
                    continue;
                }
                this.mc.world.addWeatherEffect((Entity)new EntityLightningBolt((World)this.mc.world, player.posX, player.posY, player.posZ, true));
            }
        }
    }
}
