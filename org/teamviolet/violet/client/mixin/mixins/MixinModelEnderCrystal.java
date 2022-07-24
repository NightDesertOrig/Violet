//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelEnderCrystal.class })
public class MixinModelEnderCrystal
{
    @ModifyArgs(method = { "render" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;scale(FFF)V", ordinal = 0))
    private void modifyRenderCrystal(final Args args) {
        args.set(0, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crystalScale.getValue()) ? RenderTweaks.getInstance().scaleFactor.getValue() : ((Float)args.get(0))));
        args.set(1, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crystalScale.getValue()) ? RenderTweaks.getInstance().scaleFactor.getValue() : ((Float)args.get(1))));
        args.set(2, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crystalScale.getValue()) ? RenderTweaks.getInstance().scaleFactor.getValue() : ((Float)args.get(2))));
    }
}
