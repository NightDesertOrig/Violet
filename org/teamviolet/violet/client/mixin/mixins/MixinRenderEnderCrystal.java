//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderEnderCrystal.class })
public abstract class MixinRenderEnderCrystal
{
    private int renders;
    
    public MixinRenderEnderCrystal() {
        this.renders = 0;
    }
    
    @Shadow
    public abstract void doRender(final EntityEnderCrystal p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    @ModifyArgs(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void modifyRenderCrystal(final Args args) {
        final float f = (float)args.get(2) / 3.0f;
        final float spin = (float)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crystalSpin.getValue()) ? RenderTweaks.getInstance().spinFactor.getValue() : 1.0f);
        args.set(2, (Object)((RenderTweaks.getInstance().isEnabled() && (boolean)RenderTweaks.getInstance().crystalSpin.getValue()) ? (f * spin) : args.get(2)));
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("HEAD") }, cancellable = true)
    public void doRenderHead(final EntityEnderCrystal entityEnderCrystal, final double d1, final double d2, final double d3, final float f1, final float f2, final CallbackInfo ci) {
        final RenderEnderCrystalEvent.Pre renderEnderCrystalEvent = new RenderEnderCrystalEvent.Pre(entityEnderCrystal, d1, d2, d3, this.renders);
        Violet.getViolet().getDispatcher().post((Object)renderEnderCrystalEvent);
        if (renderEnderCrystalEvent.isCanceled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("TAIL") }, cancellable = true)
    public void doRenderTail(final EntityEnderCrystal entityEnderCrystal, final double d1, final double d2, final double d3, final float f1, final float f2, final CallbackInfo ci) {
        final RenderEnderCrystalEvent.Post renderEnderCrystalEvent = new RenderEnderCrystalEvent.Post(entityEnderCrystal, d1, d2, d3, this.renders);
        Violet.getViolet().getDispatcher().post((Object)renderEnderCrystalEvent);
        if (this.renders >= 1) {
            this.renders = 0;
        }
        if (renderEnderCrystalEvent.shouldRenderAgain()) {
            ++this.renders;
            this.doRender(entityEnderCrystal, d1, d2, d3, f1, f2);
        }
        if (renderEnderCrystalEvent.isCanceled()) {
            ci.cancel();
        }
    }
}
