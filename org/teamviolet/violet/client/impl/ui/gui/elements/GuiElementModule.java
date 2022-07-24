//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.font.*;

public class GuiElementModule extends GuiElement
{
    public Module module;
    private boolean odd;
    
    public GuiElementModule(final Module module, final boolean odd, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.module = module;
        this.odd = odd;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        Color elementColor = (Color)ClickGui.getInstance().background.getValue();
        final Color textColor = (Color)(this.module.isEnabled() ? ClickGui.getInstance().accentColor.getValue() : Color.WHITE);
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height)) {
            if (MouseKeysTracker.leftClicked) {
                this.module.setEnabled(!this.module.isEnabled());
                playGuiClick();
            }
            if (MouseKeysTracker.rightClicked) {
                this.module.setOpen(!this.module.isOpen());
                playGuiClick();
            }
            elementColor = new Color(Color.HSBtoRGB(ColorUtil.getHue(elementColor), ColorUtil.getSat(elementColor), MathUtil.clamp(ColorUtil.getBright(elementColor) + 0.1f, 0.0f, 1.0f)));
        }
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), elementColor);
        FontUtil.drawText(this.module.getName(), (float)(this.x + 4), this.y + this.height / 2.0f - FontUtil.getStringHeight(this.module.getName()) / 2.0f, textColor);
    }
}
