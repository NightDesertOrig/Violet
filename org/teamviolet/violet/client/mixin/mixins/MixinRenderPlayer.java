//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;

@Mixin({ RenderPlayer.class })
public abstract class MixinRenderPlayer extends RenderLivingBase<AbstractClientPlayer>
{
    public MixinRenderPlayer(final RenderManager renderManagerIn, final ModelBase modelBaseIn, final float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }
    
    @Shadow
    public abstract ModelPlayer getMainModel();
    
    @Shadow
    protected abstract void setModelVisibilities(final AbstractClientPlayer p0);
    
    @Inject(method = { "setModelVisibilities" }, at = { @At("TAIL") })
    private void setModelVisibilitiesI(final AbstractClientPlayer clientPlayer, final CallbackInfo ci) {
        final ModelPlayer model = this.getMainModel();
        if (RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crouch.getValue()) {
            model.isSneak = true;
        }
    }
    
    @Overwrite
    public void doRender(final AbstractClientPlayer entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderPlayerEvent.Pre((EntityPlayer)entity, (RenderPlayer)this, partialTicks, x, y, z))) {
            return;
        }
        if (!entity.isUser() || this.renderManager.renderViewEntity == entity) {
            double d0 = y;
            if (entity.isSneaking() || (RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crouch.getValue())) {
                d0 = y - 0.125;
            }
            this.setModelVisibilities(entity);
            GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
            super.doRender((EntityLivingBase)entity, x, d0, z, entityYaw, partialTicks);
            GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderPlayerEvent.Post((EntityPlayer)entity, (RenderPlayer)this, partialTicks, x, y, z));
    }
}
