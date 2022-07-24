//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.gui;

import net.minecraft.util.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public abstract class GuiElement
{
    public int x;
    public int y;
    public int width;
    public int height;
    protected Timer animationTimer;
    protected ResourceLocation tick;
    protected ResourceLocation paste;
    protected ResourceLocation copy;
    
    public GuiElement(final int x, final int y, final int width, final int height) {
        this.tick = new ResourceLocation("textures/violet/images/tick.png");
        this.paste = new ResourceLocation("textures/violet/images/paste.png");
        this.copy = new ResourceLocation("textures/violet/images/copy.png");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void drawElement(final int p0, final int p1);
    
    public void drawRect(final Vec2f start, final Vec2f end, final Color color, final ClickGui.RectangleMode mode) {
        if (mode == ClickGui.RectangleMode.Normal) {
            Render2D.drawRectFill(Render2D.vertexHelperUB, start, end, color);
        }
        else {
            Render2D.drawRoundedRectangleFilled(Render2D.vertexHelperUB, start, end, 4.0f, 20, color);
        }
    }
    
    public void drawIcon(final ResourceLocation res) {
        GlStateManager.pushMatrix();
        final int iconSize = 9;
        GlStateManager.translate((float)(this.x + this.width - 3 - iconSize), (float)(this.y + (this.height - iconSize) / 2), 0.0f);
        GlStateManager.translate((float)(iconSize / 2), (float)(iconSize / 2), 0.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 255.0f);
        Render2D.drawImage(res, new Vec2f((float)(-(iconSize / 2)), (float)(-(iconSize / 2))), new Vec2f((float)(iconSize / 2), (float)(iconSize / 2)));
        GlStateManager.popMatrix();
    }
    
    public void drawIcon(final ResourceLocation res, final int x, final int y, final int width, final int height) {
        Render2D.drawImage(res, new Vec2f((float)x, (float)y), new Vec2f((float)(x + width), (float)(x + height)));
    }
    
    public static void playGuiClick() {
        Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
