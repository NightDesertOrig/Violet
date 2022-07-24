//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.builder;

import java.util.function.*;
import com.mojang.brigadier.tree.*;
import com.mojang.brigadier.*;
import java.util.*;

public class LiteralArgumentBuilder<S> extends ArgumentBuilder<S, LiteralArgumentBuilder<S>>
{
    private final String literal;
    
    protected LiteralArgumentBuilder(final String literal) {
        this.literal = literal;
    }
    
    public static <S> LiteralArgumentBuilder<S> literal(final String name) {
        return new LiteralArgumentBuilder<S>(name);
    }
    
    protected LiteralArgumentBuilder<S> getThis() {
        return this;
    }
    
    public String getLiteral() {
        return this.literal;
    }
    
    public LiteralCommandNode<S> build() {
        final LiteralCommandNode<S> result = new LiteralCommandNode<S>(this.getLiteral(), this.getCommand(), this.getRequirement(), this.getRedirect(), this.getRedirectModifier(), this.isFork());
        for (final CommandNode<S> argument : this.getArguments()) {
            result.addChild(argument);
        }
        return result;
    }
}
