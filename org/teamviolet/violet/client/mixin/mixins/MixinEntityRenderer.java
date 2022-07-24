//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.shader.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import org.teamviolet.violet.client.impl.module.player.*;
import java.util.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityRenderer.class })
public abstract class MixinEntityRenderer implements IEntityRenderer
{
    @Shadow
    @Final
    private Minecraft mc;
    @Shadow
    private float thirdPersonDistancePrev;
    @Shadow
    private boolean cloudFog;
    @Shadow
    private boolean lightmapUpdateNeeded;
    @Shadow
    @Final
    private int[] lightmapColors;
    @Shadow
    private long prevFrameTime;
    @Shadow
    private float smoothCamYaw;
    @Shadow
    private float smoothCamPitch;
    @Shadow
    private float smoothCamPartialTicks;
    @Shadow
    private float smoothCamFilterX;
    @Shadow
    private float smoothCamFilterY;
    @Shadow
    private long timeWorldIcon;
    @Shadow
    private ShaderGroup shaderGroup;
    @Shadow
    private boolean useShader;
    @Shadow
    private long renderEndNanoTime;
    
    @Shadow
    protected abstract void setupCameraTransform(final float p0, final int p1);
    
    @Shadow
    protected abstract void renderHand(final float p0, final int p1);
    
    @Shadow
    public abstract void renderWorld(final float p0, final long p1);
    
    @Shadow
    protected abstract void createWorldIcon();
    
    @Shadow
    public abstract void setupOverlayRendering();
    
    @Shadow
    protected abstract void renderItemActivation(final int p0, final int p1, final float p2);
    
    public void invokeSetupCameraTransform(final float partialTicks, final int pass) {
        this.setupCameraTransform(partialTicks, pass);
    }
    
    public void invokeRenderHand(final float partialTicks, final int pass) {
        this.renderHand(partialTicks, pass);
    }
    
    public void setLightmapUpdateNeeded(final boolean val) {
        this.lightmapUpdateNeeded = val;
    }
    
    @Inject(method = { "updateLightmap" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;updateDynamicTexture()V", shift = At.Shift.BEFORE) })
    private void updateTextureHook(final float partialTicks, final CallbackInfo ci) {
        final UpdateLightmapEvent event = new UpdateLightmapEvent(this.lightmapColors);
        Violet.getViolet().getDispatcher().post((Object)event);
        for (int i = 0; i < event.lightmapColors.length; ++i) {
            this.lightmapColors[i] = event.lightmapColors[i];
        }
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcluding(final WorldClient worldClient, final Entity entityIn, final AxisAlignedBB boundingBox, final Predicate predicate) {
        if (NoHitbox.getInstance().isEnabled()) {
            if (!(boolean)NoHitbox.getInstance().selectional.getValue()) {
                return new ArrayList<Entity>();
            }
            if ((Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && (boolean)NoHitbox.getInstance().pickaxe.getValue()) || (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemAppleGold && (boolean)NoHitbox.getInstance().gapples.getValue()) || (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal && (boolean)NoHitbox.getInstance().crystals.getValue())) {
                return new ArrayList<Entity>();
            }
        }
        return (List<Entity>)worldClient.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate);
    }
}
