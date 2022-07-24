//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.font.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class GuiElementColor extends GuiElement
{
    public Value<Color> colorSetting;
    private boolean odd;
    private int selX;
    private int selY;
    private int selectionSectionSize;
    private int bsX;
    private int bsY;
    private int bsWidth;
    private int bsHeight;
    private int bspX;
    private int bspY;
    private int bspRadius;
    private int hX;
    private int hY;
    private int hWidth;
    private int hHeight;
    private int hpY;
    private int hpHeight;
    private int aX;
    private int aY;
    private int aWidth;
    private int aHeight;
    private int apY;
    private int apHeight;
    private int tX;
    private int tY;
    private int tWidth;
    private int tHeight;
    private int cX;
    private int cY;
    private int cWidth;
    private int cHeight;
    private int pX;
    private int pY;
    private int pWidth;
    private int pHeight;
    private int rX;
    private int rY;
    private int rWidth;
    private int rHeight;
    private Timer copyTimer;
    private Timer pasteTimer;
    private boolean copied;
    private boolean rainbow;
    private int rainbowSpeed;
    
    public GuiElementColor(final Value<Color> colorSetting, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.copyTimer = null;
        this.pasteTimer = null;
        this.copied = false;
        this.rainbow = false;
        this.rainbowSpeed = 1;
        this.colorSetting = colorSetting;
        this.odd = odd;
        this.selectionSectionSize = width;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)ClickGui.getInstance().background.getValue()), ColorUtil.getSat((Color)ClickGui.getInstance().background.getValue()), MathUtil.clamp(ColorUtil.getBright((Color)ClickGui.getInstance().background.getValue()) - 0.05f, 0.0f, 1.0f)));
        this.selX = this.x;
        this.selY = this.y + this.height;
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftClicked) {
                playGuiClick();
            }
            if (MouseKeysTracker.rightClicked) {
                this.colorSetting.setOpen(!this.colorSetting.isOpen());
                playGuiClick();
            }
        }
        if ((MouseKeysTracker.mouseOver(this.selX, this.selY, this.selX + this.selectionSectionSize, this.selY + this.selectionSectionSize) && this.colorSetting.isOpen()) || MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)(this.x + this.width - 4 - 9), (float)(this.y + (this.height - 9) / 2)), new Vec2f((float)(this.x + this.width - 4), (float)(this.y + (this.height - 9) / 2 + 9)), (Color)this.colorSetting.getValue());
        FontUtil.drawText(this.colorSetting.getName(), (float)(this.x + 6), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.colorSetting.getName()) / 2.0f, Color.WHITE);
        if (!this.colorSetting.isOpen()) {
            return;
        }
        this.bsX = this.selX + 4;
        this.bsY = this.selY + 4;
        this.bsWidth = this.selectionSectionSize - 33;
        this.bsHeight = this.selectionSectionSize - 40;
        this.bspRadius = 3;
        this.hX = this.selX + this.bsWidth + 8;
        this.hY = this.selY + 4;
        this.hWidth = 8;
        this.hHeight = this.bsHeight;
        this.hpHeight = 2;
        this.aX = this.selX + this.bsWidth + this.hWidth + 12;
        this.aY = this.selY + 4;
        this.aWidth = 8;
        this.aHeight = this.bsHeight;
        this.apHeight = 2;
        this.tX = this.selX + 4;
        this.tY = this.selY + this.aHeight + 8;
        this.tWidth = this.selectionSectionSize - (this.height + 2) * 2;
        this.tHeight = this.height;
        this.cX = this.tX + this.tWidth + 1;
        this.cY = this.tY + 1;
        this.cWidth = this.tHeight - 2;
        this.cHeight = this.tHeight - 2;
        this.pX = this.cX + this.cWidth + 1;
        this.pY = this.cY;
        this.pWidth = this.cWidth;
        this.pHeight = this.cHeight;
        this.rX = this.tX;
        this.rY = this.tY + this.height + 4;
        this.rWidth = this.width - 8;
        this.rHeight = this.tHeight - 4;
        this.pickSaturationAndBrightness(mouseX, mouseY, this.bsX, this.bsY, this.bsWidth, this.bsHeight);
        this.pickHue(mouseX, mouseY, this.hX, this.hY, this.hWidth, this.hHeight);
        this.pickAlpha(mouseX, mouseY, this.aX, this.aY, this.aWidth, this.aHeight);
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.selX, (float)this.selY), new Vec2f((float)(this.selX + this.selectionSectionSize), (float)(this.selY + this.selectionSectionSize + 8)), elementColor);
        Render2D.drawMainPicker(Render2D.vertexHelperUB, new Vec2f((float)this.bsX, (float)this.bsY), new Vec2f((float)(this.bsX + this.bsWidth), (float)(this.bsY + this.bsHeight)), (Color)this.colorSetting.getValue());
        Render2D.drawRoundedRectangleFilled(Render2D.vertexHelperUB, new Vec2f((float)(this.bspX - this.bspRadius / 2 + this.bsX), (float)(this.bspY - this.bspRadius / 2 + this.bsY)), new Vec2f((float)(this.bspX + this.bspRadius / 2 + this.bsX), (float)(this.bspY + this.bspRadius / 2 + this.bsY)), (float)this.bspRadius, 5, (Color)ClickGui.getInstance().pickerColor.getValue());
        Render2D.drawHuePickerVertical(Render2D.vertexHelperUB, new Vec2f((float)this.hX, (float)this.hY), new Vec2f((float)(this.hX + this.hWidth), (float)(this.hY + this.hHeight)));
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.hX, (float)(this.hpY + this.hY)), new Vec2f((float)(this.hX + this.hWidth), (float)(this.hpY + this.hY + this.hpHeight)), (Color)ClickGui.getInstance().pickerColor.getValue());
        Render2D.drawAlphaPickerVertical(Render2D.vertexHelperUB, new Vec2f((float)this.aX, (float)this.aY), new Vec2f((float)(this.aX + this.aWidth), (float)(this.aY + this.aHeight)), (Color)this.colorSetting.getValue());
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.aX, (float)(this.apY + this.aY)), new Vec2f((float)(this.aX + this.aWidth), (float)(this.apY + this.aY + this.apHeight)), (Color)ClickGui.getInstance().pickerColor.getValue());
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.tX, (float)this.tY), new Vec2f((float)(this.tX + this.tWidth), (float)(this.tY + this.tHeight)), (Color)ClickGui.getInstance().pickerBackground.getValue());
        if (MouseKeysTracker.mouseOver(this.cX, this.cY, this.cX + this.cWidth, this.cY + this.cHeight) && MouseKeysTracker.leftClicked) {
            this.copyTimer = new Timer();
            playGuiClick();
        }
        String message = this.formatColor((Color)this.colorSetting.getValue());
        if (this.copyTimer != null) {
            if (!this.copyTimer.passed(3000L)) {
                if (!this.copied) {
                    this.copyToClipboard(message);
                    this.copied = true;
                }
                message = "Copied!";
            }
            else {
                this.copied = false;
            }
        }
        if (MouseKeysTracker.mouseOver(this.pX, this.pY, this.pX + this.pWidth, this.pY + this.pHeight) && MouseKeysTracker.leftClicked) {
            final boolean decoded = this.decodeColor(this.pasteFromClipboard());
            if (!decoded) {
                this.pasteTimer = new Timer();
            }
            playGuiClick();
        }
        if (this.pasteTimer != null && !this.pasteTimer.passed(3000L)) {
            message = "Error!";
        }
        FontUtil.drawTextCenter(message, this.tX + this.tWidth / 2.0f, this.tY + this.tHeight / 2.0f, Color.WHITE);
        Render2D.drawImage(this.copy, new Vec2f((float)this.cX, (float)this.cY), new Vec2f((float)(this.cX + this.cWidth), (float)(this.cY + this.cHeight)));
        Render2D.drawImage(this.paste, new Vec2f((float)this.pX, (float)this.pY), new Vec2f((float)(this.pX + this.pWidth), (float)(this.pY + this.pHeight)));
        if (MouseKeysTracker.mouseOver(this.rX, this.rY, this.rX + this.rWidth, this.rY + this.rHeight) && MouseKeysTracker.leftClicked) {
            this.colorSetting.setRainbow(!this.colorSetting.isRainbow());
            playGuiClick();
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.rX, (float)this.rY), new Vec2f((float)(this.rX + this.rWidth), (float)(this.rY + this.rHeight)), (Color)ClickGui.getInstance().pickerBackground.getValue());
        final Color textColor = (Color)(this.colorSetting.isRainbow() ? ClickGui.getInstance().accentColor.getValue() : Color.WHITE);
        FontUtil.drawText("Rainbow", this.rX + this.rWidth / 2.0f - FontUtil.getStringWidth("Rainbow") / 2.0f, this.rY + this.rHeight / 2.0f - FontUtil.getStringHeight("Rainbow") / 2.0f, textColor);
    }
    
    private String formatColor(final Color color) {
        return "#" + String.format("%02X", 0xFF & color.getRed()) + String.format("%02X", 0xFF & color.getGreen()) + String.format("%02X", 0xFF & color.getBlue());
    }
    
    private boolean decodeColor(String code) {
        try {
            code = code.replaceAll("#", "");
            final int r = (int)Long.parseLong(code.substring(0, 2), 16);
            final int g = (int)Long.parseLong(code.substring(2, 4), 16);
            final int b = (int)Long.parseLong(code.substring(4, 6), 16);
            this.colorSetting.setValue((Object)new Color(r, g, b));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    private void pickSaturationAndBrightness(final int mouseX, final int mouseY, final int x, final int y, final int width, final int height) {
        final double pixX = 255.0f / width;
        this.bspX = (int)MathUtil.clamp((float)(ColorUtil.getSat((Color)this.colorSetting.getValue()) * 255.0f / pixX), 0.0f, (float)width);
        final double pixY = 255.0f / height;
        this.bspY = (int)MathUtil.clamp((float)((255.0f - ColorUtil.getBright((Color)this.colorSetting.getValue()) * 255.0f) / pixY), 0.0f, (float)height);
        if (MouseKeysTracker.mouseOver(x, y, x + width, y + height) && MouseKeysTracker.leftDown) {
            final float mpX = (float)(mouseX - x);
            float saturation = (float)(pixX * mpX);
            if (mpX >= width) {
                saturation = 255.0f;
            }
            if (mpX == 0.0f) {
                saturation = 0.0f;
            }
            final float mpY = (float)(mouseY - y);
            float brightness = (float)(pixY * mpY);
            if (mpY >= height) {
                brightness = 255.0f;
            }
            if (mpY == 0.0f) {
                brightness = 0.0f;
            }
            this.colorSetting.setValue((Object)new Color(Color.HSBtoRGB(ColorUtil.getHue((Color)this.colorSetting.getValue()), saturation / 255.0f, 1.0f - brightness / 255.0f)));
        }
    }
    
    private void pickHue(final int mouseX, final int mouseY, final int x, final int y, final int width, final int height) {
        final double pixY = 255.0f / height;
        this.hpY = (int)MathUtil.clamp((float)(ColorUtil.getHue((Color)this.colorSetting.getValue()) * 255.0f / pixY), 0.0f, (float)height);
        if (MouseKeysTracker.mouseOver(x, y, x + width, y + height) && MouseKeysTracker.leftDown) {
            final float mpY = (float)(mouseY - y);
            float hue = (float)(pixY * mpY);
            if (mpY >= height) {
                hue = 255.0f;
            }
            if (mpY == 0.0f) {
                hue = 0.0f;
            }
            this.colorSetting.setValue((Object)new Color(Color.HSBtoRGB(hue / 255.0f, ColorUtil.getSat((Color)this.colorSetting.getValue()), ColorUtil.getBright((Color)this.colorSetting.getValue()))));
        }
    }
    
    private void pickAlpha(final int mouseX, final int mouseY, final int x, final int y, final int width, final int height) {
        final double pixY = 255.0f / height;
        this.apY = (int)MathUtil.clamp((float)((255 - ((Color)this.colorSetting.getValue()).getAlpha()) / pixY), 0.0f, (float)(height - this.apHeight));
        if (MouseKeysTracker.mouseOver(x, y, x + width, y + height) && MouseKeysTracker.leftDown) {
            final float mpY = (float)(mouseY - y);
            int alpha = (int)(pixY * mpY);
            if (mpY >= height) {
                alpha = 255;
            }
            if (mpY == 0.0f) {
                alpha = 0;
            }
            alpha = 255 - (int)MathUtil.clamp((float)alpha, 0.0f, 255.0f);
            if (alpha < 0) {
                alpha = 0;
            }
            if (alpha > 255) {
                alpha = 255;
            }
            this.colorSetting.setValue((Object)new Color(((Color)this.colorSetting.getValue()).getRed(), ((Color)this.colorSetting.getValue()).getGreen(), ((Color)this.colorSetting.getValue()).getBlue(), alpha));
        }
    }
    
    private void copyToClipboard(final String s) {
        final StringSelection selection = new StringSelection(s);
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
    
    private String pasteFromClipboard() {
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final Transferable transferable = clipboard.getContents(this);
        if (transferable == null) {
            return "";
        }
        try {
            return (String)transferable.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception e) {
            return "";
        }
    }
}
