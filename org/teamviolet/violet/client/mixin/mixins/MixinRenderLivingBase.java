//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T>
{
    private int renders;
    @Shadow
    protected ModelBase mainModel;
    
    protected MixinRenderLivingBase(final RenderManager renderManager) {
        super(renderManager);
        this.renders = 0;
    }
    
    @Shadow
    protected abstract void renderModel(final T p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    @Shadow
    protected abstract float handleRotationFloat(final T p0, final float p1);
    
    @Shadow
    protected abstract float interpolateRotation(final float p0, final float p1, final float p2);
    
    @ModifyArgs(method = { "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;renderModel(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V"))
    private void modifyFloatI(final Args args) {
        args.set(1, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().staticLimbs.getValue()) ? RenderTweaks.getInstance().limbFactor.getValue() : ((Float)args.get(1))));
        args.set(2, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().staticLimbs.getValue()) ? RenderTweaks.getInstance().limbFactor.getValue() : ((Float)args.get(2))));
    }
    
    @Inject(method = { "renderModel" }, at = { @At("HEAD") }, cancellable = true)
    public void renderModel(final T entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final CallbackInfo ci) {
        if (entitylivingbaseIn instanceof EntityPopCham) {
            final RenderPopChamEvent.Pre popChamEvent = new RenderPopChamEvent.Pre((EntityPopCham)entitylivingbaseIn, this.renders);
            Violet.getViolet().getDispatcher().post((Object)popChamEvent);
            if (popChamEvent.isCanceled()) {
                ci.cancel();
            }
        }
        else {
            final RenderLivingModelEvent.Pre renderLivingBaseEvent = new RenderLivingModelEvent.Pre((EntityLivingBase)entitylivingbaseIn, this.renders);
            Violet.getViolet().getDispatcher().post((Object)renderLivingBaseEvent);
            if (renderLivingBaseEvent.isCanceled()) {
                ci.cancel();
            }
        }
    }
    
    @Inject(method = { "renderModel" }, at = { @At("TAIL") }, cancellable = true)
    public void renderModel1(final T entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final CallbackInfo ci) {
        if (entitylivingbaseIn instanceof EntityPopCham) {
            final RenderPopChamEvent.Post renderPopChamEvent = new RenderPopChamEvent.Post((EntityPopCham)entitylivingbaseIn, this.renders);
            Violet.getViolet().getDispatcher().post((Object)renderPopChamEvent);
            if (this.renders >= 1) {
                this.renders = 0;
            }
            if (renderPopChamEvent.shouldRenderAgain()) {
                ++this.renders;
                this.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            if (renderPopChamEvent.isCanceled()) {
                ci.cancel();
            }
        }
        else {
            final RenderLivingModelEvent.Post renderLivingBaseEvent = new RenderLivingModelEvent.Post((EntityLivingBase)entitylivingbaseIn, this.renders);
            Violet.getViolet().getDispatcher().post((Object)renderLivingBaseEvent);
            if (this.renders >= 1) {
                this.renders = 0;
            }
            if (renderLivingBaseEvent.shouldRenderAgain()) {
                ++this.renders;
                this.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            if (renderLivingBaseEvent.isCanceled()) {
                ci.cancel();
            }
        }
    }
}
