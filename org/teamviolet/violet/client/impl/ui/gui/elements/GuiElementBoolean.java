//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.api.value.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.font.*;

public class GuiElementBoolean extends GuiElement
{
    public Value<Boolean> booleanSetting;
    private boolean odd;
    
    public GuiElementBoolean(final Value<Boolean> booleanSetting, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.booleanSetting = booleanSetting;
        this.odd = odd;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)ClickGui.getInstance().background.getValue()), ColorUtil.getSat((Color)ClickGui.getInstance().background.getValue()), MathUtil.clamp(ColorUtil.getBright((Color)ClickGui.getInstance().background.getValue()) - 0.05f, 0.0f, 1.0f)));
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftClicked) {
                this.booleanSetting.setValue((Object)!(boolean)this.booleanSetting.getValue());
                playGuiClick();
            }
            if (MouseKeysTracker.rightClicked) {
                this.booleanSetting.setOpen(!this.booleanSetting.isOpen());
                playGuiClick();
            }
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        if (!this.booleanSetting.getValueList().isEmpty()) {
            FontUtil.drawText(".", (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(".") + 1.0f, (Color)ClickGui.getInstance().accentColor.getValue());
            FontUtil.drawText(this.booleanSetting.getName(), this.x + 9 + FontUtil.getStringWidth("."), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.booleanSetting.getName()) / 2.0f, Color.WHITE);
        }
        else {
            FontUtil.drawText(this.booleanSetting.getName(), (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.booleanSetting.getName()) / 2.0f, Color.WHITE);
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)(this.x + this.width - 4 - 9), (float)(this.y + (this.height - 9) / 2)), new Vec2f((float)(this.x + this.width - 4), (float)(this.y + (this.height - 9) / 2 + 9)), (Color)ClickGui.getInstance().pickerBackground.getValue());
        if (this.booleanSetting.getValue()) {
            this.drawIcon(this.tick);
        }
    }
}
