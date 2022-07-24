//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.hudeditor.elements;

import org.teamviolet.violet.client.impl.ui.gui.elements.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.gui.*;
import java.util.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.util.misc.*;

public class GuiPanelHud extends GuiPanelCategory
{
    public GuiPanelHud(final Module.Category category, final int x, final int y, final int width, final int height) {
        super(category, x, y, width, height);
    }
    
    public void relocate(final int offsetX, final int offsetY) {
        for (final GuiElement element : this.elements) {
            if (element instanceof GuiElementPreview) {
                continue;
            }
            final GuiElement guiElement = element;
            guiElement.x += offsetX;
            final GuiElement guiElement2 = element;
            guiElement2.y += offsetY;
        }
    }
    
    public void initElements() {
        super.initElements();
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            if (module instanceof HudModule) {
                this.elements.add(new GuiElementPreview((HudModule)module));
            }
        }
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        for (final GuiElement element : this.elements) {
            if (element instanceof GuiElementPreview && ((GuiElementPreview)element).module.isEnabled()) {
                element.drawElement(mouseX, mouseY);
            }
        }
        super.drawElement(mouseX, mouseY);
    }
    
    protected void doDrag() {
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftDown && !this.dragging) {
                this.dragging = true;
                this.offsetX = MouseKeysTracker.mouseX - this.x;
                this.offsetY = MouseKeysTracker.mouseY - this.y;
            }
            else if (!MouseKeysTracker.leftDown) {
                this.dragging = false;
            }
        }
        if (this.dragging) {
            this.prevPosX = this.x;
            this.prevPosY = this.y;
            this.x = MouseKeysTracker.mouseX - this.offsetX;
            this.y = MouseKeysTracker.mouseY - this.offsetY;
            this.relocate(this.x - this.prevPosX, this.y - this.prevPosY);
        }
    }
}
