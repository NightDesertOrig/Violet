//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.game.*;
import java.util.*;
import com.mojang.brigadier.exceptions.*;

public class ModulesCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("modules").executes(c -> {
            for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
                InfoUtil.chatInfo(module.getName() + " (" + module.getId() + ") | " + module.getDescription());
            }
            return 1;
        }));
    }
}
