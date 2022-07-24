//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui.elements;

import org.teamviolet.violet.client.api.gui.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.misc.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.render.font.*;

public class GuiElementHeader extends GuiElement
{
    private Module.Category category;
    
    public GuiElementHeader(final Module.Category category, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.category = category;
    }
    
    public void drawElement(final int mouseX, final int mouseY) {
        if (MouseKeysTracker.mouseOver(this.x, this.y, this.x + this.width, this.y + this.height) && MouseKeysTracker.rightClicked) {
            this.category.setOpen(!this.category.isOpen());
            playGuiClick();
        }
        this.drawRect(new Vec2f((float)this.x, (float)this.y), new Vec2f((float)(this.x + this.width), (float)(this.y + this.height)), (Color)ClickGui.getInstance().accentColor.getValue(), (ClickGui.RectangleMode)ClickGui.getInstance().headerMode.getValue());
        FontUtil.drawTextCenter(this.category.name(), this.x + this.width / 2.0f, this.y + this.height / 2.0f, Color.WHITE);
    }
}
