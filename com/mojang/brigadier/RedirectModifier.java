//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

import com.mojang.brigadier.context.*;
import java.util.*;
import com.mojang.brigadier.exceptions.*;

@FunctionalInterface
public interface RedirectModifier<S>
{
    Collection<S> apply(final CommandContext<S> p0) throws CommandSyntaxException;
}
