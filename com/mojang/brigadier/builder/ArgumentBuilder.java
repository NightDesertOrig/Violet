//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.builder;

import java.util.function.*;
import com.mojang.brigadier.tree.*;
import com.mojang.brigadier.*;
import java.util.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.exceptions.*;

public abstract class ArgumentBuilder<S, T extends ArgumentBuilder<S, T>>
{
    private final RootCommandNode<S> arguments;
    private Command<S> command;
    private Predicate<S> requirement;
    private CommandNode<S> target;
    private RedirectModifier<S> modifier;
    private boolean forks;
    
    public ArgumentBuilder() {
        this.arguments = new RootCommandNode<S>();
        this.requirement = (s -> true);
        this.modifier = null;
    }
    
    protected abstract T getThis();
    
    public T then(final ArgumentBuilder<S, ?> argument) {
        if (this.target != null) {
            throw new IllegalStateException("Cannot add children to a redirected node");
        }
        this.arguments.addChild(argument.build());
        return this.getThis();
    }
    
    public T then(final CommandNode<S> argument) {
        if (this.target != null) {
            throw new IllegalStateException("Cannot add children to a redirected node");
        }
        this.arguments.addChild(argument);
        return this.getThis();
    }
    
    public Collection<CommandNode<S>> getArguments() {
        return this.arguments.getChildren();
    }
    
    public T executes(final Command<S> command) {
        this.command = command;
        return this.getThis();
    }
    
    public Command<S> getCommand() {
        return this.command;
    }
    
    public T requires(final Predicate<S> requirement) {
        this.requirement = requirement;
        return this.getThis();
    }
    
    public Predicate<S> getRequirement() {
        return this.requirement;
    }
    
    public T redirect(final CommandNode<S> target) {
        return this.forward(target, null, false);
    }
    
    public T redirect(final CommandNode<S> target, final SingleRedirectModifier<S> modifier) {
        return this.forward(target, (modifier == null) ? null : (o -> Collections.singleton(modifier.apply(o))), false);
    }
    
    public T fork(final CommandNode<S> target, final RedirectModifier<S> modifier) {
        return this.forward(target, modifier, true);
    }
    
    public T forward(final CommandNode<S> target, final RedirectModifier<S> modifier, final boolean fork) {
        if (!this.arguments.getChildren().isEmpty()) {
            throw new IllegalStateException("Cannot forward a node with children");
        }
        this.target = target;
        this.modifier = modifier;
        this.forks = fork;
        return this.getThis();
    }
    
    public CommandNode<S> getRedirect() {
        return this.target;
    }
    
    public RedirectModifier<S> getRedirectModifier() {
        return this.modifier;
    }
    
    public boolean isFork() {
        return this.forks;
    }
    
    public abstract CommandNode<S> build();
}
