//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import org.teamviolet.violet.client.api.module.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.util.game.*;
import com.mojang.brigadier.exceptions.*;
import org.teamviolet.violet.client.*;

public class ToggleCommand implements VioletCommand
{
    private Module lastModule;
    
    public ToggleCommand() {
        this.lastModule = null;
    }
    
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("t").then(RequiredArgumentBuilder.argument("module", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String name = StringArgumentType.getString(c, "module");
            final Module module = Violet.getViolet().getModuleManager().getModule(name);
            if (module != null) {
                (this.lastModule = module).setEnabled(!module.isEnabled());
                return 1;
            }
            InfoUtil.chatError("Could not find module " + name + ".");
            return 2;
        }))).executes(c -> {
            if (this.lastModule != null) {
                this.lastModule.setEnabled(!this.lastModule.isEnabled());
                return 1;
            }
            InfoUtil.chatError("You have not toggled a module using this command yet!");
            return 2;
        }));
    }
}
