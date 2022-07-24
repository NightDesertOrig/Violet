//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP extends AbstractClientPlayer
{
    @Shadow
    @Final
    public NetHandlerPlayClient connection;
    @Shadow
    public MovementInput movementInput;
    
    public MixinEntityPlayerSP(final World worldIn, final GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(final AbstractClientPlayer instance, final MoverType moverType, final double x, final double y, final double z) {
        if (instance != Utils.player()) {
            instance.move(moverType, x, y, z);
        }
        else {
            final PlayerMoveEvent playerMoveEvent = new PlayerMoveEvent(moverType, x, y, z);
            Violet.getViolet().getDispatcher().post((Object)playerMoveEvent);
            if (!playerMoveEvent.isCanceled()) {
                super.move(playerMoveEvent.getType(), playerMoveEvent.getX(), playerMoveEvent.getY(), playerMoveEvent.getZ());
            }
        }
    }
    
    @Inject(method = { "onUpdate" }, at = { @At("HEAD") }, cancellable = true)
    public void redirectUpdateWalking(final CallbackInfo ci) {
        final UpdateWalkingPlayerEvent pre = (UpdateWalkingPlayerEvent)UpdateWalkingPlayerEvent.Pre.get(Utils.mc.player.posX, Utils.mc.player.getEntityBoundingBox().minY, Utils.mc.player.posY, Utils.mc.player.rotationYaw, Utils.mc.player.rotationPitch, Utils.mc.player.onGround);
        Violet.getViolet().getDispatcher().post((Object)pre);
        final UpdateWalkingPlayerEvent post = (UpdateWalkingPlayerEvent)UpdateWalkingPlayerEvent.Post.get(Utils.mc.player.posX, Utils.mc.player.posY, Utils.mc.player.posY, Utils.mc.player.rotationYaw, Utils.mc.player.rotationPitch, Utils.mc.player.onGround);
        Violet.getViolet().getDispatcher().post((Object)post);
        Rotates.reset();
    }
}
