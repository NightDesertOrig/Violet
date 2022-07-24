//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util.pathfinding.navmesh;

public class Link
{
    private float px;
    private float py;
    private Space target;
    
    public Link(final float px, final float py, final Space target) {
        this.px = px;
        this.py = py;
        this.target = target;
    }
    
    public float distance2(final float tx, final float ty) {
        final float dx = tx - this.px;
        final float dy = ty - this.py;
        return dx * dx + dy * dy;
    }
    
    public float getX() {
        return this.px;
    }
    
    public float getY() {
        return this.py;
    }
    
    public Space getTarget() {
        return this.target;
    }
}
