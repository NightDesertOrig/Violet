//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import java.util.function.*;
import com.mojang.brigadier.*;

public class DynamicCommandExceptionType implements CommandExceptionType
{
    private final Function<Object, Message> function;
    
    public DynamicCommandExceptionType(final Function<Object, Message> function) {
        this.function = function;
    }
    
    public CommandSyntaxException create(final Object arg) {
        return new CommandSyntaxException((CommandExceptionType)this, (Message)this.function.apply(arg));
    }
    
    public CommandSyntaxException createWithContext(final ImmutableStringReader reader, final Object arg) {
        return new CommandSyntaxException((CommandExceptionType)this, (Message)this.function.apply(arg), reader.getString(), reader.getCursor());
    }
}
