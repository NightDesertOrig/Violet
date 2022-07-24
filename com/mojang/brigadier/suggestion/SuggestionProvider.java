//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.suggestion;

import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.exceptions.*;

@FunctionalInterface
public interface SuggestionProvider<S>
{
    CompletableFuture<Suggestions> getSuggestions(final CommandContext<S> p0, final SuggestionsBuilder p1) throws CommandSyntaxException;
}
