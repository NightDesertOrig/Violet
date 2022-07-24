//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.arguments;

import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import java.util.*;

public interface ArgumentType<T>
{
    T parse(final StringReader p0) throws CommandSyntaxException;
    
    default <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        return Suggestions.empty();
    }
    
    default Collection<String> getExamples() {
        return (Collection<String>)Collections.emptyList();
    }
}
