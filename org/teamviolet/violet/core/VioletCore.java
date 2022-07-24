//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.core;

import net.minecraftforge.fml.relauncher.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import javax.annotation.*;
import java.util.*;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("violet")
public class VioletCore implements IFMLLoadingPlugin
{
    public VioletCore() {
        message("Launching...");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.violet.json");
    }
    
    public String[] getASMTransformerClass() {
        return null;
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    @Nullable
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    public static void message(final String message) {
        System.out.println("[Violet Core] " + message);
    }
}
