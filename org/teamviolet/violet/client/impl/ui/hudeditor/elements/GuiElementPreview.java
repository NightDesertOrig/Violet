//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.hudeditor.elements;

import org.teamviolet.violet.client.api.gui.*;
import java.awt.*;
import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.impl.ui.hudeditor.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;

public class GuiElementPreview extends GuiElement
{
    private static Color background;
    public HudModule module;
    private boolean dragging;
    private int boundX;
    private int boundY;
    private int boundWidth;
    private int boundHeight;
    private int offsetX;
    private int offsetY;
    private int prevPosX;
    private int prevPosY;
    
    public GuiElementPreview(final HudModule module) {
        super(module.x, module.y, module.width, module.height);
        this.boundX = this.x - 2;
        this.boundY = this.y - 2;
        this.boundWidth = this.x + this.width + 2;
        this.boundHeight = this.y + this.height + 2;
        this.module = module;
    }
    
    private void updateDimensions() {
        this.module.updateBounds();
        this.width = this.module.width;
        this.height = this.module.height;
        this.boundWidth = this.width + 4;
        this.boundHeight = this.height + 4;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        this.updateDimensions();
        if (MouseKeysTracker.mouseOver(this.boundX, this.boundY, this.boundX + this.boundWidth, this.boundY + this.boundHeight)) {
            if (MouseKeysTracker.leftDown && !this.dragging) {
                this.dragging = ClickHudScreen.getInstance().handleDrag(this.module);
                this.offsetX = mouseX - this.x;
                this.offsetY = mouseY - this.y;
            }
            else if (!MouseKeysTracker.leftDown) {
                this.dragging = false;
                if (ClickHudScreen.getInstance().dragging == this.module) {
                    ClickHudScreen.getInstance().dragging = null;
                }
            }
        }
        if (this.dragging) {
            this.prevPosX = this.x;
            this.prevPosY = this.y;
            final int tx = mouseX - this.offsetX;
            final int ty = mouseY - this.offsetY;
            this.x += tx - this.prevPosX;
            this.y += ty - this.prevPosY;
        }
        this.boundX = this.x - 2;
        this.boundY = this.y - 2;
        if (this.boundX <= 0) {
            this.x = 2;
            this.boundX = this.x - 2;
        }
        if (this.boundY <= 0) {
            this.y = 2;
            this.boundY = this.y - 2;
        }
        if (this.boundX + this.boundWidth >= this.sr().getScaledWidth()) {
            this.x = this.sr().getScaledWidth() - this.boundWidth + 2;
            this.boundX = this.x - 2;
        }
        if (this.boundY + this.boundHeight >= this.sr().getScaledHeight()) {
            this.y = this.sr().getScaledHeight() - this.boundHeight + 2;
            this.boundY = this.y - 2;
        }
        this.module.x = this.x;
        this.module.y = this.y;
        this.drawBackground();
        this.module.draw();
    }
    
    private void drawBackground() {
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.boundX, (float)this.boundY), new Vec2f((float)(this.boundX + this.boundWidth), (float)(this.boundY + this.boundHeight)), GuiElementPreview.background);
    }
    
    public void bounding(final int minX, final int maxX, final int minY, final int maxY) {
    }
    
    protected ScaledResolution sr() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }
    
    static {
        GuiElementPreview.background = new Color(1782595648, true);
    }
}
