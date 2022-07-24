//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.entity.*;

public class RenderLivingModelEvent extends CancelableEvent
{
    private final EntityLivingBase entity;
    private int render;
    private boolean renderAgain;
    
    public RenderLivingModelEvent(final EntityLivingBase entity, final int render) {
        this.renderAgain = false;
        this.entity = entity;
        this.render = render;
    }
    
    public void setRenderAgain(final boolean val) {
        this.renderAgain = val;
    }
    
    public boolean shouldRenderAgain() {
        return this.renderAgain;
    }
    
    public int getRender() {
        return this.render;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public static class Pre extends RenderLivingModelEvent
    {
        public Pre(final EntityLivingBase entity, final int render) {
            super(entity, render);
        }
    }
    
    public static class Post extends RenderLivingModelEvent
    {
        public Post(final EntityLivingBase entity, final int render) {
            super(entity, render);
        }
    }
}
