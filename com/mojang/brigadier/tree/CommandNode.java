//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.tree;

import java.util.function.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.brigadier.context.*;
import java.util.concurrent.*;
import com.mojang.brigadier.suggestion.*;
import com.mojang.brigadier.builder.*;
import java.util.*;

public abstract class CommandNode<S> implements Comparable<CommandNode<S>>
{
    private final Map<String, CommandNode<S>> children;
    private final Map<String, LiteralCommandNode<S>> literals;
    private final Map<String, ArgumentCommandNode<S, ?>> arguments;
    private final Predicate<S> requirement;
    private final CommandNode<S> redirect;
    private final RedirectModifier<S> modifier;
    private final boolean forks;
    private Command<S> command;
    
    protected CommandNode(final Command<S> command, final Predicate<S> requirement, final CommandNode<S> redirect, final RedirectModifier<S> modifier, final boolean forks) {
        this.children = new LinkedHashMap<String, CommandNode<S>>();
        this.literals = new LinkedHashMap<String, LiteralCommandNode<S>>();
        this.arguments = new LinkedHashMap<String, ArgumentCommandNode<S, ?>>();
        this.command = command;
        this.requirement = requirement;
        this.redirect = redirect;
        this.modifier = modifier;
        this.forks = forks;
    }
    
    public Command<S> getCommand() {
        return this.command;
    }
    
    public Collection<CommandNode<S>> getChildren() {
        return this.children.values();
    }
    
    public CommandNode<S> getChild(final String name) {
        return this.children.get(name);
    }
    
    public CommandNode<S> getRedirect() {
        return this.redirect;
    }
    
    public RedirectModifier<S> getRedirectModifier() {
        return this.modifier;
    }
    
    public boolean canUse(final S source) {
        return this.requirement.test(source);
    }
    
    public void addChild(final CommandNode<S> node) {
        if (node instanceof RootCommandNode) {
            throw new UnsupportedOperationException("Cannot add a RootCommandNode as a child to any other CommandNode");
        }
        final CommandNode<S> child = this.children.get(node.getName());
        if (child != null) {
            if (node.getCommand() != null) {
                child.command = node.getCommand();
            }
            for (final CommandNode<S> grandchild : node.getChildren()) {
                child.addChild(grandchild);
            }
        }
        else {
            this.children.put(node.getName(), node);
            if (node instanceof LiteralCommandNode) {
                this.literals.put(node.getName(), (LiteralCommandNode)node);
            }
            else if (node instanceof ArgumentCommandNode) {
                this.arguments.put(node.getName(), (com.mojang.brigadier.tree.ArgumentCommandNode<S, ?>)node);
            }
        }
    }
    
    public void findAmbiguities(final AmbiguityConsumer<S> consumer) {
        Set<String> matches = new HashSet<String>();
        for (final CommandNode<S> child : this.children.values()) {
            for (final CommandNode<S> sibling : this.children.values()) {
                if (child == sibling) {
                    continue;
                }
                for (final String input : child.getExamples()) {
                    if (sibling.isValidInput(input)) {
                        matches.add(input);
                    }
                }
                if (matches.size() <= 0) {
                    continue;
                }
                consumer.ambiguous(this, (CommandNode)child, (CommandNode)sibling, (Collection)matches);
                matches = new HashSet<String>();
            }
            child.findAmbiguities(consumer);
        }
    }
    
    protected abstract boolean isValidInput(final String p0);
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandNode)) {
            return false;
        }
        final CommandNode<S> that = (CommandNode<S>)o;
        if (!this.children.equals(that.children)) {
            return false;
        }
        if (this.command != null) {
            if (this.command.equals(that.command)) {
                return true;
            }
        }
        else if (that.command == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.children.hashCode() + ((this.command != null) ? this.command.hashCode() : 0);
    }
    
    public Predicate<S> getRequirement() {
        return this.requirement;
    }
    
    public abstract String getName();
    
    public abstract String getUsageText();
    
    public abstract void parse(final StringReader p0, final CommandContextBuilder<S> p1) throws CommandSyntaxException;
    
    public abstract CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> p0, final SuggestionsBuilder p1) throws CommandSyntaxException;
    
    public abstract ArgumentBuilder<S, ?> createBuilder();
    
    protected abstract String getSortedKey();
    
    public Collection<? extends CommandNode<S>> getRelevantNodes(final StringReader input) {
        if (this.literals.size() <= 0) {
            return (Collection<? extends CommandNode<S>>)this.arguments.values();
        }
        final int cursor = input.getCursor();
        while (input.canRead() && input.peek() != ' ') {
            input.skip();
        }
        final String text = input.getString().substring(cursor, input.getCursor());
        input.setCursor(cursor);
        final LiteralCommandNode<S> literal = this.literals.get(text);
        if (literal != null) {
            return Collections.singleton(literal);
        }
        return (Collection<? extends CommandNode<S>>)this.arguments.values();
    }
    
    @Override
    public int compareTo(final CommandNode<S> o) {
        if (this instanceof LiteralCommandNode == o instanceof LiteralCommandNode) {
            return this.getSortedKey().compareTo(o.getSortedKey());
        }
        return (o instanceof LiteralCommandNode) ? 1 : -1;
    }
    
    public boolean isFork() {
        return this.forks;
    }
    
    public abstract Collection<String> getExamples();
}
