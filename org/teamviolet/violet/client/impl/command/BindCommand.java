//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.util.game.*;
import org.lwjgl.input.*;
import org.teamviolet.violet.client.api.module.*;
import com.mojang.brigadier.exceptions.*;

public class BindCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("bind").then(RequiredArgumentBuilder.argument("moduleName", (ArgumentType)StringArgumentType.string()).then(RequiredArgumentBuilder.argument("bindName", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String moduleName = StringArgumentType.getString(c, "moduleName");
            final String bind = StringArgumentType.getString(c, "bindName").toUpperCase();
            final Module module = Violet.getViolet().getModuleManager().getModule(moduleName);
            if (module == null) {
                InfoUtil.chatError("Module " + moduleName + " does not exist.");
                return 2;
            }
            module.getBind().setKey(Keyboard.getKeyIndex(bind));
            InfoUtil.chatInfo("Successfully bound " + module.getName() + " to " + bind);
            return 1;
        }))));
    }
}
