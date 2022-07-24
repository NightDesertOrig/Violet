//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.entity.*;

public class RenderPopChamEvent extends CancelableEvent
{
    private final EntityPopCham popcham;
    private int render;
    private boolean renderAgain;
    
    public RenderPopChamEvent(final EntityPopCham popcham, final int render) {
        this.renderAgain = false;
        this.popcham = popcham;
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
    
    public EntityLivingBase getPopcham() {
        return (EntityLivingBase)this.popcham;
    }
    
    public static class Pre extends RenderPopChamEvent
    {
        public Pre(final EntityPopCham popcham, final int render) {
            super(popcham, render);
        }
    }
    
    public static class Post extends RenderPopChamEvent
    {
        public Post(final EntityPopCham popcham, final int render) {
            super(popcham, render);
        }
    }
}
