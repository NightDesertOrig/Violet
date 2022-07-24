//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;

@Mixin({ GuiMainMenu.class })
public abstract class MixinMainMenu extends GuiScreen
{
    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES;
    
    static {
        MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
    }
}
