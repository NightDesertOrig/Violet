//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.hud;

import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.util.render.font.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import java.util.*;

@Module.Manifest(Module.Category.HUD)
public class ModuleList extends HudModule
{
    private int topHeight;
    private int longestModuleLength;
    
    public ModuleList() {
        super(2, 2);
        this.topHeight = 0;
        this.longestModuleLength = 0;
    }
    
    public void updateBounds() {
        this.width = this.longestModuleLength;
        this.height = this.topHeight;
        this.validatePosition();
    }
    
    public void draw() {
        this.updateBounds();
        this.topHeight = 0;
        this.longestModuleLength = 0;
        final ArrayList<Module> modulesToDraw = new ArrayList<Module>();
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            if (module.isEnabled() && !module.isHidden()) {
                modulesToDraw.add(module);
            }
        }
        modulesToDraw.sort(ModuleList::order);
        int drawY = this.y;
        boolean right = false;
        if (this.x > this.sr().getScaledWidth() / 2) {
            right = true;
        }
        for (final Module module2 : modulesToDraw) {
            if (FontUtil.getStringWidth(module2.getName()) > this.longestModuleLength) {
                this.longestModuleLength = (int)FontUtil.getStringWidth(module2.getName());
            }
            FontUtil.drawText(module2.getName() + (module2.getInfo().equals("") ? "" : (ChatFormatting.WHITE + " [" + module2.getInfo() + "]")), right ? (this.x + this.longestModuleLength - FontUtil.getStringWidth(module2.getName() + (module2.getInfo().equals("") ? "" : (ChatFormatting.WHITE + " [" + module2.getInfo() + "]")))) : ((float)this.x), (float)drawY, (Color)this.defaultColor.getValue());
            drawY += (int)(FontUtil.getStringHeight(module2.getName()) + 2.0f);
        }
        this.topHeight = drawY - this.y;
    }
    
    private static int order(final Module m1, final Module m2) {
        final String s1 = m1.getName();
        final String s2 = m2.getName();
        return Integer.compare(s2.length(), s1.length());
    }
}
