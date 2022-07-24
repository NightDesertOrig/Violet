//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;

public class HelpCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("help").then(RequiredArgumentBuilder.argument("page", (ArgumentType)IntegerArgumentType.integer()).executes(c -> this.displayHelp(IntegerArgumentType.getInteger(c, "page"))))).executes(c -> this.displayHelp(1)));
    }
    
    private int displayHelp(final int page) {
        if (page < 1 || page > 4) {
            return 2;
        }
        return 1;
    }
}
