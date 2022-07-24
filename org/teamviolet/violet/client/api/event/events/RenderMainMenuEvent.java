//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import net.minecraft.client.gui.*;

public class RenderMainMenuEvent
{
    private GuiScreen menu;
    
    public RenderMainMenuEvent(final GuiScreen menu) {
        this.menu = menu;
    }
    
    public GuiScreen getMenu() {
        return this.menu;
    }
}
