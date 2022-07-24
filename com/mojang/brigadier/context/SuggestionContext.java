//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.context;

import com.mojang.brigadier.tree.*;

public class SuggestionContext<S>
{
    public final CommandNode<S> parent;
    public final int startPos;
    
    public SuggestionContext(final CommandNode<S> parent, final int startPos) {
        this.parent = parent;
        this.startPos = startPos;
    }
}
