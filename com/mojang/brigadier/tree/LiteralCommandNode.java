//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.tree;

import java.util.function.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import java.util.*;
import com.mojang.brigadier.builder.*;

public class LiteralCommandNode<S> extends CommandNode<S>
{
    private final String literal;
    private final String literalLowerCase;
    
    public LiteralCommandNode(final String literal, final Command<S> command, final Predicate<S> requirement, final CommandNode<S> redirect, final RedirectModifier<S> modifier, final boolean forks) {
        super((Command)command, (Predicate)requirement, (CommandNode)redirect, (RedirectModifier)modifier, forks);
        this.literal = literal;
        this.literalLowerCase = literal.toLowerCase(Locale.ROOT);
    }
    
    public String getLiteral() {
        return this.literal;
    }
    
    public String getName() {
        return this.literal;
    }
    
    public void parse(final StringReader reader, final CommandContextBuilder<S> contextBuilder) throws CommandSyntaxException {
        final int start = reader.getCursor();
        final int end = this.parse(reader);
        if (end > -1) {
            contextBuilder.withNode((CommandNode)this, StringRange.between(start, end));
            return;
        }
        throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().createWithContext((ImmutableStringReader)reader, (Object)this.literal);
    }
    
    private int parse(final StringReader reader) {
        final int start = reader.getCursor();
        if (reader.canRead(this.literal.length())) {
            final int end = start + this.literal.length();
            if (reader.getString().substring(start, end).equals(this.literal)) {
                reader.setCursor(end);
                if (!reader.canRead() || reader.peek() == ' ') {
                    return end;
                }
                reader.setCursor(start);
            }
        }
        return -1;
    }
    
    public CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        if (this.literalLowerCase.startsWith(builder.getRemainingLowerCase())) {
            return (CompletableFuture<Suggestions>)builder.suggest(this.literal).buildFuture();
        }
        return (CompletableFuture<Suggestions>)Suggestions.empty();
    }
    
    public boolean isValidInput(final String input) {
        return this.parse(new StringReader(input)) > -1;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiteralCommandNode)) {
            return false;
        }
        final LiteralCommandNode that = (LiteralCommandNode)o;
        return this.literal.equals(that.literal) && super.equals(o);
    }
    
    public String getUsageText() {
        return this.literal;
    }
    
    public int hashCode() {
        int result = this.literal.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
    
    public LiteralArgumentBuilder<S> createBuilder() {
        final LiteralArgumentBuilder<S> builder = (LiteralArgumentBuilder<S>)LiteralArgumentBuilder.literal(this.literal);
        builder.requires(this.getRequirement());
        builder.forward(this.getRedirect(), this.getRedirectModifier(), this.isFork());
        if (this.getCommand() != null) {
            builder.executes(this.getCommand());
        }
        return builder;
    }
    
    protected String getSortedKey() {
        return this.literal;
    }
    
    public Collection<String> getExamples() {
        return Collections.singleton(this.literal);
    }
    
    public String toString() {
        return "<literal " + this.literal + ">";
    }
}
