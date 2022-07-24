//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.movement;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.api.event.handler.*;
import java.util.*;
import net.minecraft.potion.*;

@Module.Manifest(Module.Category.MOVEMENT)
public class InstantSpeed extends Module
{
    private final Value<Boolean> strafeJump;
    
    public InstantSpeed() {
        this.strafeJump = (Value<Boolean>)ValueFactory.booleanValue().withName("Strafe").withVal((Object)true).build((Module)this);
    }
    
    @Listener
    public void listen(final PlayerMoveEvent event) {
        if (this.mc.player.isSneaking() || this.mc.player.isInWater() || this.mc.player.isInLava()) {
            return;
        }
        if (this.mc.player.onGround && (boolean)this.strafeJump.getValue()) {
            event.setY(this.mc.player.motionY = 0.4);
        }
        final MovementInput movementInput = this.mc.player.movementInput;
        float moveForward = movementInput.moveForward;
        float moveStrafe = movementInput.moveStrafe;
        float rotationYaw = this.mc.player.rotationYaw;
        if (moveForward == 0.0 && moveStrafe == 0.0) {
            event.setX(0.0);
            event.setZ(0.0);
        }
        else {
            if (moveForward != 0.0) {
                if (moveStrafe > 0.0) {
                    rotationYaw += ((moveForward > 0.0) ? -45 : 45);
                }
                else if (moveStrafe < 0.0) {
                    rotationYaw += ((moveForward > 0.0) ? 45 : -45);
                }
                moveStrafe = 0.0f;
                if (moveForward != 0.0f) {
                    moveForward = ((moveForward > 0.0) ? 1.0f : -1.0f);
                }
            }
            moveStrafe = ((moveStrafe == 0.0f) ? moveStrafe : ((moveStrafe > 0.0) ? 1.0f : -1.0f));
            event.setX(moveForward * this.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + moveStrafe * this.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
            event.setZ(moveForward * this.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - moveStrafe * this.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
        }
    }
    
    public double getMaxSpeed() {
        double maxModifier = 0.2873;
        if (this.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            maxModifier *= 1.0 + 0.2 * (Objects.requireNonNull(this.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return maxModifier;
    }
}
