//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import com.mojang.brigadier.*;

public class SimpleCommandExceptionType implements CommandExceptionType
{
    private final Message message;
    
    public SimpleCommandExceptionType(final Message message) {
        this.message = message;
    }
    
    public CommandSyntaxException create() {
        return new CommandSyntaxException((CommandExceptionType)this, this.message);
    }
    
    public CommandSyntaxException createWithContext(final ImmutableStringReader reader) {
        return new CommandSyntaxException((CommandExceptionType)this, this.message, reader.getString(), reader.getCursor());
    }
    
    @Override
    public String toString() {
        return this.message.getString();
    }
}
