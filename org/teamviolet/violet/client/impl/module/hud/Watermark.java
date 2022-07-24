//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.hud;

import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.account.*;
import org.teamviolet.violet.client.util.render.font.*;
import java.awt.*;

@Module.Manifest(Module.Category.HUD)
public class Watermark extends HudModule
{
    private String message;
    
    public Watermark() {
        super(2, 2);
        this.message = Client.name + " - " + Client.version;
    }
    
    public void updateBounds() {
        this.width = (int)FontUtil.getStringWidth(this.message);
        this.height = (int)FontUtil.getStringHeight(this.message);
        this.validatePosition();
    }
    
    public void draw() {
        this.updateBounds();
        FontUtil.drawText(this.message = Client.name + " - " + Client.version, (float)this.x, (float)this.y, (Color)this.defaultColor.getValue());
    }
}
