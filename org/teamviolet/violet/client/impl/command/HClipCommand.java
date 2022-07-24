//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import net.minecraft.util.math.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.entity.*;
import com.mojang.brigadier.exceptions.*;

public class HClipCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("hclip").then(RequiredArgumentBuilder.argument("blocks", (ArgumentType)IntegerArgumentType.integer()).executes(c -> {
            final int blocks = IntegerArgumentType.getInteger(c, "blocks");
            final Vec3d direction = this.direction(Utils.mc.player.rotationYaw);
            if (direction != null) {
                final Entity entity = (Entity)(Utils.mc.player.isRiding() ? Utils.mc.player.getRidingEntity() : Utils.mc.player);
                entity.setPosition(Utils.mc.player.posX + direction.x * blocks, Utils.mc.player.posY, Utils.mc.player.posZ + direction.z * blocks);
                InfoUtil.chatInfo("Lagged you forward");
            }
            return 1;
        })));
    }
    
    private Vec3d direction(final float yaw) {
        return new Vec3d(Math.cos(this.degToRad(yaw + 90.0f)), 0.0, Math.sin(this.degToRad(yaw + 90.0f)));
    }
    
    private double degToRad(final double deg) {
        return deg * 0.01745329238474369;
    }
}
