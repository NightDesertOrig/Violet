//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;

@FunctionalInterface
public interface Command<S>
{
    public static final int SINGLE_SUCCESS = 1;
    
    int run(final CommandContext<S> p0) throws CommandSyntaxException;
}
