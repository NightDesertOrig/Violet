//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

public class LiteralMessage implements Message
{
    private final String string;
    
    public LiteralMessage(final String string) {
        this.string = string;
    }
    
    @Override
    public String getString() {
        return this.string;
    }
    
    @Override
    public String toString() {
        return this.string;
    }
}
