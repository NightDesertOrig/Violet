//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.arguments;

import com.mojang.brigadier.context.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import java.util.*;

public class BoolArgumentType implements ArgumentType<Boolean>
{
    private static final Collection<String> EXAMPLES;
    
    private BoolArgumentType() {
    }
    
    public static BoolArgumentType bool() {
        return new BoolArgumentType();
    }
    
    public static boolean getBool(final CommandContext<?> context, final String name) {
        return context.getArgument(name, Boolean.class);
    }
    
    public Boolean parse(final StringReader reader) throws CommandSyntaxException {
        return reader.readBoolean();
    }
    
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        if ("true".startsWith(builder.getRemainingLowerCase())) {
            builder.suggest("true");
        }
        if ("false".startsWith(builder.getRemainingLowerCase())) {
            builder.suggest("false");
        }
        return builder.buildFuture();
    }
    
    public Collection<String> getExamples() {
        return BoolArgumentType.EXAMPLES;
    }
    
    static {
        EXAMPLES = Arrays.asList("true", "false");
    }
}
