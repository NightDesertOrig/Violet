//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.entity.item.*;

public class RenderEnderCrystalEvent extends CancelableEvent
{
    private final EntityEnderCrystal entityEnderCrystal;
    private final double x;
    private final double y;
    private final double z;
    private int render;
    private boolean renderAgain;
    
    public RenderEnderCrystalEvent(final EntityEnderCrystal entityEnderCrystal, final double x, final double y, final double z, final int render) {
        this.renderAgain = false;
        this.entityEnderCrystal = entityEnderCrystal;
        this.x = x;
        this.y = y;
        this.z = z;
        this.render = render;
    }
    
    public EntityEnderCrystal getEntityEnderCrystal() {
        return this.entityEnderCrystal;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setRenderAgain(final boolean renderAgain) {
        this.renderAgain = renderAgain;
    }
    
    public boolean shouldRenderAgain() {
        return this.renderAgain;
    }
    
    public int getRender() {
        return this.render;
    }
    
    public static class Pre extends RenderEnderCrystalEvent
    {
        public Pre(final EntityEnderCrystal entityEnderCrystal, final double x, final double y, final double z, final int render) {
            super(entityEnderCrystal, x, y, z, render);
        }
    }
    
    public static class Post extends RenderEnderCrystalEvent
    {
        public Post(final EntityEnderCrystal entityEnderCrystal, final double x, final double y, final double z, final int render) {
            super(entityEnderCrystal, x, y, z, render);
        }
    }
}
