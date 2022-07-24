//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityLivingBase.class })
public interface IEntityLivingBase
{
    @Accessor("interpTargetX")
    void setTargetX(final double p0);
    
    @Accessor("interpTargetY")
    void setTargetY(final double p0);
    
    @Accessor("interpTargetZ")
    void setTargetZ(final double p0);
    
    @Accessor("interpTargetYaw")
    void setTargetYaw(final double p0);
    
    @Accessor("interpTargetPitch")
    void setTargetPitch(final double p0);
    
    @Accessor("newPosRotationIncrements")
    void setRotationIncrements(final int p0);
}
