//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.impl.module.client.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.font.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ FontRenderer.class })
public class MixinFontRenderer
{
    @Inject(method = { "drawString(Ljava/lang/String;FFIZ)I" }, at = { @At("HEAD") }, cancellable = true)
    public void renderStringHook(final String text, final float x, final float y, final int color, final boolean dropShadow, final CallbackInfoReturnable<Integer> info) {
        if (Utils.mc.world != null && CustomFont.getInstance().isEnabled() && (boolean)CustomFont.getInstance().overriden.getValue()) {
            final float result = FontUtil.drawTextCF(text, x, y, new Color(color));
            info.setReturnValue((Object)(int)result);
        }
    }
}
