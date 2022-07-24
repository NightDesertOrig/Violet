//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.tree;

import com.mojang.brigadier.arguments.*;
import java.util.function.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import java.util.*;
import com.mojang.brigadier.builder.*;

public class ArgumentCommandNode<S, T> extends CommandNode<S>
{
    private static final String USAGE_ARGUMENT_OPEN = "<";
    private static final String USAGE_ARGUMENT_CLOSE = ">";
    private final String name;
    private final ArgumentType<T> type;
    private final SuggestionProvider<S> customSuggestions;
    
    public ArgumentCommandNode(final String name, final ArgumentType<T> type, final Command<S> command, final Predicate<S> requirement, final CommandNode<S> redirect, final RedirectModifier<S> modifier, final boolean forks, final SuggestionProvider<S> customSuggestions) {
        super(command, requirement, redirect, modifier, forks);
        this.name = name;
        this.type = type;
        this.customSuggestions = customSuggestions;
    }
    
    public ArgumentType<T> getType() {
        return this.type;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getUsageText() {
        return "<" + this.name + ">";
    }
    
    public SuggestionProvider<S> getCustomSuggestions() {
        return this.customSuggestions;
    }
    
    @Override
    public void parse(final StringReader reader, final CommandContextBuilder<S> contextBuilder) throws CommandSyntaxException {
        final int start = reader.getCursor();
        final T result = (T)this.type.parse(reader);
        final ParsedArgument<S, T> parsed = (ParsedArgument<S, T>)new ParsedArgument(start, reader.getCursor(), (Object)result);
        contextBuilder.withArgument(this.name, (ParsedArgument)parsed);
        contextBuilder.withNode((CommandNode)this, parsed.getRange());
    }
    
    @Override
    public CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) throws CommandSyntaxException {
        if (this.customSuggestions == null) {
            return (CompletableFuture<Suggestions>)this.type.listSuggestions((CommandContext)context, builder);
        }
        return (CompletableFuture<Suggestions>)this.customSuggestions.getSuggestions((CommandContext)context, builder);
    }
    
    public RequiredArgumentBuilder<S, T> createBuilder() {
        final RequiredArgumentBuilder<S, T> builder = (RequiredArgumentBuilder<S, T>)RequiredArgumentBuilder.argument(this.name, (ArgumentType)this.type);
        builder.requires((Predicate)this.getRequirement());
        builder.forward((CommandNode)this.getRedirect(), (RedirectModifier)this.getRedirectModifier(), this.isFork());
        builder.suggests((SuggestionProvider)this.customSuggestions);
        if (this.getCommand() != null) {
            builder.executes((Command)this.getCommand());
        }
        return builder;
    }
    
    public boolean isValidInput(final String input) {
        try {
            final StringReader reader = new StringReader(input);
            this.type.parse(reader);
            return !reader.canRead() || reader.peek() == ' ';
        }
        catch (CommandSyntaxException ignored) {
            return false;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArgumentCommandNode)) {
            return false;
        }
        final ArgumentCommandNode that = (ArgumentCommandNode)o;
        return this.name.equals(that.name) && this.type.equals(that.type) && super.equals(o);
    }
    
    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.type.hashCode();
        return result;
    }
    
    @Override
    protected String getSortedKey() {
        return this.name;
    }
    
    @Override
    public Collection<String> getExamples() {
        return (Collection<String>)this.type.getExamples();
    }
    
    @Override
    public String toString() {
        return "<argument " + this.name + ":" + this.type + ">";
    }
}
