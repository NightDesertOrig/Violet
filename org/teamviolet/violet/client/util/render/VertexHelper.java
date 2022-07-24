//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.util.math.*;

public class VertexHelper
{
    private final Tessellator tessellator;
    private final BufferBuilder bufferBuilder;
    private final boolean useBuffer;
    
    public VertexHelper(final boolean useBuffer) {
        this.tessellator = Tessellator.getInstance();
        this.bufferBuilder = this.tessellator.getBuffer();
        this.useBuffer = useBuffer;
    }
    
    public void begin(final int mode) {
        if (this.useBuffer) {
            this.bufferBuilder.begin(mode, DefaultVertexFormats.POSITION_COLOR);
        }
        else {
            GL11.glBegin(mode);
        }
    }
    
    public void put(final Vec2f pos, final Color color) {
        this.put(pos.x, pos.y, 0.0, color);
    }
    
    public void put(final double x, final double y, final double z, final Color color) {
        this.put(new Vec3d(x, y, z), color);
    }
    
    public void put(final Vec3d pos, final Color color) {
        if (this.useBuffer) {
            this.bufferBuilder.pos(pos.x, pos.y, pos.z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        }
        else {
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glVertex3d(pos.x, pos.y, pos.z);
        }
    }
    
    public void end() {
        if (this.useBuffer) {
            this.tessellator.draw();
        }
        else {
            GL11.glEnd();
        }
    }
}
