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
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.module.*;
import java.util.*;
import com.mojang.brigadier.exceptions.*;

public class SettingsCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("settings").then(RequiredArgumentBuilder.argument("module", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String moduleName = StringArgumentType.getString(c, "module");
            final Module module = Violet.getViolet().getModuleManager().getModule(moduleName);
            if (module == null) {
                InfoUtil.chatError("Could not find module " + moduleName + ".");
                return 2;
            }
            for (final Value<?> container : module.getValueList()) {
                InfoUtil.chatInfo(container.getName() + " | " + container.getValue() + " | " + container.getDescription());
            }
            return 1;
        })));
    }
}
