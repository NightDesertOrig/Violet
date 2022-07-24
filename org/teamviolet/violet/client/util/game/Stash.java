//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.util.math.*;

public final class Stash
{
    private Vec3d coords;
    private int chests;
    private int carts;
    private int hoppers;
    
    public Stash(final Vec3d coords, final int chests, final int carts, final int hoppers) {
        this.coords = coords;
        this.chests = chests;
        this.carts = carts;
        this.hoppers = hoppers;
    }
    
    public Vec3d getCoords() {
        return this.coords;
    }
    
    public int getChests() {
        return this.chests;
    }
    
    public int getCarts() {
        return this.carts;
    }
    
    public int getHoppers() {
        return this.hoppers;
    }
}
