//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ EntityLivingBase.class })
public abstract class MixinEntityLivingBase extends Entity
{
    public MixinEntityLivingBase(final World worldIn) {
        super(worldIn);
    }
    
    @Shadow
    public abstract boolean isPotionActive(final Potion p0);
    
    @Shadow
    public abstract PotionEffect getActivePotionEffect(final Potion p0);
    
    @Overwrite
    private int getArmSwingAnimationEnd() {
        if (this.isPotionActive(MobEffects.HASTE)) {
            return 6 - (1 + this.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
        }
        if (!Swing.getInstance().isEnabled()) {
            return this.isPotionActive(MobEffects.MINING_FATIGUE) ? (6 + (1 + this.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2) : 6;
        }
        if (Swing.getInstance().speed.getValue() == Swing.Speed.Slow) {
            return 6 + (1 + (int)Swing.getInstance().factor.getValue()) * 2;
        }
        return 6 - (1 + (int)Swing.getInstance().factor.getValue() / 10) * 2;
    }
}
