//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.tree;

import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import com.mojang.brigadier.builder.*;
import java.util.*;

public class RootCommandNode<S> extends CommandNode<S>
{
    public RootCommandNode() {
        super((Command)null, c -> true, (CommandNode)null, s -> Collections.singleton(s.getSource()), false);
    }
    
    public String getName() {
        return "";
    }
    
    public String getUsageText() {
        return "";
    }
    
    public void parse(final StringReader reader, final CommandContextBuilder<S> contextBuilder) throws CommandSyntaxException {
    }
    
    public CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        return (CompletableFuture<Suggestions>)Suggestions.empty();
    }
    
    public boolean isValidInput(final String input) {
        return false;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof RootCommandNode && super.equals(o));
    }
    
    public ArgumentBuilder<S, ?> createBuilder() {
        throw new IllegalStateException("Cannot convert root into a builder");
    }
    
    protected String getSortedKey() {
        return "";
    }
    
    public Collection<String> getExamples() {
        return (Collection<String>)Collections.emptyList();
    }
    
    public String toString() {
        return "<root>";
    }
}
