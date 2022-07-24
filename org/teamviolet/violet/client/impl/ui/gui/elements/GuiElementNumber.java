//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.api.value.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.misc.*;
import java.text.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.font.*;

public class GuiElementNumber<T extends Number> extends GuiElement
{
    public Value<T> numberSetting;
    private Class<T> type;
    private boolean odd;
    private int sliderPos;
    private int sliderX;
    private int sliderY;
    private int sliderWidth;
    private int sliderHeight;
    
    public GuiElementNumber(final Value<T> numberSetting, final Class<T> type, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.numberSetting = numberSetting;
        this.type = type;
        this.odd = odd;
        this.sliderX = x;
        this.sliderY = y;
        this.sliderWidth = width;
        this.sliderHeight = height;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)ClickGui.getInstance().background.getValue()), ColorUtil.getSat((Color)ClickGui.getInstance().background.getValue()), MathUtil.clamp(ColorUtil.getBright((Color)ClickGui.getInstance().background.getValue()) - 0.05f, 0.0f, 1.0f)));
        final Color pickerBackground = (Color)ClickGui.getInstance().pickerBackground.getValue();
        final Color fill = (Color)ClickGui.getInstance().accentColor.getValue();
        this.sliderX = this.x + 2;
        this.sliderY = this.y + 2;
        this.sliderWidth = this.width - 4;
        this.sliderHeight = this.height - 4;
        final double pix = (this.numberSetting.getMax() - this.numberSetting.getMin()) / this.sliderWidth;
        final float mp = (float)(mouseX - this.sliderX);
        this.sliderPos = (int)MathUtil.clamp((float)((((Number)this.numberSetting.getValue()).floatValue() - this.numberSetting.getMin()) / pix), 0.0f, (float)this.sliderWidth);
        if (MouseKeysTracker.mouseOver(this.sliderX, this.sliderY, this.sliderX + this.sliderWidth, this.sliderY + this.sliderHeight) && MouseKeysTracker.leftDown) {
            Number newVal = null;
            if (this.type.isAssignableFrom(Float.class)) {
                newVal = new Float(pix * mp + this.numberSetting.getMin());
            }
            if (this.type.isAssignableFrom(Double.class)) {
                newVal = new Double(pix * mp + this.numberSetting.getMin());
            }
            if (this.type.isAssignableFrom(Integer.class)) {
                newVal = new Integer((int)(pix * mp + this.numberSetting.getMin()));
            }
            String format = "##";
            if (this.numberSetting.getDecimals() > 0) {
                format += ".";
                for (int i = 0; i < this.numberSetting.getDecimals(); ++i) {
                    format += "0";
                }
            }
            if (mp >= this.sliderWidth) {
                newVal = this.numberSetting.getMax();
            }
            if (mp == 0.0f) {
                newVal = this.numberSetting.getMin();
            }
            final DecimalFormat f = new DecimalFormat(format);
            final float formatted = MathUtil.clamp(Float.parseFloat(f.format(newVal)), this.numberSetting.getMin(), this.numberSetting.getMax());
            if (this.type.isAssignableFrom(Float.class)) {
                newVal = new Float(formatted);
            }
            if (this.type.isAssignableFrom(Double.class)) {
                newVal = new Double(formatted);
            }
            if (this.type.isAssignableFrom(Integer.class)) {
                newVal = new Integer((int)formatted);
            }
            this.numberSetting.setValue((Object)this.type.cast(newVal));
        }
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.rightClicked) {
                this.numberSetting.setOpen(!this.numberSetting.isOpen());
                playGuiClick();
            }
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        this.drawRect(new Vec2f((float)this.sliderX, (float)this.sliderY), new Vec2f((float)(this.sliderX + this.sliderWidth), (float)(this.sliderY + this.sliderHeight)), pickerBackground, (ClickGui.RectangleMode)ClickGui.getInstance().sliderMode.getValue());
        this.drawRect(new Vec2f((float)this.sliderX, (float)this.sliderY), new Vec2f((float)(this.sliderX + this.sliderPos), (float)(this.sliderY + this.sliderHeight)), fill, (ClickGui.RectangleMode)ClickGui.getInstance().sliderMode.getValue());
        FontUtil.drawText(this.numberSetting.getName(), (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.numberSetting.getName()) / 2.0f, Color.WHITE);
        FontUtil.drawText(String.valueOf(this.numberSetting.getValue()), this.x + this.width - FontUtil.getStringWidth(String.valueOf(this.numberSetting.getValue())) - 3.0f, this.y + this.height / 2.0f - FontUtil.getStringHeight(String.valueOf(this.numberSetting.getValue())) / 2.0f, Color.WHITE);
    }
}
