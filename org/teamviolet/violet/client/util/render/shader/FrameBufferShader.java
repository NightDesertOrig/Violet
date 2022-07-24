//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

import net.minecraft.client.*;
import net.minecraft.client.shader.*;
import java.awt.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public abstract class FrameBufferShader extends GLSLShader
{
    protected final Minecraft mc;
    protected Framebuffer framebuffer;
    protected Color inside;
    protected Color outside;
    protected float radius;
    protected float quality;
    protected long startTime;
    protected int insideMode;
    protected int outsideMode;
    protected int glow;
    private boolean entityShadows;
    
    public FrameBufferShader(final String fragmentShader) {
        super(fragmentShader);
        this.mc = Minecraft.getMinecraft();
        this.radius = 2.0f;
        this.quality = 1.0f;
        this.startTime = TimeUtil.startTime;
    }
    
    public void startDraw(final float partialTicks) {
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        (this.framebuffer = this.setupFrameBuffer(this.framebuffer)).framebufferClear();
        this.framebuffer.bindFramebuffer(true);
        this.entityShadows = this.mc.gameSettings.entityShadows;
        this.mc.gameSettings.entityShadows = false;
        ((IEntityRenderer)this.mc.entityRenderer).invokeSetupCameraTransform(partialTicks, 0);
    }
    
    public void stopDraw(final Color inside, final float radius, final float quality) {
    }
    
    public void stopDraw(final Color inside, final Color outside, final float radius, final float quality, final int insideMode, final int outsideMode, final boolean glow) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        GlStateManager.enableBlend();
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.inside = inside;
        this.outside = outside;
        this.radius = radius;
        this.quality = quality;
        this.insideMode = insideMode;
        this.outsideMode = outsideMode;
        this.glow = (glow ? 1 : 0);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader();
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void setRadius(final float radius) {
        this.radius = radius;
    }
    
    public Framebuffer setupFrameBuffer(Framebuffer frameBuffer) {
        if (frameBuffer != null) {
            frameBuffer.deleteFramebuffer();
        }
        frameBuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
        return frameBuffer;
    }
    
    public void drawFramebuffer(final Framebuffer framebuffer) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        GL11.glBindTexture(3553, framebuffer.framebufferTexture);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 1.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(0.0, (double)scaledResolution.getScaledHeight());
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight());
        GL11.glTexCoord2d(1.0, 1.0);
        GL11.glVertex2d((double)scaledResolution.getScaledWidth(), 0.0);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }
}
