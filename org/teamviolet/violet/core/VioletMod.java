//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.core;

import net.minecraftforge.fml.common.*;
import org.teamviolet.violet.client.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "violet", name = "Violet", version = "1.0.4")
public class VioletMod
{
    public static final String MOD_ID = "violet";
    public static final String MOD_NAME = "Violet";
    public static final String VERSION = "1.0.4";
    
    public VioletMod() {
        Violet.getViolet();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Violet.getViolet().listen(event);
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        Violet.getViolet().listen(event);
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        Violet.getViolet().listen(event);
    }
}
