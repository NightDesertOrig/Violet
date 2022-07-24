//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.impl.ui.hudeditor.*;
import net.minecraft.client.gui.*;

@Module.Manifest(Module.Category.CLIENT)
public class HUDEditor extends Module
{
    private static HUDEditor hudEditor;
    
    public static HUDEditor getInstance() {
        if (HUDEditor.hudEditor == null) {
            HUDEditor.hudEditor = new HUDEditor();
        }
        return HUDEditor.hudEditor;
    }
    
    private HUDEditor() {
        this.hidden.setValue((Object)true);
    }
    
    public void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.mc.displayGuiScreen((GuiScreen)ClickHudScreen.getInstance());
    }
}
