//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import java.awt.*;

public class GlStateHelper extends GlStateManager
{
    private static final Minecraft mc;
    private static Rectangle lastScissor;
    
    public static void scissor(final double x, final double y, final double width, final double height) {
        GlStateHelper.lastScissor = new Rectangle((int)x, (int)y, (int)width, (int)height);
        GL11.glScissor((int)x, (int)y, (int)width, (int)height);
    }
    
    public static void enableScissor() {
        GL11.glEnable(3089);
    }
    
    public static void disableScissor() {
        GL11.glDisable(3089);
    }
    
    public static void stencil(final double x, final double y, final double width, final double height) {
    }
    
    public static void enableStencil() {
        GL11.glEnable(2960);
    }
    
    public static void disableStencil() {
        GL11.glDisable(2960);
    }
    
    public static void scale(final float scale) {
        scale(scale, scale, scale);
    }
    
    public static void rescale() {
        final ScaledResolution sr = new ScaledResolution(GlStateHelper.mc);
        clear(256);
        viewport(0, 0, GlStateHelper.mc.displayWidth, GlStateHelper.mc.displayHeight);
        matrixMode(5889);
        loadIdentity();
        ortho(0.0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
        matrixMode(5888);
        loadIdentity();
        translate(0.0f, 0.0f, -2000.0f);
    }
    
    public static void color(final Color c) {
        color(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
    }
    
    public static void prepareGl2d() {
        disableAlpha();
        disableTexture2D();
        enableBlend();
        shadeModel(7425);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        disableCull();
    }
    
    public static void releaseGl2d() {
        enableAlpha();
        enableTexture2D();
        shadeModel(7424);
        GL11.glDisable(2848);
        enableCull();
    }
    
    static {
        mc = Minecraft.getMinecraft();
        GlStateHelper.lastScissor = null;
    }
}
