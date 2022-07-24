//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.module.hud;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.impl.module.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;

public abstract class HudModule extends Module
{
    public int x;
    public int y;
    public int width;
    public int height;
    public int defX;
    public int defY;
    protected final Value<Color> defaultColor;
    
    public HudModule(final int defX, final int defY) {
        this.x = -100;
        this.y = -100;
        this.defaultColor = ValueFactory.colorValue().withName("Color").withVal(ClickGui.getInstance().accentColor.getValue()).withDesc("Default color for the HUD element.").build(this);
        this.defX = defX;
        this.defY = defY;
    }
    
    public abstract void updateBounds();
    
    public abstract void draw();
    
    protected void validatePosition() {
        if (this.x == -100) {
            this.x = this.defX;
        }
        if (this.y == -100) {
            this.y = this.defY;
        }
    }
    
    public Vec2f getTopCorner() {
        return new Vec2f((float)(this.x - 2), (float)(this.y - 2));
    }
    
    public Vec2f getBottomCorner() {
        return new Vec2f((float)(this.x + this.width + 2), (float)(this.y + this.height + 2));
    }
    
    protected ScaledResolution sr() {
        return new ScaledResolution(this.mc);
    }
}
