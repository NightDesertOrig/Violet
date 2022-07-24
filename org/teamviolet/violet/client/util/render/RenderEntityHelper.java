//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.model.*;
import java.awt.*;
import org.lwjgl.opengl.*;

public class RenderEntityHelper
{
    private static final Minecraft mc;
    
    public static void setupRenderStatic(final Entity entityIn, final float partialTicks, final boolean p_188388_3_) {
        final IRenderManager irm = (IRenderManager)RenderEntityHelper.mc.getRenderManager();
        if (entityIn.ticksExisted == 0) {
            entityIn.lastTickPosX = entityIn.posX;
            entityIn.lastTickPosY = entityIn.posY;
            entityIn.lastTickPosZ = entityIn.posZ;
        }
        final double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * partialTicks;
        final double d2 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * partialTicks;
        final double d3 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * partialTicks;
        final float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;
        int i = entityIn.getBrightnessForRender();
        if (entityIn.isBurning()) {
            i = 15728880;
        }
        final int j = i % 65536;
        final int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.translate(d0 - irm.getRenderPosX(), d2 - irm.getRenderPosY(), d3 - irm.getRenderPosZ());
    }
    
    public static void renderGhost(final EntityPlayer player, final ModelPlayer model, final float partialTicks, final Color color, final float alpha) {
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
        setupRenderStatic((Entity)player, partialTicks, false);
        prepareScale();
        GlStateManager.rotate(player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, alpha);
        GL11.glEnable(2896);
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glPolygonMode(1032, 6913);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, alpha);
        GL11.glLineWidth(2.0f);
        model.render((Entity)player, player.limbSwing, player.limbSwingAmount, (float)player.ticksExisted, 180.0f - player.rotationYawHead, player.rotationPitch, 0.1f);
        GL11.glEnable(2896);
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }
    
    private static float prepareScale() {
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(0.0f, -2.489f, 0.0f);
        final float f = 0.1f;
        return f;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
