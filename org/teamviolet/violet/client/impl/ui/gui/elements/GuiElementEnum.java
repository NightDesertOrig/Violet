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

public class GuiElementEnum extends GuiElement
{
    public Value<Enum<?>> enumSetting;
    private boolean odd;
    
    public GuiElementEnum(final Value<Enum<?>> enumSetting, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.enumSetting = enumSetting;
        this.odd = odd;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)ClickGui.getInstance().background.getValue()), ColorUtil.getSat((Color)ClickGui.getInstance().background.getValue()), MathUtil.clamp(ColorUtil.getBright((Color)ClickGui.getInstance().background.getValue()) - 0.05f, 0.0f, 1.0f)));
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftClicked) {
                this.increase();
                playGuiClick();
            }
            if (MouseKeysTracker.rightClicked) {
                this.decrease();
                playGuiClick();
            }
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        FontUtil.drawText(this.enumSetting.getName(), (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.enumSetting.getName()) / 2.0f, Color.WHITE);
        FontUtil.drawText(String.valueOf(this.enumSetting.getValue()), this.x + this.width - FontUtil.getStringWidth(String.valueOf(this.enumSetting.getValue())) - 3.0f, this.y + this.height / 2.0f - FontUtil.getStringHeight(this.enumSetting.getName()) / 2.0f, Color.WHITE);
    }
    
    private <E extends Enum<E>> void increase() {
        final E[] values = ((Enum)this.enumSetting.getValue()).getDeclaringClass().getEnumConstants();
        final int ordinal = ((Enum)this.enumSetting.getValue()).ordinal();
        this.enumSetting.setValue((Object)values[(ordinal + 1 >= values.length) ? 0 : (ordinal + 1)]);
    }
    
    private <E extends Enum<E>> void decrease() {
        final E[] values = ((Enum)this.enumSetting.getValue()).getDeclaringClass().getEnumConstants();
        final int ordinal = ((Enum)this.enumSetting.getValue()).ordinal();
        this.enumSetting.setValue((Object)values[(ordinal - 1 < 0) ? (values.length - 1) : (ordinal - 1)]);
    }
}
