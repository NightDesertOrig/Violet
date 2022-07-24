//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.layers.*;

public class RenderLayerEvent extends CancelableEvent
{
    public EntityLivingBase entity;
    public LayerRenderer<?> layer;
    
    public RenderLayerEvent(final EntityLivingBase entity, final LayerRenderer<?> layer) {
        this.entity = entity;
        this.layer = layer;
    }
}
