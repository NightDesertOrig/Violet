//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

import com.mojang.brigadier.context.*;
import com.mojang.brigadier.tree.*;
import com.mojang.brigadier.exceptions.*;
import java.util.*;

public class ParseResults<S>
{
    private final CommandContextBuilder<S> context;
    private final Map<CommandNode<S>, CommandSyntaxException> exceptions;
    private final ImmutableStringReader reader;
    
    public ParseResults(final CommandContextBuilder<S> context, final ImmutableStringReader reader, final Map<CommandNode<S>, CommandSyntaxException> exceptions) {
        this.context = context;
        this.reader = reader;
        this.exceptions = exceptions;
    }
    
    public ParseResults(final CommandContextBuilder<S> context) {
        this(context, (ImmutableStringReader)new StringReader(""), Collections.emptyMap());
    }
    
    public CommandContextBuilder<S> getContext() {
        return this.context;
    }
    
    public ImmutableStringReader getReader() {
        return this.reader;
    }
    
    public Map<CommandNode<S>, CommandSyntaxException> getExceptions() {
        return this.exceptions;
    }
}
