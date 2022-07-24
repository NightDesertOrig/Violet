//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

import com.mojang.brigadier.tree.*;
import java.util.*;

@FunctionalInterface
public interface AmbiguityConsumer<S>
{
    void ambiguous(final CommandNode<S> p0, final CommandNode<S> p1, final CommandNode<S> p2, final Collection<String> p3);
}
