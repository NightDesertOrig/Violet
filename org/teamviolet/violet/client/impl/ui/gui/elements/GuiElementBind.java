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
import org.lwjgl.input.*;

public class GuiElementBind extends GuiElement
{
    public Value<Bind> bindSetting;
    private boolean odd;
    
    public GuiElementBind(final Value<Bind> bindSetting, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.bindSetting = bindSetting;
        this.odd = odd;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)ClickGui.getInstance().background.getValue()), ColorUtil.getSat((Color)ClickGui.getInstance().background.getValue()), MathUtil.clamp(ColorUtil.getBright((Color)ClickGui.getInstance().background.getValue()) - 0.05f, 0.0f, 1.0f)));
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftClicked) {
                this.bindSetting.setTyping(!this.bindSetting.isTyping());
                playGuiClick();
            }
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        FontUtil.drawText(this.bindSetting.getName(), (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.bindSetting.getName()) / 2.0f, Color.WHITE);
        final Color typeColor = (Color)(this.bindSetting.isTyping() ? ClickGui.getInstance().accentColor.getValue() : Color.WHITE);
        this.setBind(MouseKeysTracker.keyDown);
        FontUtil.drawText(Keyboard.getKeyName(((Bind)this.bindSetting.getValue()).getKey()), this.x + this.width - FontUtil.getStringWidth(Keyboard.getKeyName(((Bind)this.bindSetting.getValue()).getKey())) - 3.0f, this.y + this.height / 2.0f - FontUtil.getStringHeight(Keyboard.getKeyName(((Bind)this.bindSetting.getValue()).getKey())) / 2.0f, typeColor);
    }
    
    private void setBind(final int key) {
        if (this.bindSetting.isTyping() && key != 0) {
            ((Bind)this.bindSetting.getValue()).setKey((key == 28 || key == 1) ? 0 : key);
            this.bindSetting.setTyping(false);
        }
    }
}
