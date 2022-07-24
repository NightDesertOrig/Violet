//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import net.minecraft.client.entity.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.game.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import com.mojang.brigadier.exceptions.*;
import java.util.*;

public class FakePlayerCommand implements VioletCommand
{
    public static final Map<String, EntityOtherPlayerMP> FAKE_PLAYERS;
    
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("fakeplayer").then(RequiredArgumentBuilder.argument("name", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String name = StringArgumentType.getString(c, "name").toLowerCase();
            if (FakePlayerCommand.FAKE_PLAYERS.containsKey(name)) {
                final EntityOtherPlayerMP playerMP = FakePlayerCommand.FAKE_PLAYERS.get(name);
                FakePlayerCommand.FAKE_PLAYERS.remove(name);
                Utils.world().removeEntity((Entity)playerMP);
                InfoUtil.chatInfo("Removed Fake Player with name " + name + ".");
            }
            else {
                final EntityOtherPlayerMP playerMP = new EntityOtherPlayerMP((World)Utils.world(), new GameProfile(Utils.mc.player.getUniqueID(), name));
                FakePlayerCommand.FAKE_PLAYERS.put(name, playerMP);
                Utils.world().addEntityToWorld(playerMP.getEntityId(), (Entity)playerMP);
                playerMP.copyLocationAndAnglesFrom((Entity)Utils.mc.player);
                playerMP.inventory = Utils.mc.player.inventory;
                InfoUtil.chatInfo("Added Fake Player with name " + name + ".");
            }
            return 1;
        })));
    }
    
    static {
        FAKE_PLAYERS = new HashMap<String, EntityOtherPlayerMP>();
    }
}
