//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.client.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class RenderUtil
{
    private static final Tessellator TESSELLATOR;
    private static final BufferBuilder BUFFER_BUILDER;
    private static final Minecraft mc;
    
    public static void drawBoxBlockPosShader(final BlockPos blockPos, final Color color, final RenderMode renderMode) {
        drawBoxBlockPosShader(blockPos, 0.0, 0.0, 0.0, color, renderMode);
    }
    
    public static void drawBoxBlockPosShader(final BlockPos blockPos, final double height, final double length, final double width, final Color color, final RenderMode renderMode) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderBuilder.glSetup();
        switch (renderMode) {
            case FILL: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                break;
            }
        }
        RenderBuilder.glRelease();
    }
    
    public static void drawSelectionBoxShader(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.BUFFER_BUILDER.begin(5, DefaultVertexFormats.POSITION_COLOR);
        addChainedFilledBoxVertices(RenderUtil.BUFFER_BUILDER, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + length, axisAlignedBB.maxY + height, axisAlignedBB.maxZ + width, color);
        RenderUtil.TESSELLATOR.draw();
    }
    
    public static void drawBoxBlockPos(final BlockPos blockPos, final Color color, final RenderMode renderMode) {
        drawBoxBlockPos(blockPos, 0.0, 0.0, 0.0, color, renderMode, 180);
    }
    
    public static void drawBoxBlockPos(final BlockPos blockPos, final Color color, final RenderMode renderMode, final int alpha) {
        drawBoxBlockPos(blockPos, 0.0, 0.0, 0.0, color, renderMode, alpha);
    }
    
    public static void drawBoxBlockPos(final BlockPos blockPos, final double height, final double length, final double width, final Color color, final RenderMode renderMode, final int alpha) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderBuilder.glSetup();
        switch (renderMode) {
            case FILL: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                break;
            }
            case OUTLINE: {
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
            case BOTH: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
            case GLOW: {
                RenderBuilder.glPrepare();
                drawSelectionGlowFilledBox(axisAlignedBB, height, length, width, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
                RenderBuilder.glRestore();
                break;
            }
            case CLAW: {
                drawClawBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
        }
        RenderBuilder.glRelease();
    }
    
    public static void renderAxis(final AxisAlignedBB axisAlignedBB, final Color color, final int alpha) {
        RenderBuilder.glSetup();
        drawSelectionBox(axisAlignedBB, 0.0, 0.0, 0.0, color);
        drawSelectionBoundingBox(axisAlignedBB, 0.0, 0.0, 0.0, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
        RenderBuilder.glRelease();
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color, final RenderMode renderMode, final int alpha) {
        RenderBuilder.glSetup();
        switch (renderMode) {
            case FILL: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                break;
            }
            case OUTLINE: {
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
            case BOTH: {
                drawSelectionBox(axisAlignedBB, height, length, width, color);
                drawSelectionBoundingBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
            case GLOW: {
                RenderBuilder.glPrepare();
                drawSelectionGlowFilledBox(axisAlignedBB, height, length, width, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
                RenderBuilder.glRestore();
                break;
            }
            case CLAW: {
                drawClawBox(axisAlignedBB, height, length, width, new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                break;
            }
        }
        RenderBuilder.glRelease();
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color, final RenderMode renderMode) {
        drawBox(axisAlignedBB, height, length, width, color, renderMode, 180);
    }
    
    public static void drawSelectionBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.BUFFER_BUILDER.begin(5, DefaultVertexFormats.POSITION_COLOR);
        addChainedFilledBoxVertices(RenderUtil.BUFFER_BUILDER, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + length, axisAlignedBB.maxY + height, axisAlignedBB.maxZ + width, color);
        RenderUtil.TESSELLATOR.draw();
    }
    
    public static void addChainedFilledBoxVertices(final BufferBuilder builder, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        builder.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(minX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        builder.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.BUFFER_BUILDER.begin(3, DefaultVertexFormats.POSITION_COLOR);
        addChainedBoundingBoxVertices(RenderUtil.BUFFER_BUILDER, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + length, axisAlignedBB.maxY + height, axisAlignedBB.maxZ + width, color);
        RenderUtil.TESSELLATOR.draw();
    }
    
    public static void addChainedBoundingBoxVertices(final BufferBuilder buffer, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        buffer.pos(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
    }
    
    public static void drawSelectionGlowFilledBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color startColor, final Color endColor) {
        RenderUtil.BUFFER_BUILDER.begin(7, DefaultVertexFormats.POSITION_COLOR);
        addChainedGlowBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + length, axisAlignedBB.maxY + height, axisAlignedBB.maxZ + width, startColor, endColor);
        RenderUtil.TESSELLATOR.draw();
        drawSelectionBoundingBox(axisAlignedBB, -1.0, 0.0, 0.0, new Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue(), 255));
    }
    
    public static void addChainedGlowBoxVertices(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color startColor, final Color endColor) {
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(maxX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, minZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, minY, maxZ).color(startColor.getRed() / 255.0f, startColor.getGreen() / 255.0f, startColor.getBlue() / 255.0f, startColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, maxZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
        RenderUtil.BUFFER_BUILDER.pos(minX, maxY, minZ).color(endColor.getRed() / 255.0f, endColor.getGreen() / 255.0f, endColor.getBlue() / 255.0f, endColor.getAlpha() / 255.0f).endVertex();
    }
    
    public static void drawClawBox(final AxisAlignedBB axisAlignedBB, final double height, final double length, final double width, final Color color) {
        RenderUtil.BUFFER_BUILDER.begin(3, DefaultVertexFormats.POSITION_COLOR);
        addChainedClawBoxVertices(RenderUtil.BUFFER_BUILDER, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + length, axisAlignedBB.maxY + height, axisAlignedBB.maxZ + width, color);
        RenderUtil.TESSELLATOR.draw();
    }
    
    public static void addChainedClawBoxVertices(final BufferBuilder buffer, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final Color color) {
        buffer.pos(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY, maxZ - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY, minZ + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY, maxZ - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY, minZ + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX - 0.8, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX - 0.8, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX + 0.8, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX + 0.8, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY + 0.2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, minY + 0.2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY + 0.2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, minY + 0.2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, maxY, maxZ - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, maxY, minZ + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, maxY, maxZ - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, maxY, minZ + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX - 0.8, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX - 0.8, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX + 0.8, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX + 0.8, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, maxY - 0.2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(minX, maxY - 0.2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, maxY - 0.2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        buffer.pos(maxX, maxY - 0.2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }
    
    public static void drawLine3D(final float x, final float y, final float z, final float minX, final float minY, final float minZ, final float thickness, final Color color) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(thickness);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GlStateManager.disableDepth();
        GL11.glEnable(34383);
        RenderUtil.BUFFER_BUILDER.begin(1, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil.BUFFER_BUILDER.pos((double)x, (double)y, (double)z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.BUFFER_BUILDER.pos((double)minX, (double)minY, (double)minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.TESSELLATOR.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.enableDepth();
        GL11.glDisable(34383);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static void drawOutlineRect(int left, int top, int right, int bottom, final Color color, final int lineWidth) {
        if (left < right) {
            final int i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final int j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float f4 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float f5 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float f6 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth((float)lineWidth);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f4, f5, f6, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left, (double)bottom, 0.0).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0).endVertex();
        bufferbuilder.pos((double)right, (double)top, 0.0).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawGradientRect(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, 10.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, 10.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, 10.0).color(f6, f7, f8, f5).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 10.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static Color staticRainbow() {
        final float hue = System.currentTimeMillis() % 11520L / 12000.0f;
        final int rgb = Color.HSBtoRGB(hue, 1.0f, 1.0f);
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        return new Color(red, green, blue);
    }
    
    private RenderUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        TESSELLATOR = Tessellator.getInstance();
        BUFFER_BUILDER = RenderUtil.TESSELLATOR.getBuffer();
        mc = Minecraft.getMinecraft();
    }
    
    public enum RenderMode
    {
        FILL, 
        OUTLINE, 
        BOTH, 
        CLAW, 
        GLOW;
    }
    
    private static class RenderBuilder
    {
        public static void glSetup() {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(1.5f);
        }
        
        public static void glRelease() {
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        
        public static void glPrepare() {
            GlStateManager.disableCull();
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
        }
        
        public static void glRestore() {
            GlStateManager.enableCull();
            GlStateManager.enableAlpha();
            GlStateManager.shadeModel(7424);
        }
    }
}
