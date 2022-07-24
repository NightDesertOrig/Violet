//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import org.teamviolet.violet.client.util.misc.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import org.teamviolet.violet.client.util.render.font.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class Render2D
{
    public static final VertexHelper vertexHelperUB;
    public static final VertexHelper vertexHelperNB;
    private static final Minecraft mc;
    private static Rectangle scissor1;
    
    public static void drawRectStencil(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final Color color) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        GlStateHelper.prepareGl2d();
        GlStateHelper.enableStencil();
        GL11.glClear(17664);
        GL11.glStencilFunc(517, 1, 255);
        GL11.glStencilMask(0);
        GL11.glDisable(2929);
        vertexHelper.begin(8);
        vertexHelper.put(topLeft, color);
        vertexHelper.put(topRight, color);
        vertexHelper.put(bottomLeft, color);
        vertexHelper.put(bottomRight, color);
        vertexHelper.end();
        GL11.glStencilMask(255);
        GL11.glStencilFunc(519, 0, 255);
        GL11.glEnable(2929);
        GlStateHelper.releaseGl2d();
    }
    
    public static void drawQuadFill(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f topRight, final Vec2f bottomLeft, final Vec2f bottomRight, final Color color) {
        GlStateHelper.prepareGl2d();
        vertexHelper.begin(7);
        vertexHelper.put(topLeft, color);
        vertexHelper.put(topRight, color);
        vertexHelper.put(bottomLeft, color);
        vertexHelper.put(bottomRight, color);
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
    }
    
    public static void drawRectFill(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final Color color) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        GlStateHelper.prepareGl2d();
        vertexHelper.begin(8);
        vertexHelper.put(topLeft, color);
        vertexHelper.put(topRight, color);
        vertexHelper.put(bottomLeft, color);
        vertexHelper.put(bottomRight, color);
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
    }
    
    public static void drawMainPicker(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final Color color) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        final float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        GlStateHelper.prepareGl2d();
        vertexHelper.begin(9);
        vertexHelper.put(topLeft, Color.getHSBColor(1.0f, 0.0f, 1.0f));
        vertexHelper.put(bottomLeft, Color.getHSBColor(1.0f, 0.0f, 1.0f));
        vertexHelper.put(bottomRight, Color.getHSBColor(hsbVals[0], 1.0f, 1.0f));
        vertexHelper.put(topRight, Color.getHSBColor(hsbVals[0], 1.0f, 1.0f));
        vertexHelper.end();
        vertexHelper.begin(9);
        vertexHelper.put(topLeft, new Color(0, 0, 0, 0));
        vertexHelper.put(bottomLeft, Color.BLACK);
        vertexHelper.put(bottomRight, Color.BLACK);
        vertexHelper.put(topRight, new Color(0, 0, 0, 0));
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
    }
    
    public static void drawHuePicker(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight) {
        final float width = Math.abs(bottomRight.x - topLeft.x);
        final float height = bottomRight.y - topLeft.y;
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawRectGradient(vertexHelper, new Vec2f(topLeft.x + i * (width / 6.0f), topLeft.y), new Vec2f(topLeft.x + (i + 1) * (width / 6.0f), bottomRight.y), startColor, endColor, true);
        }
    }
    
    public static void drawHuePickerVertical(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight) {
        final float width = Math.abs(bottomRight.x - topLeft.x);
        final float height = Math.abs(bottomRight.y - topLeft.y);
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawRectGradient(vertexHelper, new Vec2f(topLeft.x, topLeft.y + i * (height / 6.0f)), new Vec2f(bottomRight.x, topLeft.y + (i + 1) * (height / 6.0f)), startColor, endColor, false);
        }
    }
    
    public static void drawAlphaPickerVertical(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final Color color) {
        final float width = Math.abs(bottomRight.x - topLeft.x);
        final float height = Math.abs(bottomRight.y - topLeft.y);
        final int checkerBoardSquareSize = (int)(width / 2.0f);
        final boolean left = true;
        for (int i = 0; i < height / checkerBoardSquareSize; ++i) {
            final float min = Math.min(topLeft.y + (i + 1) * checkerBoardSquareSize, bottomRight.y);
            if (i % 2 == 0) {
                drawRectFill(Render2D.vertexHelperUB, new Vec2f(topLeft.x, topLeft.y + i * checkerBoardSquareSize), new Vec2f(topLeft.x + checkerBoardSquareSize, min), Color.white);
                drawRectFill(Render2D.vertexHelperUB, new Vec2f(topLeft.x + checkerBoardSquareSize, topLeft.y + i * checkerBoardSquareSize), new Vec2f(bottomRight.x, min), new Color(-7303024));
            }
            else {
                drawRectFill(Render2D.vertexHelperUB, new Vec2f(topLeft.x, topLeft.y + i * checkerBoardSquareSize), new Vec2f(topLeft.x + checkerBoardSquareSize, min), new Color(-7303024));
                drawRectFill(Render2D.vertexHelperUB, new Vec2f(topLeft.x + checkerBoardSquareSize, topLeft.y + i * checkerBoardSquareSize), new Vec2f(bottomRight.x, min), Color.white);
            }
        }
        drawRectGradient(vertexHelper, topLeft, bottomRight, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0), false);
    }
    
    public static void drawRectGradient(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final Color startColor, final Color endColor, final boolean horizontal) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        GlStateHelper.prepareGl2d();
        if (horizontal) {
            vertexHelper.begin(8);
            vertexHelper.put(topLeft, startColor);
            vertexHelper.put(bottomLeft, startColor);
            vertexHelper.put(topRight, endColor);
            vertexHelper.put(bottomRight, endColor);
            vertexHelper.end();
        }
        else {
            vertexHelper.begin(8);
            vertexHelper.put(topLeft, startColor);
            vertexHelper.put(topRight, startColor);
            vertexHelper.put(bottomLeft, endColor);
            vertexHelper.put(bottomRight, endColor);
            vertexHelper.end();
        }
        GlStateHelper.releaseGl2d();
    }
    
    public static void drawRoundedRectangleFilled(final VertexHelper vertexHelper, final Vec2f top, final Vec2f bottom, final float radius, final int segments, final Color color) {
        final Vec2f vertex1 = new Vec2f(bottom.x, top.y);
        final Vec2f vertex2 = new Vec2f(top.x, bottom.y);
        drawArcFilled(vertexHelper, VectorUtil.plus(top, radius), radius, (Pair<Float, Float>)new Pair((Object)(-90.0f), (Object)0.0f), segments, color);
        drawArcFilled(vertexHelper, VectorUtil.plus(vertex1, -radius, radius), radius, (Pair<Float, Float>)new Pair((Object)0.0f, (Object)90.0f), segments, color);
        drawArcFilled(vertexHelper, VectorUtil.minus(bottom, radius), radius, (Pair<Float, Float>)new Pair((Object)90.0f, (Object)180.0f), segments, color);
        drawArcFilled(vertexHelper, VectorUtil.plus(vertex2, radius, -radius), radius, (Pair<Float, Float>)new Pair((Object)180.0f, (Object)270.0f), segments, color);
        drawRectFill(vertexHelper, VectorUtil.plus(top, radius, 0.0f), VectorUtil.plus(vertex1, -radius, radius), color);
        drawRectFill(vertexHelper, VectorUtil.plus(top, 0.0f, radius), VectorUtil.minus(bottom, 0.0f, radius), color);
        drawRectFill(vertexHelper, VectorUtil.plus(vertex2, radius, -radius), VectorUtil.minus(bottom, radius, 0.0f), color);
    }
    
    public static void drawQuadOutline(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f topRight, final Vec2f bottomLeft, final Vec2f bottomRight, final float lineWidth, final Color color) {
        GlStateHelper.prepareGl2d();
        GL11.glLineWidth(lineWidth);
        vertexHelper.begin(7);
        GL11.glPolygonMode(1032, 6913);
        vertexHelper.put(topLeft, color);
        vertexHelper.put(topRight, color);
        vertexHelper.put(bottomLeft, color);
        vertexHelper.put(bottomRight, color);
        vertexHelper.end();
        GL11.glPolygonMode(1032, 6914);
        GlStateHelper.releaseGl2d();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawRectOutline(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final float lineWidth, final Color color) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        GlStateHelper.prepareGl2d();
        GL11.glLineWidth(lineWidth);
        vertexHelper.begin(8);
        GL11.glPolygonMode(1032, 6913);
        vertexHelper.put(topLeft, color);
        vertexHelper.put(topRight, color);
        vertexHelper.put(bottomLeft, color);
        vertexHelper.put(bottomRight, color);
        vertexHelper.end();
        GL11.glPolygonMode(1032, 6914);
        GlStateHelper.releaseGl2d();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawRainbowRectOutline(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final float lineWidth, final Color color, final float factor) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        final float hue = ColorUtil.getHue(color);
        final float sat = ColorUtil.getSat(color);
        final float bri = ColorUtil.getBright(color);
        final float alpha = (float)color.getAlpha();
        final float width = Math.abs(bottomRight.x - topLeft.x);
        final float height = bottomRight.y - topLeft.y;
        final float dis = (2.0f * width + 2.0f * height) / factor;
        Vec2f curr = topLeft;
        EnumFacing dir = EnumFacing.SOUTH;
        float offset = 0.0f;
        float distance = 0.0f;
        for (int i = 0; i < factor; ++i) {
            Vec2f next = curr;
            if (dir == EnumFacing.SOUTH) {
                next = new Vec2f(curr.x, curr.y + dis);
                if (next.y > bottomLeft.y) {
                    offset = next.y - bottomLeft.y;
                    next = new Vec2f(curr.x, bottomLeft.y);
                    drawLine(vertexHelper, curr, next, lineWidth, Color.RED);
                    next = new Vec2f(curr.x + offset, bottomLeft.y);
                    distance += offset;
                    drawLine(vertexHelper, bottomLeft, next, lineWidth, Color.RED);
                    dir = EnumFacing.EAST;
                }
                else {
                    distance += dis;
                    drawLine(vertexHelper, curr, next, lineWidth, Color.RED);
                }
            }
            else if (dir == EnumFacing.EAST) {
                next = new Vec2f(curr.x + dis, curr.y);
                if (next.x > bottomRight.x) {
                    offset = next.x - bottomRight.x;
                    next = new Vec2f(bottomRight.x, curr.y);
                    drawLine(vertexHelper, curr, next, lineWidth, Color.RED);
                    next = new Vec2f(bottomRight.x, bottomRight.y - offset);
                    drawLine(vertexHelper, bottomRight, next, lineWidth, Color.RED);
                    dir = EnumFacing.NORTH;
                }
                else {
                    drawLine(vertexHelper, curr, next, lineWidth, Color.RED);
                }
            }
            curr = next;
        }
    }
    
    public static void drawRainbowRectOutline(final VertexHelper vertexHelper, final Vec2f topLeft, final Vec2f bottomRight, final float lineWidth, final Color color) {
        final Vec2f topRight = new Vec2f(bottomRight.x, topLeft.y);
        final Vec2f bottomLeft = new Vec2f(topLeft.x, bottomRight.y);
        final float hue = ColorUtil.getHue(color);
        final float sat = ColorUtil.getSat(color);
        final float bri = ColorUtil.getBright(color);
        final float alpha = (float)color.getAlpha();
        final float width = Math.abs(bottomRight.x - topLeft.x);
        final float height = bottomRight.y - topLeft.y;
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawLineGradient(vertexHelper, new Vec2f(topLeft.x + i * (width / 6.0f), topLeft.y), new Vec2f(topLeft.x + (i + 1) * (width / 6.0f), topRight.y), lineWidth, startColor, endColor);
        }
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawLineGradient(vertexHelper, new Vec2f(topLeft.x, topLeft.y + i * (height / 6.0f)), new Vec2f(bottomLeft.x, topLeft.y + (i + 1) * (height / 6.0f)), lineWidth, startColor, endColor);
        }
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawLineGradient(vertexHelper, new Vec2f(topRight.x, topRight.y + i * (height / 6.0f)), new Vec2f(bottomRight.x, topRight.y + (i + 1) * (height / 6.0f)), lineWidth, startColor, endColor);
        }
        for (int i = 0; i <= 5; ++i) {
            final Color startColor = Color.getHSBColor(i * 0.16666667f, 1.0f, 1.0f);
            final Color endColor = Color.getHSBColor((i + 1) * 0.16666667f, 1.0f, 1.0f);
            drawLineGradient(vertexHelper, new Vec2f(topLeft.x + i * (width / 6.0f), bottomLeft.y), new Vec2f(topLeft.x + (i + 1) * (width / 6.0f), bottomRight.y), lineWidth, startColor, endColor);
        }
    }
    
    public static void drawRoundedRectangleOutlined(final VertexHelper vertexHelper, final Vec2f top, final Vec2f bottom, final float radius, final int segments, final Color color, final float lineWidth) {
        final Vec2f vertex1 = new Vec2f(bottom.x, top.y);
        final Vec2f vertex2 = new Vec2f(top.x, bottom.y);
        drawArcOutline(vertexHelper, new Vec2f(top.x + radius, top.y + radius), radius, (Pair<Float, Float>)new Pair((Object)(-90.0f), (Object)0.0f), segments, lineWidth, color);
        drawArcOutline(vertexHelper, new Vec2f(vertex1.x - radius, vertex1.y + radius), radius, (Pair<Float, Float>)new Pair((Object)0.0f, (Object)90.0f), segments, lineWidth, color);
        drawArcOutline(vertexHelper, new Vec2f(bottom.x - radius, bottom.y - radius), radius, (Pair<Float, Float>)new Pair((Object)90.0f, (Object)180.0f), segments, lineWidth, color);
        drawArcOutline(vertexHelper, new Vec2f(vertex2.x + radius, vertex2.y - radius), radius, (Pair<Float, Float>)new Pair((Object)180.0f, (Object)270.0f), segments, lineWidth, color);
        drawLine(vertexHelper, new Vec2f(top.x + radius, top.y), new Vec2f(vertex1.x - radius, vertex1.y), lineWidth, color);
        drawLine(vertexHelper, new Vec2f(top.x, top.y + radius), new Vec2f(vertex2.x, vertex2.y - radius), lineWidth, color);
        drawLine(vertexHelper, new Vec2f(vertex1.x, vertex1.y + radius), new Vec2f(bottom.x, bottom.y - radius), lineWidth, color);
        drawLine(vertexHelper, new Vec2f(vertex2.x + radius, vertex2.y), new Vec2f(bottom.x - radius, bottom.y), lineWidth, color);
    }
    
    public static void drawRoundedRectangleOutlinedTop(final VertexHelper vertexHelper, final Vec2f top, final Vec2f bottom, final float radius, final int segments, final Color color, final float lineWidth) {
        final Vec2f vertex1 = new Vec2f(bottom.x, top.y);
        final Vec2f vertex2 = new Vec2f(top.x, bottom.y);
        drawArcOutline(vertexHelper, new Vec2f(top.x + radius, top.y + radius), radius, (Pair<Float, Float>)new Pair((Object)(-90.0f), (Object)0.0f), segments, lineWidth, color);
        drawArcOutline(vertexHelper, new Vec2f(vertex1.x - radius, vertex1.y + radius), radius, (Pair<Float, Float>)new Pair((Object)0.0f, (Object)90.0f), segments, lineWidth, color);
        drawLine(vertexHelper, new Vec2f(top.x + radius, top.y), new Vec2f(vertex1.x - radius, vertex1.y), lineWidth, color);
        drawLine(vertexHelper, new Vec2f(top.x, top.y + radius), vertex2, lineWidth, color);
        drawLine(vertexHelper, new Vec2f(vertex1.x, vertex1.y + radius), bottom, lineWidth, color);
        drawLine(vertexHelper, vertex2, bottom, lineWidth, color);
    }
    
    private static void drawArcFilled(final VertexHelper vertexHelper, final Vec2f center, final float radius, final Pair<Float, Float> angleRange, final int segments, final Color color) {
        drawTriangleFan(vertexHelper, center, getArcVertices(center, radius, angleRange, segments), color);
    }
    
    private static void drawArcOutline(final VertexHelper vertexHelper, final Vec2f center, final float radius, final Pair<Float, Float> angleRange, final int segments, final float lineWidth, final Color color) {
        drawLineStrip(vertexHelper, getArcVertices(center, radius, angleRange, segments), lineWidth, color);
    }
    
    private static void drawTriangleFan(final VertexHelper vertexHelper, final Vec2f centre, final ArrayList<Vec2f> vertices, final Color color) {
        GlStateHelper.prepareGl2d();
        vertexHelper.begin(6);
        vertexHelper.put(centre, color);
        for (final Vec2f vertex : vertices) {
            vertexHelper.put(vertex, color);
        }
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
    }
    
    private static void drawLineStrip(final VertexHelper vertexHelper, final ArrayList<Vec2f> vertices, final float lineWidth, final Color color) {
        GlStateHelper.prepareGl2d();
        GL11.glLineWidth(lineWidth);
        vertexHelper.begin(3);
        for (final Vec2f vertex : vertices) {
            vertexHelper.put(vertex, color);
        }
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawLine(final VertexHelper vertexHelper, final Vec2f begin, final Vec2f end, final float lineWidth, final Color color) {
        GlStateHelper.prepareGl2d();
        GL11.glLineWidth(lineWidth);
        vertexHelper.begin(1);
        vertexHelper.put(begin, color);
        vertexHelper.put(end, color);
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawLineGradient(final VertexHelper vertexHelper, final Vec2f begin, final Vec2f end, final float lineWidth, final Color color, final Color color1) {
        GlStateHelper.prepareGl2d();
        GL11.glLineWidth(lineWidth);
        vertexHelper.begin(1);
        vertexHelper.put(begin, color);
        vertexHelper.put(end, color1);
        vertexHelper.end();
        GlStateHelper.releaseGl2d();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawImageSimple(final ResourceLocation resourceLocation, final Vec2f top, final Vec2f bottom) {
        Render2D.mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int)top.x, (int)top.y, 0.0f, 0.0f, (int)(bottom.x - top.x), (int)(bottom.y - top.y), (float)(int)(bottom.x - top.x), (float)(int)(bottom.y - top.y));
    }
    
    public static void drawImageSimpleGradient(final ResourceLocation resourceLocation, final Vec2f top, final Vec2f bottom, final Color start, final Color end) {
        Render2D.mc.getTextureManager().bindTexture(resourceLocation);
        drawCompleteImage(top.x, top.y, bottom.x - top.x, bottom.y - top.y, start, end);
    }
    
    public static void drawImage(final ResourceLocation resourceLocation, final Vec2f top, final Vec2f bottom) {
        Render2D.mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int)top.x, (int)top.y, 0.0f, 0.0f, (int)(bottom.x - top.x), (int)(bottom.y - top.y), (float)(int)(bottom.x - top.x), (float)(int)(bottom.y - top.y));
    }
    
    public static void drawItem(final ItemStack item, final int x, final int y, final int amount, final boolean drawAmount) {
        final RenderItem itemRenderer = Render2D.mc.getRenderItem();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        itemRenderer.zLevel = 200.0f;
        itemRenderer.renderItemAndEffectIntoGUI(item, x, y);
        itemRenderer.renderItemOverlayIntoGUI(Render2D.mc.fontRenderer, item, x, y, "");
        itemRenderer.zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        if (drawAmount && amount > 1) {
            FontUtil.drawText(amount + "", x + 17 - FontUtil.getStringWidth(amount + ""), y + FontUtil.getStringHeight("]"), Color.white);
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    private static ArrayList<Vec2f> getArcVertices(final Vec2f center, final float radius, final Pair<Float, Float> angleRange, final int segments) {
        final double range = Math.max((float)angleRange.getA(), (float)angleRange.getB()) - Math.min((float)angleRange.getA(), (float)angleRange.getB());
        final double seg = calculateSegments(segments, radius, range);
        final double segAngle = range / seg;
        final ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
        for (int i = 0; i < seg + 1.0; ++i) {
            final double angle = Math.toRadians(i * segAngle + (float)angleRange.getA());
            vec2fs.add(new Vec2f((float)(Math.sin(angle) * radius + center.x), (float)(-Math.cos(angle) * radius + center.y)));
        }
        return vec2fs;
    }
    
    private static double calculateSegments(final int segmentsIn, final double radius, final double range) {
        if (segmentsIn != 0) {
            return segmentsIn;
        }
        final double segments = radius * 0.5 * 3.141592653589793 * (range / 360.0);
        return Math.max(segments, 16.0);
    }
    
    public static void drawCompleteImage(final double posX, final double posY, final double width, final double height, final Color start, final Color end) {
        GL11.glPushAttrib(1);
        GL11.glPushMatrix();
        GL11.glTranslated(posX, posY, 0.0);
        GlStateManager.enableBlend();
        GlStateManager.shadeModel(7425);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glBegin(7);
        GL11.glColor4f(start.getRed() / 255.0f, start.getGreen() / 255.0f, start.getBlue() / 255.0f, start.getAlpha() / 255.0f);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(start.getRed() / 255.0f, start.getGreen() / 255.0f, start.getBlue() / 255.0f, start.getAlpha() / 255.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3d(0.0, height, 0.0);
        GL11.glColor4f(end.getRed() / 255.0f, end.getGreen() / 255.0f, end.getBlue() / 255.0f, end.getAlpha() / 255.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3d(width, height, 0.0);
        GL11.glColor4f(end.getRed() / 255.0f, end.getGreen() / 255.0f, end.getBlue() / 255.0f, end.getAlpha() / 255.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3d(width, 0.0, 0.0);
        GL11.glEnd();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
    
    public static void scissor(final double x, final double y, final double width, final double height) {
        GL11.glScissor((int)x, (int)y, (int)width, (int)height);
    }
    
    public static void setLast(final double x, final double y, final double width, final double height) {
        Render2D.scissor1 = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
    
    public static void betterScissor(final float x, final float y, final float x1, final float y1, final ScaledResolution sr) {
        GL11.glScissor((int)(x * sr.getScaleFactor()), (int)(Minecraft.getMinecraft().displayHeight - y1 * sr.getScaleFactor()), (int)((x1 - x) * sr.getScaleFactor()), (int)((y1 - y) * sr.getScaleFactor()));
    }
    
    public static void scissorLast() {
        GL11.glScissor(Render2D.scissor1.x, Render2D.scissor1.y, Render2D.scissor1.width, Render2D.scissor1.height);
    }
    
    public static void enableScissor() {
        GL11.glEnable(3089);
    }
    
    public static void disableScissor() {
        GL11.glDisable(3089);
    }
    
    static {
        vertexHelperUB = new VertexHelper(true);
        vertexHelperNB = new VertexHelper(false);
        mc = Minecraft.getMinecraft();
    }
}
