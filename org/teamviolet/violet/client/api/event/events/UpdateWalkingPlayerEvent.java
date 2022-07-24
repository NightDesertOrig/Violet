//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;

public class UpdateWalkingPlayerEvent extends CancelableEvent
{
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public static class Pre extends UpdateWalkingPlayerEvent
    {
        private static Pre INSTANCE;
        
        public static Pre get(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
            Pre.INSTANCE.x = x;
            Pre.INSTANCE.y = y;
            Pre.INSTANCE.z = z;
            Pre.INSTANCE.yaw = yaw;
            Pre.INSTANCE.pitch = pitch;
            Pre.INSTANCE.onGround = onGround;
            return Pre.INSTANCE;
        }
        
        static {
            Pre.INSTANCE = new Pre();
        }
    }
    
    public static class Post extends UpdateWalkingPlayerEvent
    {
        private static Post INSTANCE;
        
        public static Post get(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
            Post.INSTANCE.x = x;
            Post.INSTANCE.y = y;
            Post.INSTANCE.z = z;
            Post.INSTANCE.yaw = yaw;
            Post.INSTANCE.pitch = pitch;
            Post.INSTANCE.onGround = onGround;
            return Post.INSTANCE;
        }
        
        static {
            Post.INSTANCE = new Post();
        }
    }
}
