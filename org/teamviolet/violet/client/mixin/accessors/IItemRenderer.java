//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.accessors;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;

@Mixin({ ItemRenderer.class })
public interface IItemRenderer
{
    @Accessor("equippedProgressOffHand")
    void setequippedProgressOffHand(final float p0);
    
    @Accessor("itemRenderer")
    RenderItem getRenderItem();
    
    @Accessor("prevEquippedProgressMainHand")
    float getPrevEquippedProgressMainHand();
    
    @Accessor("equippedProgressMainHand")
    void setEquippedProgressMainHand(final float p0);
    
    @Accessor("itemStackMainHand")
    void setItemStackMainHand(final ItemStack p0);
}
