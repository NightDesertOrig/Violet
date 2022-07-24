//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.menu.button;

import net.minecraft.util.*;
import org.teamviolet.violet.client.util.render.font.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.*;
import java.awt.*;

public abstract class ImageButton extends GuiButton
{
    private ResourceLocation image;
    private FontRenderer customFont;
    private ButtonAnimation animation;
    protected GuiScreen screen;
    private int percent;
    
    public ImageButton(final int buttonId, final int x, final int y, final int width, final int height, final String buttonText, final ResourceLocation image, final FontRenderer font, final GuiScreen screen) {
        super(buttonId, x, y, width, height, buttonText);
        this.percent = 0;
        this.image = image;
        this.customFont = font;
        this.screen = screen;
    }
    
    public abstract void onClicked();
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
        this.animation = new ButtonAnimation(1.2, 20).withBounds(new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height))).withPercentage(this.percent);
        if (this.visible) {
            if (this.isHovered(mouseX, mouseY)) {
                this.percent = this.animation.expand();
            }
            else {
                this.percent = this.animation.shrink();
            }
            this.enabled = true;
            this.hovered = (mouseX >= this.x - this.width / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
            Render2D.drawRoundedRectangleFilled(Render2D.vertexHelperUB, this.animation.top, this.animation.bottom, 4.0f, 14, new Color(1728053247, true));
            Render2D.drawRoundedRectangleOutlined(Render2D.vertexHelperUB, this.animation.top, this.animation.bottom, 4.0f, 14, new Color(-1, true), 2.0f * this.percent / 100.0f);
            this.customFont.drawCenteredString(this.displayString, (float)(this.x + this.width / 2), (float)(this.y + this.height / 2 - this.customFont.getStringHeight("A") / 2), Color.WHITE.getRGB());
        }
    }
    
    public boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.visible && mouseX >= this.x - this.width / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
            this.onClicked();
        }
        return this.enabled && this.visible && mouseX >= this.x - this.width / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
    
    private boolean isHovered(final int mouseX, final int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
    
    public void updatePosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
