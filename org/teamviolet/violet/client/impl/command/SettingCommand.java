//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;

public class SettingCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("set").then(RequiredArgumentBuilder.argument("module", (ArgumentType)StringArgumentType.string()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)RequiredArgumentBuilder.argument("setting", (ArgumentType)StringArgumentType.string()).then(RequiredArgumentBuilder.argument("value", (ArgumentType)BoolArgumentType.bool()).executes(c -> {
            final boolean value = BoolArgumentType.getBool(c, "value");
            return 1;
        }))).then(RequiredArgumentBuilder.argument("value", (ArgumentType)DoubleArgumentType.doubleArg()).executes(c -> {
            final double value = DoubleArgumentType.getDouble(c, "value");
            return 1;
        }))).then(RequiredArgumentBuilder.argument("value", (ArgumentType)FloatArgumentType.floatArg()).executes(c -> {
            final float value = FloatArgumentType.getFloat(c, "value");
            return 1;
        }))).then(RequiredArgumentBuilder.argument("value", (ArgumentType)IntegerArgumentType.integer()).executes(c -> {
            final int value = IntegerArgumentType.getInteger(c, "value");
            return 1;
        }))).then(RequiredArgumentBuilder.argument("value", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String value = StringArgumentType.getString(c, "value");
            return 1;
        })))));
    }
}
