//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.util.game.*;
import com.mojang.brigadier.exceptions.*;
import org.teamviolet.violet.client.util.account.*;

public class VersionCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("name").then(RequiredArgumentBuilder.argument("name", (ArgumentType)StringArgumentType.string()).executes(c -> {
            Client.name = StringArgumentType.getString(c, "name");
            return 1;
        }))).executes(c -> {
            InfoUtil.chatError("You didnt select a name!");
            return 2;
        }));
    }
}
