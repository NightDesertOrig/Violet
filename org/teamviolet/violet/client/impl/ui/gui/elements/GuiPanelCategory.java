//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.value.*;
import java.util.*;
import java.awt.*;
import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.impl.ui.gui.*;

public class GuiPanelCategory extends GuiPanel
{
    private Module.Category category;
    private int drawingPos;
    
    public GuiPanelCategory(final Module.Category category, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.drawingPos = 0;
        this.category = category;
    }
    
    public void initElements() {
        int drawingPos = this.y;
        this.elements.add(new GuiElementHeader(this.category, this.x, drawingPos, this.width, this.height));
        drawingPos += this.height;
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            if (module.getCategory() == this.category) {
                this.elements.add(new GuiElementModule(module, true, this.x + 2, drawingPos, this.width, this.height - 2));
                drawingPos += this.height - 2;
                for (final Value<?> value : module.getValueList()) {
                    this.addSetting(value, true);
                }
            }
        }
    }
    
    protected void addSetting(final Value<?> value, final boolean odd) {
        if (value.getValue() instanceof Boolean) {
            this.elements.add(new GuiElementBoolean((Value)value, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += this.height - 2;
        }
        if (value.getValue() instanceof Float) {
            this.elements.add(new GuiElementNumber((Value)value, (Class)Float.class, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += this.height - 2;
        }
        if (value.getValue() instanceof Double) {
            this.elements.add(new GuiElementNumber((Value)value, (Class)Double.class, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += this.height - 2;
        }
        if (value.getValue() instanceof Integer) {
            this.elements.add(new GuiElementNumber((Value)value, (Class)Integer.class, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += this.height - 2;
        }
        if (value.getValue() instanceof Enum) {
            this.elements.add(new GuiElementEnum((Value)value, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += this.height - 2;
        }
        if (value.getValue() instanceof Color) {
            this.elements.add(new GuiElementColor((Value)value, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += (value.isOpen() ? (this.height + this.width - 6) : (this.height - 2));
        }
        if (value.getValue() instanceof Bind) {
            this.elements.add(new GuiElementBind((Value)value, odd, this.x + 2, this.drawingPos, this.width, this.height - 2));
            this.drawingPos += (value.isOpen() ? (this.height + this.width - 6) : (this.height - 2));
        }
        for (final Value<?> subValue : value.getValueList()) {
            this.addSetting(subValue, odd);
        }
    }
    
    public void relocate(final int offsetX, final int offsetY) {
        for (final GuiElement guiElement : this.elements) {
            final GuiElement element = guiElement;
            guiElement.x += offsetX;
            final GuiElement guiElement2 = element;
            guiElement2.y += offsetY;
        }
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        this.doDrag();
        int drawingPos = this.y;
        this.height = (int)ClickGui.getInstance().height.getValue();
        for (final GuiElement element : this.elements) {
            if (element instanceof GuiElementHeader) {
                element.setPosition(this.x, drawingPos);
                element.height = this.height;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height;
            }
            else if (element instanceof GuiElementModule) {
                if (!this.category.isOpen()) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height - 2;
            }
            else if (element instanceof GuiElementBoolean) {
                if (!this.shouldDrawSetting((Value<?>)((GuiElementBoolean)element).booleanSetting)) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height - 2;
            }
            else if (element instanceof GuiElementNumber) {
                if (!this.shouldDrawSetting((Value<?>)((GuiElementNumber)element).numberSetting)) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height - 2;
            }
            else if (element instanceof GuiElementEnum) {
                if (!this.shouldDrawSetting((Value<?>)((GuiElementEnum)element).enumSetting)) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height - 2;
            }
            else if (element instanceof GuiElementColor) {
                if (!this.shouldDrawSetting((Value<?>)((GuiElementColor)element).colorSetting)) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += (((GuiElementColor)element).colorSetting.isOpen() ? (this.height + this.width + 2) : (this.height - 2));
            }
            else {
                if (!(element instanceof GuiElementBind) || !this.shouldDrawSetting((Value<?>)((GuiElementBind)element).bindSetting)) {
                    continue;
                }
                element.setPosition(this.x, drawingPos);
                element.height = this.height - 2;
                element.drawElement(mouseX, mouseY);
                drawingPos += this.height - 2;
            }
        }
        Render2D.drawRectOutline(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)drawingPos), 1.5f, (Color)ClickGui.getInstance().accentColor.getValue());
    }
    
    protected void doDrag() {
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftDown && !this.dragging) {
                this.dragging = ClickGuiScreen.getInstance().handleDrag((GuiPanel)this);
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
    
    private boolean shouldDrawSetting(final Value<?> value) {
        boolean isParentDrawn = true;
        boolean isParentOpen = true;
        final boolean isCategoryOpen = value.getModule().getCategory().isOpen();
        final boolean isModuleOpen = value.getModule().isOpen();
        if (value.hasParent()) {
            isParentOpen = value.getParent().isOpen();
            isParentDrawn = this.shouldDrawSetting((Value<?>)value.getParent());
        }
        return isParentDrawn && isModuleOpen && isParentOpen && isCategoryOpen;
    }
}
