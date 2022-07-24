//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.particles;

import net.minecraft.util.math.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;

public class Particle
{
    public Vec2f velocity;
    public Vec2f pos;
    
    public Particle(final Vec2f pos, final Vec2f velocity) {
        this.pos = pos;
        this.velocity = velocity;
    }
    
    public void draw(final Color color, final float radius) {
        Render2D.drawRoundedRectangleFilled(Render2D.vertexHelperUB, this.pos, new Vec2f(this.pos.x + radius * 2.0f, this.pos.y + radius * 2.0f), radius, 20, color);
    }
}
