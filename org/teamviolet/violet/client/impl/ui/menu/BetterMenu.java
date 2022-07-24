//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.menu;

import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.client.gui.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.util.render.font.*;
import org.teamviolet.violet.client.api.event.handler.*;

public class BetterMenu
{
    public void init() {
        Violet.getViolet().getDispatcher().register((Object)this);
    }
    
    @Listener
    public void listen(final RenderMainMenuEvent event) {
        final ScaledResolution sr = new ScaledResolution(Utils.mc);
        FontUtil.drawTextRainbow("Violet", 2.0f, 2.0f, ColorUtil.getRainbow(), 100.0f, true);
    }
}
