//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import com.mojang.brigadier.*;

public class DynamicNCommandExceptionType implements CommandExceptionType
{
    private final Function function;
    
    public DynamicNCommandExceptionType(final Function function) {
        this.function = function;
    }
    
    public CommandSyntaxException create(final Object a, final Object... args) {
        return new CommandSyntaxException((CommandExceptionType)this, this.function.apply(args));
    }
    
    public CommandSyntaxException createWithContext(final ImmutableStringReader reader, final Object... args) {
        return new CommandSyntaxException((CommandExceptionType)this, this.function.apply(args), reader.getString(), reader.getCursor());
    }
    
    public interface Function
    {
        Message apply(final Object[] p0);
    }
}
