//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import com.mojang.brigadier.*;

public class Dynamic2CommandExceptionType implements CommandExceptionType
{
    private final Function function;
    
    public Dynamic2CommandExceptionType(final Function function) {
        this.function = function;
    }
    
    public CommandSyntaxException create(final Object a, final Object b) {
        return new CommandSyntaxException((CommandExceptionType)this, this.function.apply(a, b));
    }
    
    public CommandSyntaxException createWithContext(final ImmutableStringReader reader, final Object a, final Object b) {
        return new CommandSyntaxException((CommandExceptionType)this, this.function.apply(a, b), reader.getString(), reader.getCursor());
    }
    
    public interface Function
    {
        Message apply(final Object p0, final Object p1);
    }
}
