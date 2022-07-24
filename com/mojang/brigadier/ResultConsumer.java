//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier;

import com.mojang.brigadier.context.*;

@FunctionalInterface
public interface ResultConsumer<S>
{
    void onCommandComplete(final CommandContext<S> p0, final boolean p1, final int p2);
}
