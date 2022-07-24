//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.entity.*;

public final class HoleStatus
{
    private final BlockPos pos;
    private final boolean bedrockHole;
    private final List<Entity> entities;
    
    HoleStatus(final BlockPos pos, final boolean bedrockHole, final List<Entity> entities) {
        this.pos = pos;
        this.bedrockHole = bedrockHole;
        this.entities = entities;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public boolean isBedrockHole() {
        return this.bedrockHole;
    }
    
    public List<Entity> getEntities() {
        return this.entities;
    }
}
