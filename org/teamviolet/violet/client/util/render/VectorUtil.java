//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import net.minecraft.util.math.*;

public class VectorUtil
{
    public static Vec2f plus(final Vec2f vec2f, final float d1) {
        return new Vec2f(vec2f.x + d1, vec2f.y + d1);
    }
    
    public static Vec2f minus(final Vec2f vec2f, final float d1) {
        return plus(vec2f, -d1);
    }
    
    public static Vec2f plus(final Vec2f vec2f, final float x1, final float y1) {
        return new Vec2f(vec2f.x + x1, vec2f.y + y1);
    }
    
    public static Vec2f minus(final Vec2f vec2f, final float x1, final float y1) {
        return plus(vec2f, -x1, -y1);
    }
    
    private VectorUtil() {
        throw new UnsupportedOperationException();
    }
}
