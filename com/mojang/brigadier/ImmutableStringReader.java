//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

public interface ImmutableStringReader
{
    String getString();
    
    int getRemainingLength();
    
    int getTotalLength();
    
    int getCursor();
    
    String getRead();
    
    String getRemaining();
    
    boolean canRead(final int p0);
    
    boolean canRead();
    
    char peek();
    
    char peek(final int p0);
}
