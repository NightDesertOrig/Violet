//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemRenderer.class })
public class MixinItemRenderer implements IItemRenderer
{
    @Shadow
    private float equippedProgressOffHand;
    @Shadow
    @Final
    private RenderItem itemRenderer;
    @Shadow
    private float equippedProgressMainHand;
    @Shadow
    private float prevEquippedProgressMainHand;
    @Shadow
    private ItemStack itemStackMainHand;
    
    @Redirect(method = { "renderItemInFirstPerson(F)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"))
    private void renderItemInFirstPersonHook(final ItemRenderer itemRenderer, final AbstractClientPlayer player, final float drinkOffset, final float mapAngle, final EnumHand hand, final float x, final ItemStack stack, final float y) {
        final RenderItemFPEvent event = new RenderItemFPEvent(itemRenderer, player, drinkOffset, mapAngle, hand, x, stack, y, 0);
        Violet.getViolet().getDispatcher().post((Object)event);
        if (!ViewModel.getInstance().isEnabled()) {
            itemRenderer.renderItemInFirstPerson(player, drinkOffset, mapAngle, hand, x, stack, y);
        }
    }
    
    @Inject(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = At.Shift.AFTER) })
    private void pushMatrixHook(final CallbackInfo info) {
        final RenderItemFPEvent rifpe = new RenderItemFPEvent((ItemRenderer)null, (AbstractClientPlayer)null, 1.0f, 1.0f, (EnumHand)null, 1.0f, (ItemStack)null, 1.0f, -1);
        Violet.getViolet().getDispatcher().post((Object)rifpe);
    }
    
    public void setequippedProgressOffHand(final float f) {
        this.equippedProgressOffHand = f;
    }
    
    public RenderItem getRenderItem() {
        return this.itemRenderer;
    }
    
    public float getPrevEquippedProgressMainHand() {
        return this.prevEquippedProgressMainHand;
    }
    
    public void setEquippedProgressMainHand(final float equippedProgressMainHand) {
        this.equippedProgressMainHand = equippedProgressMainHand;
    }
    
    public void setItemStackMainHand(final ItemStack itemStackMainHand) {
        this.itemStackMainHand = itemStackMainHand;
    }
}
